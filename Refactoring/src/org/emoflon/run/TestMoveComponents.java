package org.emoflon.run;

import org.emoflon.refactoring.MoveComponents;
import org.emoflon.refactoring.logging.LoggingConfig;

public class TestMoveComponents {
	
	public static void main(String[] args) {
		LoggingConfig.activateLogging = true;
		LoggingConfig.useFormatter = false;
//		LoggingConfig.matchSubStringsToLog.add("TGGMatch");
//		LoggingConfig.matchSubStringsToLog.add("GTPatternMatch");
		
		var config = new MoveComponents("TestSystem1.xmi");
		var constraintCounter = config.getConstraintCounter();
		config.getApi().updateMatches();
		
		constraintCounter.printAll();
		
		config.getApi().terminate();
	}

}
