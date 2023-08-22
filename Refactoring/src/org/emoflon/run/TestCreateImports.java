package org.emoflon.run;

import org.emoflon.refactoring.TransitiveImport;
import org.emoflon.refactoring.logging.LoggingConfig;

public class TestCreateImports {
	
	public static void main(String[] args) {
		LoggingConfig.activateLogging = true;
		LoggingConfig.useFormatter = false;
		LoggingConfig.matchSubStringsToLog.add("TGGMatch");
//		LoggingConfig.matchSubStringsToLog.add("GTPatternMatch");
		
		var config = new TransitiveImport("TestSystem2.xmi");
		var constraintCounter = config.getConstraintCounter();
		config.getApi().updateMatches();
		
		constraintCounter.printAll();
		
		config.getApi().terminate();
	}

}
