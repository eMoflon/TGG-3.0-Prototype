package org.emoflon.ac.run.classdiagram;

import org.emoflon.refactoring.logging.LoggingConfig;

import hipe.generic.actor.junction.util.HiPEConfig;

public class TestMoveFeatures {
	
	public static final int MODELSIZE = 1;
	
	public static final int iterations = 50;

	public static void main(String[] args) {
		System.out.println("Running Application Conditions...");

		LoggingConfig.activateLogging = true;
		LoggingConfig.useFormatter = true;
		
		var config = new MoveFeatures("classDiagram/Example_small.xmi");
		ModelGeneratorUtil.generateModel(config.getApi().getModel().getResources().get(0), MODELSIZE);

		long tic = System.nanoTime();
		var constraintCounter = config.getConstraintCounter();
		config.getApi().updateMatches();
		
//		var i=1;
//		for(; i <= iterations; i++) {
//			long stepTic = System.nanoTime();
//			config.performOneStep();
//			config.getApi().updateMatches();
//			var updatedTotalRepairs = constraintCounter.countTotalRepairs();
//			var updatedTotalViolations = constraintCounter.countTotalViolations();
//			if(i % 10 == 0) {
//				System.out.println(i + ";" + (updatedTotalRepairs-updatedTotalViolations) + ";" + updatedTotalRepairs + ";" + updatedTotalViolations);
//			}
//		}
		
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
