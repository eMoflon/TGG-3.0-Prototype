package org.emoflon.refactoring;

import java.util.LinkedList;

import org.emoflon.refactoring.analysis.ConstraintCounter;
import org.emoflon.refactoring.analysis.OverlapCreator;

import refactoringgtl.moving.api.MovingHiPEGtApi;
import softwareSystem.SoftwareSystemPackage;

public class TransitiveImport extends RefactoringCase<MovingHiPEGtApi>{

	public TransitiveImport(String path) {
		super(path);
	}

	@Override
	protected void createAndRegisterOverlaps() {
		var createImportName = api.createImport().ruleName;
		
		var transitiveImportMissingFrontName = api.transitiveImportMissingFront().patternName;
		var transitiveImportMissingBackName = api.transitiveImportMissingBack().patternName;
		var transitiveImportCreatedName = api.transitiveImportCreated().patternName;

		rules.add(api.createImport());
		
		violations.add(api.transitiveImportMissingFront());
		violations.add(api.transitiveImportMissingBack());
		
		repairs.add(api.transitiveImportCreated());
		
		// create violations
		var violationOverlap1 = new OverlapCreator();

		violationOverlap1.registerOverlap(api.createImport(), "component", "otherComponent");
		violationOverlap1.registerOverlap(api.transitiveImportMissingFront(), "firstComponent", "secondComponent"); // +
		violationOverlap1.registerOverlap(api.transitiveImportMissingBack(), "secondComponent", "thirdComponent"); // +
		
		name2overlapCreators.computeIfAbsent(createImportName, (x) -> new LinkedList<>()).add(violationOverlap1);
		name2overlapCreators.computeIfAbsent(transitiveImportMissingFrontName, (x) -> new LinkedList<>()).add(violationOverlap1);
		name2overlapCreators.computeIfAbsent(transitiveImportMissingBackName, (x) -> new LinkedList<>()).add(violationOverlap1);
		
		
		// create repairs
		var repairOverlap1 = new OverlapCreator();

		repairOverlap1.registerOverlap(api.createImport(), "component", "otherComponent");
		repairOverlap1.registerOverlap(api.transitiveImportCreated(), "firstComponent", "thirdComponent"); // +
		
		name2overlapCreators.computeIfAbsent(createImportName, (x) -> new LinkedList<>()).add(repairOverlap1);
		name2overlapCreators.computeIfAbsent(transitiveImportCreatedName, (x) -> new LinkedList<>()).add(repairOverlap1);

		var violationOverlaps = new LinkedList<OverlapCreator>();
		violationOverlaps.add(violationOverlap1);

		var repairOverlaps = new LinkedList<OverlapCreator>();
		repairOverlaps.add(repairOverlap1);
		
		constraintCounter = new ConstraintCounter(violationOverlaps, repairOverlaps);
	}

	@Override
	protected void createAPI() {
		api = new MovingHiPEGtApi();
	}
	
	@Override
	protected void initializeMetamodel() {
		SoftwareSystemPackage.eINSTANCE.getName();
	}

}
