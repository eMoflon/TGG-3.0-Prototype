package org.emoflon.refactoring;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.emoflon.refactoring.analysis.ConstraintCounter;
import org.emoflon.refactoring.analysis.OverlapCreator;

import refactoringgtl.moving.api.MovingGtApi;
import refactoringgtl.moving.api.MovingHiPEGtApi;

public class MoveComponents {

	@SuppressWarnings("rawtypes")
	private MovingGtApi api;
	
	private Map<String, Collection<OverlapCreator>> name2overlapCreators = new HashMap<>();
	
	private Set<String> rules = new HashSet<>();
	private Set<String> violation = new HashSet<>();
	private Set<String> repairs = new HashSet<>();
	
	private ConstraintCounter constraintCounter;
	
	public MoveComponents(String path) {
		initialize(path);
	}

	private void initialize(String path) {
		api = new MovingHiPEGtApi();
		loadModel(path);
		api.initializeEngine();
		
		var moveComponent = api.moveComponent().ruleName;
		
		var importedComponentInSystem = api.importedComponentInSystem().patternName;
		var importedComponentNotInSystem = api.importedComponentNotInSystem().patternName;
		var importingComponentInSystem = api.importingComponentInSystem().patternName;
		var importingComponentNotInSystem = api.importingComponentNotInSystem().patternName;

		rules.add(moveComponent);
		
		violation.add(importedComponentInSystem);
		violation.add(importedComponentNotInSystem);
		violation.add(importingComponentInSystem);
		violation.add(importingComponentNotInSystem);
		
		repairs.add(importedComponentInSystem);
		repairs.add(importedComponentNotInSystem);
		repairs.add(importingComponentInSystem);
		repairs.add(importingComponentNotInSystem);
		
		// create violations
		var violationOverlap1 = new OverlapCreator();
		var violationOverlap2 = new OverlapCreator();

		violationOverlap1.registerOverlap(moveComponent, "formerSystem", "component");
		violationOverlap1.registerOverlap(importedComponentInSystem, "system", "component"); // +
		violationOverlap1.registerOverlap(importingComponentInSystem, "system", "component"); // +
		
		violationOverlap2.registerOverlap(moveComponent, "newSystem", "component");
		violationOverlap2.registerOverlap(importedComponentNotInSystem, "system", "component");
		violationOverlap2.registerOverlap(importingComponentNotInSystem, "system", "component");
		
		name2overlapCreators.computeIfAbsent(moveComponent, (x) -> new LinkedList<>()).add(violationOverlap1);
		name2overlapCreators.computeIfAbsent(importedComponentInSystem, (x) -> new LinkedList<>()).add(violationOverlap1);
		name2overlapCreators.computeIfAbsent(importingComponentInSystem, (x) -> new LinkedList<>()).add(violationOverlap1);
		
		name2overlapCreators.computeIfAbsent(moveComponent, (x) -> new LinkedList<>()).add(violationOverlap2);
		name2overlapCreators.computeIfAbsent(importedComponentNotInSystem, (x) -> new LinkedList<>()).add(violationOverlap2);
		name2overlapCreators.computeIfAbsent(importingComponentNotInSystem, (x) -> new LinkedList<>()).add(violationOverlap2);
		
		// create repairs
		var repairOverlap1 = new OverlapCreator();
		var repairOverlap2 = new OverlapCreator();

		repairOverlap1.registerOverlap(moveComponent, "newSystem", "component");
//		repairOverlap1.registerOverlap(importedComponentInSystem, "system", "component"); // +
//		repairOverlap1.registerOverlap(importingComponentInSystem, "system", "component"); // +
		
		repairOverlap2.registerOverlap(moveComponent, "formerSystem", "component");
//		repairOverlap2.registerOverlap(importedComponentNotInSystem, "system", "component");
		repairOverlap2.registerOverlap(importingComponentNotInSystem, "system", "component");
		
		name2overlapCreators.computeIfAbsent(moveComponent, (x) -> new LinkedList<>()).add(repairOverlap1);
		name2overlapCreators.computeIfAbsent(importedComponentInSystem, (x) -> new LinkedList<>()).add(repairOverlap1);
		name2overlapCreators.computeIfAbsent(importingComponentInSystem, (x) -> new LinkedList<>()).add(repairOverlap1);

		name2overlapCreators.computeIfAbsent(moveComponent, (x) -> new LinkedList<>()).add(repairOverlap2);
		name2overlapCreators.computeIfAbsent(importedComponentNotInSystem, (x) -> new LinkedList<>()).add(repairOverlap2);
		name2overlapCreators.computeIfAbsent(importingComponentNotInSystem, (x) -> new LinkedList<>()).add(repairOverlap2);
		
		var violations = new LinkedList<OverlapCreator>();
		violations.add(violationOverlap1);
		violations.add(violationOverlap2);

		var repairs = new LinkedList<OverlapCreator>();
		repairs.add(repairOverlap1);
		repairs.add(repairOverlap2);
		
		constraintCounter = new ConstraintCounter(violations, repairs);
		
		registerSubscriptions();
	}
	
	private void registerSubscriptions() {
		api.moveComponent().subscribeAppearing(constraintCounter::addRuleMatch);
		api.moveComponent().unsubscribeAppearing(constraintCounter::removeRuleMatch);
		
		// pattern1
		api.importedComponentInSystem().subscribeAppearing(constraintCounter::addViolation);
		api.importedComponentInSystem().unsubscribeAppearing(constraintCounter::removeViolation);
		
		api.importedComponentInSystem().subscribeAppearing(constraintCounter::addRepair);
		api.importedComponentInSystem().unsubscribeAppearing(constraintCounter::removeRepair);
		
		// pattern2
		api.importingComponentInSystem().subscribeAppearing(constraintCounter::addViolation);
		api.importingComponentInSystem().unsubscribeAppearing(constraintCounter::removeViolation);
		
		api.importingComponentInSystem().subscribeAppearing(constraintCounter::addRepair);
		api.importingComponentInSystem().unsubscribeAppearing(constraintCounter::removeRepair);
		
		// pattern3
		api.importedComponentNotInSystem().subscribeAppearing(constraintCounter::addViolation);
		api.importedComponentNotInSystem().unsubscribeAppearing(constraintCounter::removeViolation);
		
		api.importedComponentNotInSystem().subscribeAppearing(constraintCounter::addRepair);
		api.importedComponentNotInSystem().unsubscribeAppearing(constraintCounter::removeRepair);
		
		// pattern4
		api.importingComponentNotInSystem().subscribeAppearing(constraintCounter::addViolation);
		api.importingComponentNotInSystem().unsubscribeAppearing(constraintCounter::removeViolation);
		
		api.importingComponentNotInSystem().subscribeAppearing(constraintCounter::addRepair);
		api.importingComponentNotInSystem().unsubscribeAppearing(constraintCounter::removeRepair);
	}

	public void loadModel(String model) {
		var path = api.getWorkspacePath() + "Refactoring/resources/" + model;
		try {
			api.addModel(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MovingGtApi getApi() {
		return api;
	}
	
	public ConstraintCounter getConstraintCounter() {
		return constraintCounter;
	}
	
}
