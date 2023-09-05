package org.emoflon.run.search;

import org.emoflon.refactoring.logging.LoggingConfig;
import org.emoflon.run.search.scheduling.MoveComponents;

public class TestMoveComponents {
	
	public static void main(String[] args) {
		LoggingConfig.activateLogging = true;
		LoggingConfig.useFormatter = true;
//		LoggingConfig.matchSubStringsToLog.add("TGGMatch");
//		LoggingConfig.matchSubStringsToLog.add("GTPatternMatch");
		
		var config = new MoveComponents("TestSystem1.xmi", 8);
		
		long tic = System.nanoTime();
		config.update();
		config.printAll();
			
		config.performOneStep();
		config.update();
		config.printAll();
		
		long toc = System.nanoTime();
		System.out.println("Execution took: " + (double) (toc - tic) / (double) (1000 * 1000 * 1000) + "s");
			
		config.terminate();
	}

}
