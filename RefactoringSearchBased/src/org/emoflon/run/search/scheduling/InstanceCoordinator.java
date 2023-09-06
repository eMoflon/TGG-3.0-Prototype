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
import org.emoflon.refactoring.logging.LoggingConfig;
import org.emoflon.run.search.matching.ConstraintCheckingInstance;
import org.emoflon.run.search.matching.RuleMatchingInstance;
import org.emoflon.run.search.matching.WeightedMatch;

public abstract class InstanceCoordinator {
	
	private Collection<IBeXGTRule> rules = new HashSet<>();
	
	private Collection<RuleMatchingInstance> ruleMatchers = new LinkedList<>();
	private List<ConstraintCheckingInstance> constraintCheckers = new LinkedList<>();
	
	private Collection<IBeXGTMatch> ruleMatches = new HashSet<>();
	
	private Collection<WeightedMatch> checkedMatches = null;
	
	protected String path;
	protected int instanceCount;
	
	public InstanceCoordinator(String path, int instanceCount) {
		this.path = path;
		this.instanceCount = instanceCount;
		initialize();
	}
	
	public abstract void initialize();
	
	public void terminate() {
		System.out.println("Terminating rule matchers...");
		for(var ruleMatcher : ruleMatchers) {
			ruleMatcher.getApi().terminate();
		}
		System.out.println("Terminating constraint checkers...");
		for(var constraintChecker : constraintCheckers) {
			constraintChecker.getApi().terminate();
		}
	}

	public void update() {
		for(var ruleMatcher : ruleMatchers) {
			ruleMatcher.update();
		}
		
		System.out.println("Rule Matches: " + ruleMatches.size());
		
		int count=0;
		for(var match : ruleMatches) {
			constraintCheckers.get(count%constraintCheckers.size()).registerRuleMatchForChecking(match);
			count++;
		}
		
		checkedMatches = (Collection<WeightedMatch>) constraintCheckers.parallelStream().flatMap(c -> c.evaluate().stream()).collect(Collectors.toList());
	}
	
	public void performOneStep() {
		if(checkedMatches == null) {
			LoggingConfig.log("InstanceCoordinator: ", "Cannot perform a step because there are no checked matches. Please call update() first.");
			return;
		}
		var match = selectBestMatch(checkedMatches);
		if(match == null) 
			return;
		applyMatch(match);
	}
	
	private void applyMatch(IBeXGTMatch match) {
		for(var rule : rules) {
			if(rule.patternName.equals(match.getPatternName())) {
				LoggingConfig.logln("Applying Match:", match);
				rule.apply(match);
				break;
			}
		}
	}
	
	private IBeXGTMatch selectBestMatch(Collection<WeightedMatch> checkedMatches) {
		List<RuleApplicationGain> matchList = checkedMatches.parallelStream() //
				.map(m -> 
					new RuleApplicationGain(m.appliedRuleMatch(), m.repairs().size() - m.violations().size())
				).collect(Collectors.toList());
		
		matchList.sort((a,b) -> b.gain() - a.gain());
		var bestMatch = matchList.get(0);
		if(bestMatch.gain() > 0) {
			LoggingConfig.log("Choose Match: ", bestMatch.match() + " with gain " + bestMatch.gain());
			return bestMatch.match();
		}
		else {
			LoggingConfig.log("Blocked Match: ", "Best match is " + bestMatch.match() + " and has only a gain of " + bestMatch.gain());
			return null;
		}
	}

	public void registerRuleMatcher(RuleMatchingInstance instance) {
		ruleMatchers.add(instance);
		rules.addAll(instance.getRules());
	}
	
	public void registerObservedRule(IBeXGTRule rule) {
		rules.add(rule);
		rule.subscribeAppearing(m -> ruleMatches.add((IBeXGTMatch) m));
		rule.subscribeDisappearing(m -> ruleMatches.remove((IBeXGTMatch) m));
	}
	
	public void registerConstraintChecker(ConstraintCheckingInstance instance) {
		constraintCheckers.add(instance);
	}
	
	public void printAll() {
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
	
	public void printConstraintMatchCount() {
		for(var checker : constraintCheckers)
			checker.print();
	}
}
