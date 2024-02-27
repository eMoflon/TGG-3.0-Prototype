package org.emoflon.ac.run.classdiagram;

import org.emoflon.refactoring.logging.LoggingConfig;

public class MeasureRepairSteps {

public static final int MODELSIZE = 10;
	
	public static final int iterations = 50;

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
		var i=1;
		while(config.performOneStep()) {
			config.getApi().updateMatches();
			var updatedTotalRepairs = constraintCounter.countTotalRepairs();
			var updatedTotalViolations = constraintCounter.countTotalViolations();
			if(i % 10 == 0) {
				System.out.println(i + ";" + (updatedTotalRepairs-updatedTotalViolations) + ";" + updatedTotalRepairs + ";" + updatedTotalViolations);
			}
			i++;
		}
		
		System.out.println();
		
		System.out.println();
		


		long toc = System.nanoTime();
//		constraintCounter.printAll();
		System.out.println("Execution took: " + (double) (toc - tic) / (double) (1000 * 1000 * 1000) + "s");
		
		config.getApi().terminate();
	}
}
