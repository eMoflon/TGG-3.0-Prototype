package org.emoflon.ac.run.softwareSystem;

import org.emoflon.refactoring.logging.LoggingConfig;

public class TestCreateImports {
	
	public static void main(String[] args) {
		LoggingConfig.activateLogging = true;
		LoggingConfig.useFormatter = true;
		LoggingConfig.matchSubStringsToLog.add("TGGMatch");
//		LoggingConfig.matchSubStringsToLog.add("GTPatternMatch");
		
		var config = new TransitiveImport("TestSystem2.xmi");
		var constraintCounter = config.getConstraintCounter();
		
		long tic = System.nanoTime();
		config.getApi().updateMatches();
		constraintCounter.printAll();
		
		config.performOneStep();
		
		config.getApi().updateMatches();
		constraintCounter.printAll();
		
		long toc = System.nanoTime();
		System.out.println("Execution took: " + (double) (toc - tic) / (double) (1000 * 1000 * 1000) + "s");
		
		config.getApi().terminate();
	}

}
