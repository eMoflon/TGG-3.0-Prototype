package org.emoflon.run.search.classDiagram;



import org.emoflon.search.matching.ConstraintCheckingInstance;
import org.emoflon.search.matching.RuleMatchingInstance;
import org.emoflon.search.scheduling.InstanceCoordinator;

import refactoringcd.constraints.api.ConstraintsHiPEGtApi;
import refactoringcd.rules.api.RulesHiPEGtApi;

public class MoveFeaturesCoordinator extends InstanceCoordinator {
	
	public MoveFeaturesCoordinator(String path, int instanceCount) {
		super(path, instanceCount);
	}

	@Override
	public void initialize() {
		var ruleMatcher = new RuleMatchingInstance<RulesHiPEGtApi>(path) {
			@Override
			protected void createAPI() {
				api = new RulesHiPEGtApi();
			}
		};
		registerRuleMatcher(ruleMatcher);
		registerObservedRule(ruleMatcher.getApi().moveMethod());
		registerObservedRule(ruleMatcher.getApi().moveAttribute());
		
		for(int i=0; i < instanceCount; i++) {
			var constraintChecker = new ConstraintCheckingInstance<ConstraintsHiPEGtApi>(ruleMatcher.getApi().getModel()) {
				@Override
				protected void createAPI() {
					api = new ConstraintsHiPEGtApi();
				}
			};
			registerConstraintChecker(constraintChecker);
			constraintChecker.registerRule(ruleMatcher.getApi().moveMethod());
			constraintChecker.registerRule(ruleMatcher.getApi().moveAttribute());
			constraintChecker.registerConstraintPattern(constraintChecker.getApi().atLeastOneCommonAttributeDependency(), true);
			constraintChecker.registerConstraintPattern(constraintChecker.getApi().externalMethodAttributeDependency(), true);
			
			// we call this, so that the initial matches are found first
			// in the future, we should be able to ignore the initial delta entirely
			constraintChecker.getApi().updateMatches();
		}
		
	}
	
}
