package org.emoflon.ac.run.classdiagram;

import org.emoflon.refactoring.logging.LoggingConfig;

import hipe.generic.actor.junction.util.HiPEConfig;

public class TestMoveFeatures {
	
	public static final int iterations = 200;

	public static void main(String[] args) {
		System.out.println("Running Application Conditions...");

		LoggingConfig.activateLogging = true;
		LoggingConfig.useFormatter = true;
//		HiPEConfig.logWorkloadActivated = true;
//		LoggingConfig.matchSubStringsToLog.add("TGGMatch");
//		LoggingConfig.matchSubStringsToLog.add("GTPatternMatch");
		
		var config = new MoveFeatures("classDiagram/Example_small.xmi");
//		var config = new MoveComponents("TestSystem_large3.xmi");
		
		long tic = System.nanoTime();
		var constraintCounter = config.getConstraintCounter();
		config.getApi().updateMatches();
		constraintCounter.printAll();
		System.out.println("Step 0; " + (double) (System.nanoTime() - tic) / (double) (1000 * 1000 * 1000) + "s");

		
		for(var i=0; i < iterations; i++) {
			long stepTic = System.nanoTime();
			config.performOneStep();
			config.getApi().updateMatches();
//			constraintCounter.printAll();
			System.out.println("Step " + (i+1) + "; " + (double) (System.nanoTime() - stepTic) / (double) (1000 * 1000 * 1000) + "s");
		}

		long toc = System.nanoTime();
		constraintCounter.printAll();
		System.out.println("Execution took: " + (double) (toc - tic) / (double) (1000 * 1000 * 1000) + "s");
		
		config.getApi().terminate();
	}
}
