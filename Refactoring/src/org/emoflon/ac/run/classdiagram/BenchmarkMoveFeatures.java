package org.emoflon.ac.run.classdiagram;

import java.util.LinkedList;

import org.eclipse.emf.ecore.resource.Resource;
import org.emoflon.refactoring.logging.LoggingConfig;

import classDiagram.Attribute;
import classDiagram.ClassDiagramFactory;
import classDiagram.ClazzModel;
import classDiagram.Method;
import hipe.generic.actor.junction.util.HiPEConfig;

public class BenchmarkMoveFeatures {
	
	public static final int iterations = 10;
	
	public static void main(String[] args) {
		LoggingConfig.activateLogging = false;
		LoggingConfig.useFormatter = true;
//		HiPEConfig.logWorkloadActivated = true;

		MoveFeatures config;
		try {
			int modelSize = Integer.parseInt(args[0]);
//			int modelSize = 40;
			config = new MoveFeatures("classDiagram/Example_empty.xmi") {
				
				@Override
				public boolean performOneStep() {
					var nextRankedMatch = constraintCounter.getNextMatch(true);
					if(nextRankedMatch == null) {
						return false;
					}
					var nextMatch = nextRankedMatch.match();
					
					for(var rule : rules) {
						if(rule.patternName.equals(nextMatch.getPatternName())) {
							LoggingConfig.logln("Applying Match:", nextMatch);
							rule.apply(nextMatch);
							return true;
						}
					}
					return false;
				}
			};
			ModelGeneratorUtil.generateModel(config.getApi().getModel().getResources().get(0), modelSize);
		}
		catch(IndexOutOfBoundsException e){
			config = new MoveFeatures("classDiagram/Example_small.xmi");
		}
		
		
		long tic = System.nanoTime();
		var constraintCounter = config.getConstraintCounter();
		config.getApi().updateMatches();
		
		var toc = System.nanoTime();
		
		long tic2 = System.nanoTime();
		for(var i=0; i < iterations; i++) {
			config.getApi().getGTEngine().getPatternMatcher().clearMatches();
			config.performOneStep();
			config.getApi().updateMatches();
		}
		
		long toc2 = System.nanoTime();

		double initialisationTime = (double) (toc - tic) / (double) (1000 * 1000 * 1000);
		double performingStepsTime =  (double) (toc2 - tic2) / (double) (1000 * 1000 * 1000);
		double totalExecutionTime =  (double) (toc2 - tic) / (double) (1000 * 1000 * 1000);
		int modelSize = ModelGeneratorUtil.countModelElements(config);
		int ruleMatchCount = config.getConstraintCounter().countRuleMatches();
		int patternMatchCount = config.getConstraintCounter().countPatternMatches();

		config.getApi().terminate();

		System.out.println(initialisationTime + ";" + performingStepsTime + ";" + totalExecutionTime + ";" + modelSize + ";" + iterations + ";" + ruleMatchCount + ";" + patternMatchCount);
	}

	
}
