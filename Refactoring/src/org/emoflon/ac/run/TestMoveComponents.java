package org.emoflon.ac.run;

import org.emoflon.refactoring.ac.MoveComponents;
import org.emoflon.refactoring.logging.LoggingConfig;

public class TestMoveComponents {
	
	public static void main(String[] args) {
		LoggingConfig.activateLogging = true;
		LoggingConfig.useFormatter = true;
//		LoggingConfig.matchSubStringsToLog.add("TGGMatch");
//		LoggingConfig.matchSubStringsToLog.add("GTPatternMatch");
		
		var config = new MoveComponents("TestSystem1.xmi");
		var constraintCounter = config.getConstraintCounter();
		config.getApi().updateMatches();
		constraintCounter.printAll();
		
		config.performOneStep();
		config.getApi().updateMatches();
		constraintCounter.printAll();
		
		config.getApi().terminate();
	}

}
