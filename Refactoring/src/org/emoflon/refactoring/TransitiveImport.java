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

public class TransitiveImport {

	@SuppressWarnings("rawtypes")
	private MovingGtApi api;
	
	private Map<String, Collection<OverlapCreator>> name2overlapCreators = new HashMap<>();
	
	private Set<String> rules = new HashSet<>();
	private Set<String> violation = new HashSet<>();
	private Set<String> repairs = new HashSet<>();
	
	private ConstraintCounter constraintCounter;
	
	public TransitiveImport(String path) {
		initialize(path);
	}

	private void initialize(String path) {
		api = new MovingHiPEGtApi();
		loadModel(path);
		api.initializeEngine();
		
		var createImport = api.createImport().ruleName;
		
		var transitiveImportMissingFront = api.transitiveImportMissingFront().patternName;
		var transitiveImportMissingBack = api.transitiveImportMissingBack().patternName;
		var transitiveImportCreated = api.transitiveImportCreated().patternName;

		rules.add(createImport);
		
		violation.add(transitiveImportMissingFront);
		violation.add(transitiveImportMissingBack);
		
		repairs.add(transitiveImportCreated);
		
		// create violations
		var violationOverlap1 = new OverlapCreator();

		violationOverlap1.registerOverlap(createImport, "component", "otherComponent");
		violationOverlap1.registerOverlap(transitiveImportMissingFront, "firstComponent", "secondComponent"); // +
		violationOverlap1.registerOverlap(transitiveImportMissingBack, "secondComponent", "thirdComponent"); // +
		
		name2overlapCreators.computeIfAbsent(createImport, (x) -> new LinkedList<>()).add(violationOverlap1);
		name2overlapCreators.computeIfAbsent(transitiveImportMissingFront, (x) -> new LinkedList<>()).add(violationOverlap1);
		name2overlapCreators.computeIfAbsent(transitiveImportMissingBack, (x) -> new LinkedList<>()).add(violationOverlap1);
		
		
		// create repairs
		var repairOverlap1 = new OverlapCreator();

		repairOverlap1.registerOverlap(createImport, "component", "otherComponent");
		repairOverlap1.registerOverlap(transitiveImportCreated, "firstComponent", "thirdComponent"); // +
		
		name2overlapCreators.computeIfAbsent(createImport, (x) -> new LinkedList<>()).add(repairOverlap1);
		name2overlapCreators.computeIfAbsent(transitiveImportCreated, (x) -> new LinkedList<>()).add(repairOverlap1);

		var violations = new LinkedList<OverlapCreator>();
		violations.add(violationOverlap1);

		var repairs = new LinkedList<OverlapCreator>();
		repairs.add(repairOverlap1);
		
		constraintCounter = new ConstraintCounter(violations, repairs);
		
		registerSubscriptions();
	}
	
	private void registerSubscriptions() {
		api.createImport().subscribeAppearing(constraintCounter::addRuleMatch);
		api.createImport().unsubscribeAppearing(constraintCounter::removeRuleMatch);
		
		// pattern1
		api.transitiveImportMissingFront().subscribeAppearing(constraintCounter::addViolation);
		api.transitiveImportMissingFront().unsubscribeAppearing(constraintCounter::removeViolation);
		
		// pattern2
		api.transitiveImportMissingBack().subscribeAppearing(constraintCounter::addViolation);
		api.transitiveImportMissingBack().unsubscribeAppearing(constraintCounter::removeViolation);
		
		// pattern3
		api.transitiveImportCreated().subscribeAppearing(constraintCounter::addRepair);
		api.transitiveImportCreated().unsubscribeAppearing(constraintCounter::removeRepair);
		
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
