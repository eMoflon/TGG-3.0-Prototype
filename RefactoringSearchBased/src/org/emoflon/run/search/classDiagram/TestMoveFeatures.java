package org.emoflon.run.search.classDiagram;

import org.emoflon.logging.LoggingConfig;

public class TestMoveFeatures {

	public static final int iterations = 10;
	
	public static final int MODEL_SIZE = 40;
	
	public static void main(String[] args) {
		System.out.println("Running Search Based...");
//		LoggingConfig.activateLogging = true;
		LoggingConfig.useFormatter = true;
//		HiPEConfig.logWorkloadActivated = true;
//		LoggingConfig.matchSubStringsToLog.add("TGGMatch");
//		LoggingConfig.matchSubStringsToLog.add("GTPatternMatch");
		
//		var config = new MoveFeaturesCoordinator("classDiagram/Example_small.xmi", 8);
		var config = new MoveFeaturesCoordinator("classDiagram/Example_empty.xmi", 16);
		ModelGeneratorUtil.generateModel(config.getRuleMatchers().iterator().next().getApi().getModel().getResources().get(0), MODEL_SIZE);

		
		long tic = System.nanoTime();
		config.update();
		System.out.println(config.getRuleMatches().size() + " matches found") ;
//		config.printAll();
		System.out.println("Step 0; " + (double) (System.nanoTime() - tic) / (double) (1000 * 1000 * 1000) + "s");
		
		for(int i=0; i < iterations; i++) {
			long stepTic = System.nanoTime();
			config.performOneStep();
			config.update();
			System.out.println("Step " + (i+1) + "; " + (double) (System.nanoTime() - stepTic) / (double) (1000 * 1000 * 1000) + "s");
//			config.printAll();
		}
		
		config.performOneStep();
		config.update();
		
		long toc = System.nanoTime();
//		config.printConstraintMatchCount();
		System.out.println("Execution took: " + (double) (toc - tic) / (double) (1000 * 1000 * 1000) + "s");
			
		config.terminate();
	}

}
