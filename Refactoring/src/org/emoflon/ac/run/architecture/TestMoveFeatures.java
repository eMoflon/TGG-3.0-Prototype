package org.emoflon.ac.run.architecture;

import org.emoflon.ac.run.architecture.config.ArchitectureUtil;
import org.emoflon.ac.run.architecture.config.MoveTTCFeatures;
import org.emoflon.logging.LoggingConfig;

import architecture.util.CRAIndexCalculator;
import architectureCRA.ClassModel;

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
		
		var config = new MoveTTCFeatures("architecture/TTC_InputRDG_B.xmi");
		ArchitectureUtil.preProcess(config.getApi().getModel().getResources().get(0));
		CRAIndexCalculator.evaluateModel((ClassModel) config.getApi().getModel().getResources().get(0).getContents().get(0));
		CRAIndexCalculator.printGeneralInfo((ClassModel) config.getApi().getModel().getResources().get(0).getContents().get(0));
		
		long tic = System.nanoTime();
		var constraintCounter = config.getConstraintCounter();
		config.getApi().updateMatches();
		constraintCounter.printAll();
		System.out.println("Step 0; " + (double) (System.nanoTime() - tic) / (double) (1000 * 1000 * 1000) + "s");

		
		for(var i=0; i < iterations; i++) {
			long stepTic = System.nanoTime();
			if(!config.performOneStep())
				break;

//			if(i % 10 == 9)
//				ArchitectureUtil.postProcess(config.getApi().getModel().getResources().get(0));

			config.getApi().updateMatches();
			System.out.println("Step " + (i+1) + "; " + (double) (System.nanoTime() - stepTic) / (double) (1000 * 1000 * 1000) + "s");
		}

		long toc = System.nanoTime();
		constraintCounter.printAll();
		ArchitectureUtil.postProcess(config.getApi().getModel().getResources().get(0));
		CRAIndexCalculator.evaluateModel((ClassModel) config.getApi().getModel().getResources().get(0).getContents().get(0));
		CRAIndexCalculator.printGeneralInfo((ClassModel) config.getApi().getModel().getResources().get(0).getContents().get(0));

		System.out.println("Execution took: " + (double) (toc - tic) / (double) (1000 * 1000 * 1000) + "s");
		
		config.getApi().terminate();
	}
}
