package org.emoflon.ac.run.classdiagram;

import java.util.LinkedList;

import org.eclipse.emf.ecore.resource.Resource;
import org.emoflon.refactoring.logging.LoggingConfig;

import classDiagram.Attribute;
import classDiagram.ClassDiagramFactory;
import classDiagram.ClazzModel;
import classDiagram.Method;

public class BenchmarkMoveFeatures {
	
	public static final int iterations = 10;
	
	protected static final int NUM_OF_CLASSES = 5;

	protected static final int NUM_OF_METHODS = 5;

	protected static final int NUM_OF_ATTRIBUTES = 5;

	protected static final int NUM_OF_INTERNAL_DEPENDENCIES = 3;

	protected static final int NUM_OF_EXTERNAL_DEPENDENCIES = 3;

	public static void main(String[] args) {
		LoggingConfig.activateLogging = false;
		LoggingConfig.useFormatter = true;
		
		MoveFeatures config;
		try {
			int modelSize = Integer.parseInt(args[0]);
//			int modelSize = 5;
			config = new MoveFeatures("classDiagram/Example_empty.xmi") {
				
				@Override
				public void performOneStep() {
					var nextMatch = constraintCounter.getNextMatch(true);
					if(nextMatch == null) {
						return;
					}
					
					for(var rule : rules) {
						if(rule.patternName.equals(nextMatch.getPatternName())) {
							LoggingConfig.logln("Applying Match:", nextMatch);
							rule.apply(nextMatch);
							break;
						}
					}
				}
			};
			generateModel(config.getApi().getModel().getResources().get(0), modelSize);
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
			config.performOneStep();
			config.getApi().updateMatches();
		}
		
		long toc2 = System.nanoTime();

		double initialisationTime = (double) (toc - tic) / (double) (1000 * 1000 * 1000);
		double performingStepsTime =  (double) (toc2 - tic2) / (double) (1000 * 1000 * 1000);
		double totalExecutionTime =  (double) (toc2 - tic) / (double) (1000 * 1000 * 1000);
		int modelSize = countModelElements(config);
		int ruleMatchCount = config.getConstraintCounter().countRuleMatches();
		int patternMatchCount = config.getConstraintCounter().countPatternMatches();
		
		config.getApi().terminate();

		System.out.println(initialisationTime + ";" + performingStepsTime + ";" + totalExecutionTime + ";" + modelSize + ";" + iterations + ";" + ruleMatchCount + ";" + patternMatchCount);
	}

	private static int countModelElements(MoveFeatures config) {
		int count = 0;
		for(var resource : config.getApi().getModel().getResources()) {
			var iterator = resource.getAllContents();
			while(iterator.hasNext()) {
				iterator.next();
				count++;
			}
		}
		return count;
	}
	
	private static void generateModel(Resource r, int modelSize) {
		ClassDiagramFactory factory = ClassDiagramFactory.eINSTANCE;

		ClazzModel clazzModel = (ClazzModel) r.getContents().get(0);;
		
		var allMethods = new LinkedList<Method>();
		var allAttributes = new LinkedList<Attribute>();
		
		for(int i=0;i<modelSize;i++) {
			for(int c=0; c<NUM_OF_CLASSES; c++) {
				var clazz = factory.createClazz();
				clazz.setName(i + "_" + c);
				clazzModel.getClazzes().add(clazz);

				var methods = new LinkedList<Method>();
				var attributes = new LinkedList<Attribute>();
				
				for(int a=0; a<NUM_OF_ATTRIBUTES; a++) {
					var attribute = factory.createAttribute();
					attribute.setName(i + "_" + a);
					attributes.add(attribute);
					clazz.getFeatures().add(attribute);
				}
				
				for(int m=0; m<NUM_OF_METHODS; m++) {
					var method = factory.createMethod();
					method.setName(i + "_" + m);
					methods.add(method);
					clazz.getFeatures().add(method);
				}
				
				for(int m =0; m < methods.size(); m++) {
					var method = methods.get(m);
					for(int d=0; d<NUM_OF_INTERNAL_DEPENDENCIES; d++) {
						method.getDependencies().add(attributes.get(((m+d) * (attributes.size() + 1)) % attributes.size()));
					}
				}
				
				allMethods.addAll(methods);
				allAttributes.addAll(attributes);
			}
		}
		
		for(var m=0; m< allMethods.size(); m++) {
			var method = allMethods.get(m);
			for(int d=0; d<NUM_OF_EXTERNAL_DEPENDENCIES; d++) {
				int a = (m*3+d) % allAttributes.size();
				Attribute attribute = null;
				
				// if both containers are equal, then both features are within the same class and we have to continue
				do {
					attribute = allAttributes.get(a);
					a=(a+1) % allAttributes.size();
				}
				while(attribute.eContainer().equals(method.eContainer()));
				
				method.getDependencies().add(attribute);
			}
		}
	}
}
