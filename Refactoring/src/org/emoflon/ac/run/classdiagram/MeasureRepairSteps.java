package org.emoflon.ac.run.classdiagram;

import org.emoflon.ac.run.classdiagram.config.ModelGeneratorUtil;
import org.emoflon.ac.run.classdiagram.config.MoveFeatures;
import org.emoflon.refactoring.logging.LoggingConfig;

/**
 * 
 * @author Lars Fritsche
 *
 *         This class executes a sequences of repair steps on a generated model
 *         and terminates when no rule repairs more than it would violate. It
 *         also prints the accumulated repairs, violations and gains of the
 *         repair steps.
 * 
 */
public class MeasureRepairSteps {

	public static final int MODELSIZE = 40;

	public static void main(String[] args) {
		System.out.println("Running Application Conditions...");

		LoggingConfig.activateLogging = false;
		LoggingConfig.useFormatter = true;

		var config = new MoveFeatures("classDiagram/Example_empty.xmi");
		ModelGeneratorUtil.generateModel(config.getApi().getModel().getResources().get(0), MODELSIZE);

		long tic = System.nanoTime();
		var constraintCounter = config.getConstraintCounter();
		config.getApi().updateMatches();

		System.out.println("iteration;gain;repairs;violations");
		var i = 1;
		while (config.performOneStep()) {
			config.getApi().updateMatches();
			if (i % 10 == 0) {
				var updatedTotalRepairs = constraintCounter.countTotalRepairs();
				var updatedTotalViolations = constraintCounter.countTotalViolations();
				System.out.println(i + ";" + (updatedTotalRepairs - updatedTotalViolations) + ";" + updatedTotalRepairs
						+ ";" + updatedTotalViolations);
			}
			i++;
		}

		System.out.println("Execution stopped after " + i + " iterations");
		System.out.println("Final iteration is: ");
		var updatedTotalRepairs = constraintCounter.countTotalRepairs();
		var updatedTotalViolations = constraintCounter.countTotalViolations();
		System.out.println(i + ";" + (updatedTotalRepairs - updatedTotalViolations) + ";" + updatedTotalRepairs + ";"
				+ updatedTotalViolations);
		System.out.println();

		long toc = System.nanoTime();
		System.out.println("Execution took: " + (double) (toc - tic) / (double) (1000 * 1000 * 1000) + "s");

		config.getApi().terminate();
	}
}
