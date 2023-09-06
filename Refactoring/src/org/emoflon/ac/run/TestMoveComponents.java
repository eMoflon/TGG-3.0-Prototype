package org.emoflon.ac.run;

import org.emoflon.refactoring.ac.MoveComponents;
import org.emoflon.refactoring.logging.LoggingConfig;

import hipe.generic.actor.junction.util.HiPEConfig;

public class TestMoveComponents {
	
	public static void main(String[] args) {
		LoggingConfig.activateLogging = true;
		LoggingConfig.useFormatter = true;
		HiPEConfig.logWorkloadActivated = true;
//		LoggingConfig.matchSubStringsToLog.add("TGGMatch");
//		LoggingConfig.matchSubStringsToLog.add("GTPatternMatch");
		
//		var config = new MoveComponents("TestSystem1.xmi");
		var config = new MoveComponents("TestSystem_large3.xmi");
		
		long tic = System.nanoTime();
		var constraintCounter = config.getConstraintCounter();
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
