package org.emoflon.ac.run.classdiagram.config;

import java.util.LinkedList;

import org.emoflon.ac.RefactoringCase;
import org.emoflon.ac.analysis.ConstraintCounter;
import org.emoflon.ac.analysis.OverlapCreator;

import refactoringcd.full.api.FullHiPEGtApi;
import softwareSystem.SoftwareSystemPackage;

/**
 * 
 * @author Lars Fritsche
 *
 *         This class manages the configuration of rule and repair/violation
 *         indicating condition patterns and how they overlap
 */
public class MoveFeatures extends RefactoringCase<FullHiPEGtApi> {

	public MoveFeatures(String path) {
		super(path);
	}

	public MoveFeatures() {
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
		moveMethodW2Repair.registerOverlap(api.movingMethodToOtherMethodWithCommonDependency(), "fromClazz", "toClazz",
				"toMethod");

		moveAttributeW2Repair.registerOverlap(api.moveAttribute(), "fromClazz", "toClazz", "attribute");
		moveAttributeW2Repair.registerOverlap(api.moveFirstCommonAttributeToOtherClass(), "fromClazz", "toClazz",
				"attribute");

		var violations = new LinkedList<OverlapCreator>();
		violations.add(moveMethodW1Violation);

		violations.add(moveAttributeW1Violation);

		violations.add(moveMethodW2Violation);
		violations.add(moveMethodW2Violation);

		violations.add(moveAttributeW2Violation);

		var repairs = new LinkedList<OverlapCreator>();
		repairs.add(moveMethodW1Repair);

		repairs.add(moveAttributeW1Repair);

		repairs.add(moveMethodW2Repair);
		repairs.add(moveMethodW2Repair);

		repairs.add(moveAttributeW2Repair);

		constraintCounter = new ConstraintCounter(violations, repairs);
	}

	@Override
	protected void createAPI() {
		api = new FullHiPEGtApi();
	}

	@Override
	protected void initializeMetamodel() {
		SoftwareSystemPackage.eINSTANCE.getName();
	}

}
