package org.emoflon.refactoring.ac.analysis;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.emoflon.ibex.gt.engine.IBeXGTMatch;
import org.emoflon.refactoring.RankedRuleApplication;
import org.emoflon.refactoring.logging.Formatter;
import org.emoflon.refactoring.logging.LoggingConfig;

@SuppressWarnings("rawtypes")
public class ConstraintCounter {

	private Collection<OverlapCreator> violationOverlapCreators;
	private Collection<OverlapCreator> repairOverlapCreators;

	private Collection<IBeXGTMatch> ruleMatches = new HashSet<>();
	private Map<OverlapCreator, OverlapMappings> creator2violations = new HashMap<>();
	private Map<OverlapCreator, OverlapMappings> creator2repairs = new HashMap<>();
	
	private int patternMatchCount = 0;
	
	private int violationCount = 0;
	private int repairCount = 0;
	
	public ConstraintCounter(Collection<OverlapCreator> violationOverlapCreators, Collection<OverlapCreator> repairOverlapCreators) {
		this.violationOverlapCreators = violationOverlapCreators;
		this.repairOverlapCreators = repairOverlapCreators;

		for(var violationOverlapCreator : violationOverlapCreators)
			creator2violations.put(violationOverlapCreator, new OverlapMappings(new HashMap<>()));

		for(var repairOverlapCreator : repairOverlapCreators)
			creator2repairs.put(repairOverlapCreator, new OverlapMappings(new HashMap<>()));
	}
	
	public void addRuleMatch(IBeXGTMatch match) {
		LoggingConfig.log("addRule", match);
		ruleMatches.add(match);
	}
	
	public void removeRuleMatch(IBeXGTMatch match) {
		LoggingConfig.log("removeRule", match);
		ruleMatches.remove(match);
	}
	
	public void addViolation(IBeXGTMatch match) {
		patternMatchCount++;
		
		for(var violationOverlapCreator : violationOverlapCreators) {
			if(!violationOverlapCreator.supportsPattern(match.getPatternName()))
				continue;
			
			var overlap = violationOverlapCreator.createOverlap(match);
			var violations = creator2violations.get(violationOverlapCreator).overlap2matches().computeIfAbsent(overlap, (x) -> new HashSet<>());
			LoggingConfig.log("addViolation", match);
			violations.add(match);
		}
	}
	
	public void removeViolation(IBeXGTMatch match) {
		patternMatchCount--;

		for(var violationOverlapCreator : violationOverlapCreators) {
			if(!violationOverlapCreator.supportsPattern(match.getPatternName()))
				continue;
			
			var overlap = violationOverlapCreator.createOverlap(match);
			var violations = creator2violations.get(violationOverlapCreator).overlap2matches().get(overlap);
			if(overlap != null) {
				LoggingConfig.log("removeViolation", match);
				violations.remove(match);
			}
		}
	}
	
	public void addRepair(IBeXGTMatch match) {
		patternMatchCount++;
		
		for(var repairOverlapCreator : repairOverlapCreators) {
			if(!repairOverlapCreator.supportsPattern(match.getPatternName()))
				continue;
			
			var overlap = repairOverlapCreator.createOverlap(match);
			var repairs = creator2repairs.get(repairOverlapCreator).overlap2matches().computeIfAbsent(overlap, (x) -> new HashSet<>());
			repairs.add(match);
			LoggingConfig.log("addRepair", match);
		}
	}
	
	public void removeRepair(IBeXGTMatch match) {
		patternMatchCount--;
		
		for(var repairOverlapCreator : repairOverlapCreators) {
			if(!repairOverlapCreator.supportsPattern(match.getPatternName()))
				continue;
		
			var overlap = repairOverlapCreator.createOverlap(match);
			var repairs = creator2repairs.get(repairOverlapCreator).overlap2matches().get(overlap);
			if(repairs != null) {
				repairs.remove(match);
				LoggingConfig.log("removeRepair", match);
			}
		}
	}
	
	
	public RankedRuleApplication getNextMatch() {
		return getNextMatch(false);
	}

	public RankedRuleApplication getNextMatch(boolean ignoreNegativeGain) {
		var matchList = ruleMatches.parallelStream() //
				.map(m -> {
						var repairs = countRepairs(m);
						var violations = countViolations(m);
						return new RankedRuleApplication(m, calculcateGain(m), repairs, violations);					
					}
				).collect(Collectors.toList());
		
		matchList.sort((a,b) -> b.gain() - a.gain());
		var bestMatch = matchList.get(0);
		if(ignoreNegativeGain || bestMatch.gain() > 0) {
			LoggingConfig.log("Choose Match: ", bestMatch.match() + " with gain " + bestMatch.gain());
			
			{
				// DEBUG
				countRepairs(bestMatch.match());
				countViolations(bestMatch.match());
			}
			
			// we assume that this match will be applied
			violationCount += bestMatch.violations();
			repairCount += bestMatch.repairs();
			return bestMatch;
		}
		else {
			LoggingConfig.log("Blocked Match: ", "Best match is " + bestMatch.match() + " and has only a gain of " + bestMatch.gain());
			return null;
		}

	}
	
	public int calculcateGain(IBeXGTMatch match) {
		return countRepairs(match) - countViolations(match);
	}
	
	public int countViolations(IBeXGTMatch match) {
//		System.out.println("	Violation for " + match + ": ");
		return count(violationOverlapCreators, creator2violations, match);
	}
	
	public int countRepairs(IBeXGTMatch match) {
//		System.out.println("	Repair for " + match + ": ");
		return count(repairOverlapCreators, creator2repairs, match);
	}
	
	private int count(Collection<OverlapCreator> overlapCreators, Map<OverlapCreator, OverlapMappings> creator2mappings, IBeXGTMatch match) {
		var count = 0;
		for(var creator : overlapCreators) {
			if(creator.supportsPattern(match.getPatternName())) {
				var overlap = creator.createOverlap(match);
				var overlapMatches = creator2mappings.get(creator).overlap2matches().getOrDefault(overlap, new LinkedList<>());
//				for(var overlapMatch : overlapMatches) {
//					System.out.println("		" + overlapMatch);
//				}
				var overlapCount = overlapMatches.size();
				count += overlapCount;
			}
		}
		return count;
	}
	
	public void printAll() {
		System.out.println("\n\n\n");
		System.out.println("Printing violations and repairs: ");
		List<String> lines = new LinkedList<>();

		for(var ruleMatch : ruleMatches) {
			var line = Formatter.toString(ruleMatch);
			var violations = countViolations(ruleMatch);
			var repairs = countRepairs(ruleMatch);
			line += " violations: " + violations;
			line += " repairs: " + repairs;
			line += " gain: " + (repairs-violations);
			
			lines.add(line);
		}
		
		Collections.sort(lines);
		
		for(var line : lines) {
			System.out.println(line);
		}
		System.out.println("\n\n\n");
	}
	
	public void printViolations() {
		System.out.println("\n\n\n");
		System.out.println("Printing violations: ");
		List<String> lines = new LinkedList<>();
		
		for(var ruleMatch : ruleMatches) {
			var line = Formatter.toString(ruleMatch);
			line += " violations: " + countViolations(ruleMatch);
			lines.add(line);
		}
		
		Collections.sort(lines);
		
		for(var line : lines) {
			System.out.println(line);
		}
	}
	
	public void printRepairs() {
		System.out.println("\n\n\n");
		System.out.println("Printing repairs: ");
		List<String> lines = new LinkedList<>();
		
		for(var ruleMatch : ruleMatches) {
			var line = Formatter.toString(ruleMatch);
			line += " repairs: " + countRepairs(ruleMatch);
			lines.add(line);
		}
		
		Collections.sort(lines);
		
		for(var line : lines) {
			System.out.println(line);
		}	
	}
	
	public int countRuleMatches() {
		return ruleMatches.size();
	}
	
	public int countPatternMatches() {
		return patternMatchCount;
	}
	
	public int countTotalViolations() {
		return violationCount;
	}
	
	public int countTotalRepairs() {
		return repairCount;
	}
}

record OverlapMappings(Map<MatchOverlap, Collection<IBeXGTMatch>> overlap2matches) {

}
