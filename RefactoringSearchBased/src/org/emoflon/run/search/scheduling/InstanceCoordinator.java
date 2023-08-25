package org.emoflon.run.search.scheduling;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.emoflon.ibex.gt.engine.IBeXGTMatch;
import org.emoflon.ibex.gt.engine.IBeXGTRule;
import org.emoflon.refactoring.RuleApplicationGain;
import org.emoflon.refactoring.logging.Formatter;
import org.emoflon.run.search.matching.ConstraintCheckingInstance;
import org.emoflon.run.search.matching.RuleMatchingInstance;
import org.emoflon.run.search.matching.WeightedMatch;

public abstract class InstanceCoordinator {
	
	private Collection<RuleMatchingInstance> ruleMatchers = new LinkedList<>();
	private List<ConstraintCheckingInstance> constraintCheckers = new LinkedList<>();
	
	private Collection<IBeXGTMatch> ruleMatches = new HashSet<>();
	
	private Collection<WeightedMatch> checkedMatches = null;
	
	public InstanceCoordinator() {
		
	}
	
	public abstract void initialize();

	public void update() {
		for(var ruleMatcher : ruleMatchers) {
			ruleMatcher.update();
		}
		
		int count=0;
		for(var match : ruleMatches) {
			constraintCheckers.get(count%constraintCheckers.size()).registerRuleMatchForChecking(match);
			count++;
		}
		
		checkedMatches = (Collection<WeightedMatch>) constraintCheckers.parallelStream().flatMap(c -> c.evaluate().stream()).collect(Collectors.toList());
	}
	
	public void performOneStep() {
		update();
		printAll(checkedMatches);
		var match = selectBestMatch(checkedMatches);
		
	}
	
	private Object selectBestMatch(Collection<WeightedMatch> checkedMatches) {
		List<RuleApplicationGain> matchList = checkedMatches.parallelStream() //
				.map(m -> 
					new RuleApplicationGain(m.appliedRuleMatch(), m.repairs().size() - m.violations().size())
				).collect(Collectors.toList());
		
		matchList.sort((a,b) -> b.gain() - a.gain());
		var bestMatch = matchList.get(0);
		
		return bestMatch;
	}

	public void registerRuleMatcher(RuleMatchingInstance instance) {
		ruleMatchers.add(instance);
	}
	
	public void registerObservedRule(IBeXGTRule rule) {
		rule.subscribeAppearing(m -> ruleMatches.add((IBeXGTMatch) m));
		rule.subscribeDisappearing(m -> ruleMatches.remove((IBeXGTMatch) m));
	}
	
	public void registerConstraintChecker(ConstraintCheckingInstance instance) {
		constraintCheckers.add(instance);
	}
	
	public void printAll(Collection<WeightedMatch> checkedMatches) {
		System.out.println("\n\n\n");
		System.out.println("Printing violations and repairs: ");
		List<String> lines = new LinkedList<>();

		for(var checkedMatch : checkedMatches) {
			var line = Formatter.toString(checkedMatch.appliedRuleMatch());
			var violations = checkedMatch.violations().size();
			var repairs = checkedMatch.repairs().size();
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
	
}
