package org.emoflon.run.search.softwareSystem;



import org.emoflon.search.matching.ConstraintCheckingInstance;
import org.emoflon.search.matching.RuleMatchingInstance;
import org.emoflon.search.scheduling.InstanceCoordinator;

import refactoring.constraints.api.ConstraintsHiPEGtApi;
import refactoring.rules.api.RulesHiPEGtApi;

public class MoveComponentsCoordinator extends InstanceCoordinator {
	
	public MoveComponentsCoordinator(String path, int instanceCount) {
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
		registerObservedRule(ruleMatcher.getApi().moveComponent());
		
		for(int i=0; i < instanceCount; i++) {
			var constraintChecker = new ConstraintCheckingInstance<ConstraintsHiPEGtApi>(ruleMatcher.getApi().getModel()) {
				@Override
				protected void createAPI() {
					api = new ConstraintsHiPEGtApi();
				}
			};
			registerConstraintChecker(constraintChecker);
			constraintChecker.registerRule(ruleMatcher.getApi().moveComponent());
			constraintChecker.registerConstraintPattern(constraintChecker.getApi().importedComponentsInSameSystem(), false);
			
			// we call this, so that the initial matches are found first
			// in the future, we should be able to ignore the initial delta entirely
			constraintChecker.getApi().updateMatches();
		}
		
		
	}
	
}
