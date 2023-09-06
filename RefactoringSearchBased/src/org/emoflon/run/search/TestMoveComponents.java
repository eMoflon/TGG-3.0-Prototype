package org.emoflon.run.search;

import org.emoflon.refactoring.logging.LoggingConfig;
import org.emoflon.run.search.scheduling.MoveComponents;

import hipe.generic.actor.junction.util.HiPEConfig;

public class TestMoveComponents {
	
	public static void main(String[] args) {
		LoggingConfig.activateLogging = true;
		LoggingConfig.useFormatter = true;
//		HiPEConfig.logWorkloadActivated = true;
//		LoggingConfig.matchSubStringsToLog.add("TGGMatch");
//		LoggingConfig.matchSubStringsToLog.add("GTPatternMatch");
		
//		var config = new MoveComponents("TestSystem1.xmi", 8);
		var config = new MoveComponents("TestSystem_large3.xmi", 16);
		
		long tic = System.nanoTime();
		config.update();
		config.printAll();
			
		config.performOneStep();
		config.update();
		config.printAll();
		
		long toc = System.nanoTime();
//		config.printConstraintMatchCount();
		System.out.println("Execution took: " + (double) (toc - tic) / (double) (1000 * 1000 * 1000) + "s");
			
		config.terminate();
	}

}
