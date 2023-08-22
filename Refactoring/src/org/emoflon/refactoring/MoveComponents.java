package org.emoflon.refactoring;

import java.util.LinkedList;

import org.emoflon.refactoring.analysis.ConstraintCounter;
import org.emoflon.refactoring.analysis.OverlapCreator;

import refactoringgtl.moving.api.MovingHiPEGtApi;

public class MoveComponents extends RefactoringCase<MovingHiPEGtApi>{


	public MoveComponents(String path) {
		super(path);
	}

	@Override
	protected void createAndRegisterOverlaps() {
		var moveComponentName = api.moveComponent().ruleName;
		
		var importedComponentInSystemName = api.importedComponentInSystem().patternName;
		var importedComponentNotInSystemName = api.importedComponentNotInSystem().patternName;
		var importingComponentInSystemName = api.importingComponentInSystem().patternName;
		var importingComponentNotInSystemName = api.importingComponentNotInSystem().patternName;

		rules.add(api.moveComponent());
		
		violations.add(api.importedComponentInSystem());
		violations.add(api.importedComponentNotInSystem());
		violations.add(api.importingComponentInSystem());
		violations.add(api.importingComponentNotInSystem());
		
		repairs.add(api.importedComponentInSystem());
		repairs.add(api.importedComponentNotInSystem());
		repairs.add(api.importingComponentInSystem());
		repairs.add(api.importingComponentNotInSystem());
		
		// create violations
		var violationOverlap1 = new OverlapCreator();
		var violationOverlap2 = new OverlapCreator();

		violationOverlap1.registerOverlap(api.moveComponent(), "formerSystem", "component");
		violationOverlap1.registerOverlap(api.importedComponentInSystem(), "system", "component"); // +
		violationOverlap1.registerOverlap(api.importingComponentInSystem(), "system", "component"); // +
		
		violationOverlap2.registerOverlap(api.moveComponent(), "newSystem", "component");
		violationOverlap2.registerOverlap(api.importedComponentNotInSystem(), "system", "component");
		violationOverlap2.registerOverlap(api.importingComponentNotInSystem(), "system", "component");
		
		name2overlapCreators.computeIfAbsent(moveComponentName, (x) -> new LinkedList<>()).add(violationOverlap1);
		name2overlapCreators.computeIfAbsent(importedComponentInSystemName, (x) -> new LinkedList<>()).add(violationOverlap1);
		name2overlapCreators.computeIfAbsent(importingComponentInSystemName, (x) -> new LinkedList<>()).add(violationOverlap1);
		
		name2overlapCreators.computeIfAbsent(moveComponentName, (x) -> new LinkedList<>()).add(violationOverlap2);
		name2overlapCreators.computeIfAbsent(importedComponentNotInSystemName, (x) -> new LinkedList<>()).add(violationOverlap2);
		name2overlapCreators.computeIfAbsent(importingComponentNotInSystemName, (x) -> new LinkedList<>()).add(violationOverlap2);
		
		// create repairs
		var repairOverlap1 = new OverlapCreator();
		var repairOverlap2 = new OverlapCreator();

//		repairOverlap1.registerOverlap(api.moveComponent(), "newSystem", "component");
//		repairOverlap1.registerOverlap(api.importedComponentInSystem(), "system", "component"); // +
//		repairOverlap1.registerOverlap(api.importingComponentInSystem(), "system", "component"); // +
//		
		repairOverlap2.registerOverlap(api.moveComponent(), "formerSystem", "component");
//		repairOverlap2.registerOverlap(api.importedComponentNotInSystem(), "system", "component");
		repairOverlap2.registerOverlap(api.importingComponentNotInSystem(), "system", "component");
		
		name2overlapCreators.computeIfAbsent(moveComponentName, (x) -> new LinkedList<>()).add(repairOverlap1);
		name2overlapCreators.computeIfAbsent(importedComponentInSystemName, (x) -> new LinkedList<>()).add(repairOverlap1);
		name2overlapCreators.computeIfAbsent(importingComponentInSystemName, (x) -> new LinkedList<>()).add(repairOverlap1);

		name2overlapCreators.computeIfAbsent(moveComponentName, (x) -> new LinkedList<>()).add(repairOverlap2);
		name2overlapCreators.computeIfAbsent(importedComponentNotInSystemName, (x) -> new LinkedList<>()).add(repairOverlap2);
		name2overlapCreators.computeIfAbsent(importingComponentNotInSystemName, (x) -> new LinkedList<>()).add(repairOverlap2);
		
		var violations = new LinkedList<OverlapCreator>();
		violations.add(violationOverlap1);
		violations.add(violationOverlap2);

		var repairs = new LinkedList<OverlapCreator>();
		repairs.add(repairOverlap1);
		repairs.add(repairOverlap2);
		
		constraintCounter = new ConstraintCounter(violations, repairs);
	}

	@Override
	protected void createAPI() {
		api = new MovingHiPEGtApi();
	}
}
