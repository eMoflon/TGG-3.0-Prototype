package org.emoflon.ac.run.architecture.config;

import java.util.LinkedList;

import org.emoflon.ac.RefactoringCase;
import org.emoflon.ac.analysis.ConstraintCounter;
import org.emoflon.ac.analysis.OverlapCreator;

import architectureCRA.ArchitectureCRAPackage;
import refactoringcd.full.ttc.api.TtcHiPEGtApi;
import softwareSystem.SoftwareSystemPackage;

/**
 * 
 * @author Lars Fritsche
 *
 *         This class manages the configuration of rule and repair/violation
 *         indicating condition patterns and how they overlap
 */
public class MoveTTCFeatures extends RefactoringCase<TtcHiPEGtApi> {

	public MoveTTCFeatures(String path) {
		super(path);
	}

	public MoveTTCFeatures() {
		super(null);
	}

	@Override
	protected void createAndRegisterOverlaps() {
		rules.add(api.moveMethod());
		rules.add(api.moveAttribute());

		// W1

		// used for moveMethod and moveAttribute
		violations.add(api.intactMethodAttributeDependency());

		// used for moveMethod and moveAttribute
		repairs.add(api.movingMethodWithAttributeDependencyTogether());

		// create violations
		var moveMethodW1Violation = new OverlapCreator();
		var moveAttributeW1Violation = new OverlapCreator();

		moveMethodW1Violation.registerOverlap(api.moveMethod(), "fromClazz", "toClazz", "method");
		moveMethodW1Violation.registerOverlap(api.intactMethodAttributeDependency(), "fromClazz", "toClazz", "method");

		moveAttributeW1Violation.registerOverlap(api.moveAttribute(), "fromClazz", "toClazz", "attribute");
		moveAttributeW1Violation.registerOverlap(api.intactMethodAttributeDependency(), "fromClazz", "toClazz",
				"attribute");

		// create repairs
		var moveMethodW1Repair = new OverlapCreator();
		var moveAttributeW1Repair = new OverlapCreator();

		moveMethodW1Repair.registerOverlap(api.moveMethod(), "fromClazz", "toClazz", "method");
		moveMethodW1Repair.registerOverlap(api.movingMethodWithAttributeDependencyTogether(), "fromClazz", "toClazz",
				"method");

		moveAttributeW1Repair.registerOverlap(api.moveAttribute(), "fromClazz", "toClazz", "attribute");
		moveAttributeW1Repair.registerOverlap(api.movingMethodWithAttributeDependencyTogether(), "toClazz", "fromClazz",
				"attribute");

		// W2
		violations.add(api.movingMethodToOtherMethodWithoutCommonDependency());
		violations.add(api.moveLastCommonAttributeToOtherClass());

		repairs.add(api.movingMethodToOtherMethodWithCommonDependency());
		repairs.add(api.moveFirstCommonAttributeToOtherClass());

		// create violations
		var moveMethodW2Violation = new OverlapCreator();
		var moveAttributeW2Violation = new OverlapCreator();

		moveMethodW2Violation.registerOverlap(api.moveMethod(), "fromClazz", "toClazz", "method");
		moveMethodW2Violation.registerOverlap(api.movingMethodToOtherMethodWithoutCommonDependency(), "fromClazz",
				"toClazz", "fromMethod");

		moveAttributeW2Violation.registerOverlap(api.moveAttribute(), "fromClazz", "toClazz", "attribute");
		moveAttributeW2Violation.registerOverlap(api.moveLastCommonAttributeToOtherClass(), "fromClazz", "toClazz",
				"attribute");

		// create repairs
		var moveMethodW2Repair = new OverlapCreator();
		var moveAttributeW2Repair = new OverlapCreator();

		moveMethodW2Repair.registerOverlap(api.moveMethod(), "fromClazz", "toClazz", "method");
		moveMethodW2Repair.registerOverlap(api.movingMethodToOtherMethodWithCommonDependency(), "fromClazz", "toClazz", "toMethod");

		moveAttributeW2Repair.registerOverlap(api.moveAttribute(), "fromClazz", "toClazz", "attribute");
		moveAttributeW2Repair.registerOverlap(api.moveFirstCommonAttributeToOtherClass(), "fromClazz", "toClazz", "attribute");
		
		// W3
		violations.add(api.intactMethodPair());

		repairs.add(api.methodDependencyBetweenClasses());
		
		// create violations
		var moveMethodW3Violation = new OverlapCreator();
		moveMethodW3Violation.registerOverlap(api.moveMethod(), "fromClazz", "toClazz", "method");
		moveMethodW3Violation.registerOverlap(api.intactMethodPair(), "fromClazz", "toClazz", "toMethod");
		
		var moveMethodW3Violation2 = new OverlapCreator();
		moveMethodW3Violation2.registerOverlap(api.moveMethod(), "fromClazz", "toClazz", "method");
		moveMethodW3Violation2.registerOverlap(api.intactMethodPair(), "fromClazz", "toClazz", "fromMethod");

		// create repairs
		var moveMethodW3Repair = new OverlapCreator();
		moveMethodW3Repair.registerOverlap(api.moveMethod(), "fromClazz", "toClazz", "method");
		moveMethodW3Repair.registerOverlap(api.methodDependencyBetweenClasses(), "fromClazz", "toClazz", "fromMethod");

		var moveMethodW3Repair2 = new OverlapCreator();
		moveMethodW3Repair2.registerOverlap(api.moveMethod(), "fromClazz", "toClazz", "method");
		moveMethodW3Repair2.registerOverlap(api.methodDependencyBetweenClasses(), "toClazz", "fromClazz", "toMethod");
		
		// W4
		violations.add(api.noMethodDependencyBetweenClasses());

		repairs.add(api.methodDependencyInClass());
		
		// create violations
		var moveMethodW4Violation = new OverlapCreator();
		moveMethodW4Violation.registerOverlap(api.moveMethod(), "fromClazz", "toClazz", "method");
		moveMethodW4Violation.registerOverlap(api.noMethodDependencyBetweenClasses(), "fromClazz", "toClazz", "fromMethod");

		// create repairs
		var moveMethodW4Repair = new OverlapCreator();
		moveMethodW4Repair.registerOverlap(api.moveMethod(), "fromClazz", "toClazz", "method");
		moveMethodW4Repair.registerOverlap(api.methodDependencyInClass(), "fromClazz", "toClazz", "fromMethod");

		var violations = new LinkedList<OverlapCreator>();
		violations.add(moveMethodW1Violation);

		violations.add(moveAttributeW1Violation);

		violations.add(moveMethodW2Violation);
		violations.add(moveMethodW2Violation);

		violations.add(moveAttributeW2Violation);
		
		violations.add(moveMethodW3Violation);
		violations.add(moveMethodW3Violation2);

		violations.add(moveMethodW4Violation);

		var repairs = new LinkedList<OverlapCreator>();
		repairs.add(moveMethodW1Repair);

		repairs.add(moveAttributeW1Repair);

		repairs.add(moveMethodW2Repair);
		repairs.add(moveMethodW2Repair);

		repairs.add(moveAttributeW2Repair);

		repairs.add(moveMethodW3Repair);
		repairs.add(moveMethodW3Repair2);

		repairs.add(moveMethodW4Repair);
		
		constraintCounter = new ConstraintCounter(violations, repairs);
	}

	@Override
	protected void createAPI() {
		api = new TtcHiPEGtApi();
	}

	@Override
	protected void initializeMetamodel() {
		ArchitectureCRAPackage.eINSTANCE.getName();
	}

}
