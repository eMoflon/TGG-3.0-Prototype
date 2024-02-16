package org.emoflon.run.search.softwareSystem;

import org.emoflon.refactoring.logging.LoggingConfig;

public class TestCreateImports {
	
	public static void main(String[] args) {
		LoggingConfig.activateLogging = true;
		LoggingConfig.useFormatter = true;
		LoggingConfig.matchSubStringsToLog.add("TGGMatch");
//		LoggingConfig.matchSubStringsToLog.add("GTPatternMatch");
		
//		var config = new TransitiveImport("TestSystem2.xmi");
//		var constraintCounter = config.getConstraintCounter();
//		config.getApi().updateMatches();
//		constraintCounter.printAll();
//		
//		config.performOneStep();
//		
//		config.getApi().updateMatches();
//		constraintCounter.printAll();
//		
//		config.getApi().terminate();
	}

}
