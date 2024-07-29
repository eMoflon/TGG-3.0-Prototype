package org.emoflon.ac.run.classdiagram;

import org.emoflon.ac.run.classdiagram.config.MoveFeatures;
import org.emoflon.logging.LoggingConfig;

/**
 * 
 * @author Lars Fritsche
 *
 * Implementation of our running example 
 */
public class TestMoveFeatures {
	
	public static final int iterations = 200;

	public static void main(String[] args) {
		System.out.println("Running Application Conditions...");

		LoggingConfig.activateLogging = true;
		LoggingConfig.useFormatter = true;
		
		var config = new MoveFeatures("classDiagram/Example_small.xmi");
		
		long tic = System.nanoTime();
		var constraintCounter = config.getConstraintCounter();
		config.getApi().updateMatches();
		constraintCounter.printAll();
		System.out.println("Step 0; " + (double) (System.nanoTime() - tic) / (double) (1000 * 1000 * 1000) + "s");

		
		for(var i=0; i < iterations; i++) {
			long stepTic = System.nanoTime();
			if(!config.performOneStep())
				break;
			config.getApi().updateMatches();
			System.out.println("Step " + (i+1) + "; " + (double) (System.nanoTime() - stepTic) / (double) (1000 * 1000 * 1000) + "s");
		}

		long toc = System.nanoTime();
		constraintCounter.printAll();
		System.out.println("Execution took: " + (double) (toc - tic) / (double) (1000 * 1000 * 1000) + "s");
		
		config.getApi().terminate();
	}
}
