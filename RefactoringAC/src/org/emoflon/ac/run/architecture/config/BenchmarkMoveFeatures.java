package org.emoflon.ac.run.architecture.config;

import org.emoflon.logging.LoggingConfig;

import hipe.generic.actor.junction.util.HiPEConfig;

/**
 * 
 * @author Lars Fritsche
 *
 *         Given a model size, this class measures the initialisation time of
 *         our approach for a generated model and measures the time it takes to
 *         perform ten repair steps.
 */
public class BenchmarkMoveFeatures {

	public static final int iterations = 10;

	public static void main(String[] args) {
		LoggingConfig.activateLogging = false;
		LoggingConfig.useFormatter = true;
		HiPEConfig.logWorkloadActivated = true;

		MoveTTCFeatures config;
		try {
//			int modelSize = Integer.parseInt(args[0]);
			int modelSize = 60;
			config = new MoveTTCFeatures("classDiagram/Example_empty.xmi") {

				@Override
				public boolean performOneStep() {
					var nextRankedMatch = constraintCounter.getNextMatch(true);
					if (nextRankedMatch == null) {
						return false;
					}
					var nextMatch = nextRankedMatch.match();

					for (var rule : rules) {
						if (rule.patternName.equals(nextMatch.getPatternName())) {
							LoggingConfig.logln("Applying Match:", nextMatch);
							rule.apply(nextMatch);
							return true;
						}
					}
					return false;
				}
			};
			// we currently do not generate 
//			ModelGeneratorUtil.generateModel(config.getApi().getModel().getResources().get(0), modelSize);
			
			ArchitectureUtil.preProcess(config.getApi().getModel().getResources().get(0));
			
		} catch (IndexOutOfBoundsException e) {
			config = new MoveTTCFeatures("classDiagram/Example_small.xmi");
		}

		long tic = System.nanoTime();
		var constraintCounter = config.getConstraintCounter();
		config.getApi().updateMatches();

		var toc = System.nanoTime();

		long tic2 = System.nanoTime();
		for (var i = 0; i < iterations; i++) {
			config.getApi().getGTEngine().getPatternMatcher().clearMatches();
			config.performOneStep();
			config.getApi().updateMatches();
		}

		long toc2 = System.nanoTime();

		double initialisationTime = (double) (toc - tic) / (double) (1000 * 1000 * 1000);
		double performingStepsTime = (double) (toc2 - tic2) / (double) (1000 * 1000 * 1000);
		double totalExecutionTime = (double) (toc2 - tic) / (double) (1000 * 1000 * 1000);
		int modelSize = ArchitectureUtil.countModelElements(config.getApi().getModel().getResources().get(0));
		int ruleMatchCount = config.getConstraintCounter().countRuleMatches();
		int patternMatchCount = config.getConstraintCounter().countPatternMatches();

		ArchitectureUtil.postProcess(config.getApi().getModel().getResources().get(0));

		config.getApi().terminate();

		System.out.println(initialisationTime + ";" + performingStepsTime + ";" + totalExecutionTime + ";" + modelSize
				+ ";" + iterations + ";" + ruleMatchCount + ";" + patternMatchCount);
	}

}
