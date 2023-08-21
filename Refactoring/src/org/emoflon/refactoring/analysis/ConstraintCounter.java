package org.emoflon.refactoring.analysis;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.emoflon.ibex.gt.engine.IBeXGTMatch;
import org.emoflon.refactoring.logging.Formatter;
import org.emoflon.refactoring.logging.LoggingConfig;

import softwareSystem.Component;

@SuppressWarnings("rawtypes")
public class ConstraintCounter {

	private Collection<OverlapCreator> violationOverlapCreators;
	private Collection<OverlapCreator> repairOverlapCreators;

	private Collection<IBeXGTMatch> ruleMatches = new HashSet<>();
	private Map<OverlapCreator, OverlapMappings> creator2violations = new HashMap<>();
	private Map<OverlapCreator, OverlapMappings> creator2repairs = new HashMap<>();
	
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
	
	public int countViolations(IBeXGTMatch match) {
		return count(violationOverlapCreators, creator2violations, match);
	}
	
	public int countRepairs(IBeXGTMatch match) {
		return count(repairOverlapCreators, creator2repairs, match);
	}
	
	private int count(Collection<OverlapCreator> overlapCreators, Map<OverlapCreator, OverlapMappings> creator2mappings, IBeXGTMatch match) {
		var count = 0;
		for(var creator : overlapCreators) {
			if(creator.supportsPattern(match.getPatternName())) {
				var overlap = creator.createOverlap(match);
				count += creator2mappings.get(creator).overlap2matches().getOrDefault(overlap, new LinkedList<>()).size();
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
}

record OverlapMappings(Map<MatchOverlap, Collection<IBeXGTMatch>> overlap2matches) {

}
