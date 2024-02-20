package org.emoflon.run.search.softwareSystem;

import org.emoflon.refactoring.logging.LoggingConfig;

public class TestMoveComponents {

	public static final int iterations = 5;
	
	public static void main(String[] args) {
		System.out.println("Running Search Based...");
//		LoggingConfig.activateLogging = true;
		LoggingConfig.useFormatter = true;
//		HiPEConfig.logWorkloadActivated = true;
//		LoggingConfig.matchSubStringsToLog.add("TGGMatch");
//		LoggingConfig.matchSubStringsToLog.add("GTPatternMatch");
		
//		var config = new MoveComponents("TestSystem1.xmi", 8);
		var config = new MoveComponentsCoordinator("TestSystem_large3.xmi", 8);
		
		long tic = System.nanoTime();
		config.update();
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
