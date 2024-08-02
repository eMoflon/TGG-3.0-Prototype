package org.emoflon.ac.run.architecture;

import java.util.LinkedList;

import org.eclipse.emf.ecore.EObject;
import org.emoflon.ac.run.architecture.config.ArchitectureUtil;
import org.emoflon.ac.run.architecture.config.MoveTTCFeatures;
import org.emoflon.logging.LoggingConfig;

import architecture.util.CRAIndexCalculator;
import architectureCRA.ClassModel;

/**
 * 
 * @author Lars Fritsche
 *
 * Implementation of our running example 
 * 
 */
public class ImproveCRARepeately {
	
	public static final int iterations = 2000;
	public static final int runs = 100;
	public static final int optimizeThreshold = 5;

	
	public static void main(String[] args) {
		
		System.out.println("Running Application Conditions...");

		LoggingConfig.activateLogging = false;
		LoggingConfig.useFormatter = false;
		
		var craIndices = new LinkedList<Double>();
		var times = new LinkedList<Double>();
		
		var modelName = "architecture/TTC_InputRDG_C.xmi";

		// this is only used for calculating the initial CRA-Index
//		var initConfig = new MoveTTCFeatures(modelName);
//		ArchitectureUtil.preProcess(initConfig.getApi().getModel().getResources().get(0));
//		EObject classModel = initConfig.getApi().getModel().getResources().get(0).getContents().get(0);
//		System.out.println("Initial CRA-Index: " + CRAIndexCalculator.calculateCRAIndex((ClassModel) classModel));
//		initConfig.getApi().terminate();
		
		for(int r=0; r < runs; r++) {	
			
			var config = new MoveTTCFeatures(modelName);
			
			ArchitectureUtil.preProcess(config.getApi().getModel().getResources().get(0));
			
			long tic = System.nanoTime();
			config.getApi().updateMatches();
			
			for(var i=0; i < iterations; i++) {
				if(!config.performOneStep(1 - Math.min((double) optimizeThreshold, (double) i) / (double) optimizeThreshold))
					break;
				
				if(i % 10 == 3)
					ArchitectureUtil.postProcess(config.getApi().getModel().getResources().get(0), true);
				
				config.getApi().updateMatches();
			}
			
			long toc = System.nanoTime();
			ArchitectureUtil.postProcess(config.getApi().getModel().getResources().get(0), false);
			
			craIndices.add(CRAIndexCalculator.calculateCRAIndex((ClassModel) config.getApi().getModel().getResources().get(0).getContents().get(0)));
			times.add((double) (toc - tic) / (double) (1000 * 1000 * 1000));
			
			config.getApi().terminate();
			
			if(r % 10 == 9) {
				System.out.println("   " + (int) ((double) (r+1) / (double) (runs-1) * 100.0) + "% finished");
			}
		}
		
		System.out.println("....Finished");
		System.out.println("\n\n");
		System.out.println("-------------------RESULTS----------------------");
		System.out.println("Number of runs: " + runs);
		System.out.println("Best CRA-Index: " + craIndices.stream().max(Double::compare).get());
		System.out.println("Average CRA-Index: " + craIndices.stream().reduce((a, b) -> a + b).get() / craIndices.size());
		System.out.println("Average Time of a Run: " + times.stream().reduce((a, b) -> a + b).get() / times.size());
	}
}
