package org.emoflon.run.search.matching;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.emoflon.ibex.gt.api.IBeXGtAPI;
import org.emoflon.ibex.gt.engine.IBeXGTMatch;
import org.emoflon.ibex.gt.engine.IBeXGTPattern;
import org.emoflon.ibex.gt.engine.IBeXGTRule;
import org.emoflon.ibex.gt.engine.hipe.HiPEPatternMatchingEngine;
import org.emoflon.ibex.gt.gtmodel.IBeXGTModel.GTRule;
import org.emoflon.smartemf.runtime.notification.SmartEMFNotification;

import scala.collection.GenMapLike;

public abstract class ConstraintCheckingInstance<API extends IBeXGtAPI<?,?,?>> {

	protected API api;
	private EContentAdapter matcherContentAdapter;
	
	private Map<String, Collection<Consumer<IBeXGTMatch>>> ruleName2notificationCreators = new HashMap<>();
	private Collection<IBeXGTPattern> constraintPattern = new LinkedList<>();
	
	private Collection<IBeXGTMatch> violations;
	private Collection<IBeXGTMatch> repairs;
	
	public ConstraintCheckingInstance(String modelPath) {
		createAPI();
		loadModel(modelPath);
		initialize();
		reset();
	}
	
	public ConstraintCheckingInstance(ResourceSet model) {
		createAPI();
		api.setModel(model);
		initialize();
		reset();
	}
	
	private void reset() {
		violations = new LinkedList<>();
		repairs = new LinkedList<>();
	}
	
	private void initialize() {
		api.initializeEngine();
		
		// this only works for HiPE
		if(api.getGTEngine().getPatternMatcher() instanceof HiPEPatternMatchingEngine engine) 
			matcherContentAdapter = engine.getAdapter();
		else 
			throw new RuntimeException("Currently, only HiPE is supported as pattern matcher!");
	}

	protected abstract void createAPI();
	
	public Collection<WeightedMatch> evaluate(Collection<IBeXGTMatch> appliedRuleMatchs) {
		var list = new LinkedList<WeightedMatch>();
		for(var match : appliedRuleMatchs) {
			list.add(evaluate(match));
		}
		return list;
	}
	
	public WeightedMatch evaluate(IBeXGTMatch appliedRuleMatch) {
		var notificationCreators = ruleName2notificationCreators.get(appliedRuleMatch.getPatternName());
		if(notificationCreators == null)
			return null;
		
		// first create notifications
		notificationCreators.forEach(c -> c.accept(appliedRuleMatch));
		
		// then let the pattern matcher run. new/vanished matches are automatically registered
		api.updateMatches();
		
		// create the result and wipe both lists
		var result = new WeightedMatch(appliedRuleMatch, violations, repairs);
		reset();
		
		return result;
	}
	
	public void registerRule(IBeXGTRule runtimeRule, GTRule specificationRule) {
		Collection<Consumer<IBeXGTMatch>> consumers = new LinkedList<>();
		for(var deletedSpecNode : specificationRule.getDeletion().getNodes()) {
			consumers.add(m -> {
				var deletedNode = m.get(deletedSpecNode.getName());
				matcherContentAdapter.notifyChanged(SmartEMFNotification.createRemovingAdapterNotification(deletedNode, null, matcherContentAdapter, -1));
			});
		}
		
		for(var deletedSpecEdge : specificationRule.getDeletion().getEdges()) {
			consumers.add(m -> {
				var source = m.get(deletedSpecEdge.getSource().getName());
				var target = m.get(deletedSpecEdge.getTarget().getName());
				var ref = deletedSpecEdge.getType();
				if(ref.isContainer()) {
					var tmp = target;
					target = source;
					source = tmp;
					ref = ref.getEOpposite();
				}
				if(ref.isMany())
					matcherContentAdapter.notifyChanged(SmartEMFNotification.createRemoveNotification(source, ref, target, -1));
				else
					matcherContentAdapter.notifyChanged(SmartEMFNotification.createSetNotification(source, ref, target, null, -1));
			});
		}
		
		// TODO lfritsche: add virtual creation of nodes (this will have an effect created edges)
//		for(var createdSpecNode : specificationRule.getDeletion().getNodes()) {
//			consumers.add(m -> {
//				var createdNode = m.get(createdSpecNode.getName());
//				matcherContentAdapter.notifyChanged(SmartEMFNotification.createAddNotification(deletedNode, null, matcherContentAdapter, -1));
//			});
//		}
		
		for(var createdSpecEdge : specificationRule.getDeletion().getEdges()) {
			consumers.add(m -> {
				var source = m.get(createdSpecEdge.getSource().getName());
				var target = m.get(createdSpecEdge.getTarget().getName());
				var ref = createdSpecEdge.getType();
				if(ref.isContainer()) {
					var tmp = target;
					target = source;
					source = tmp;
					ref = ref.getEOpposite();
				}
				
				if(ref.isMany())
					matcherContentAdapter.notifyChanged(SmartEMFNotification.createAddNotification(source, ref, target, -1));
				else
					matcherContentAdapter.notifyChanged(SmartEMFNotification.createSetNotification(source, ref, null, target, -1));
			});
		}
		
		// TODO lfritsche: also postponed for later. First have to think about how HiPE would react to fake attribute changes
//		for(var assignmentSpec : specificationRule.getAttributeAssignments()) {
//			consumers.add(m -> {
//				var source = m.get(assignmentSpec.getNode().getName());
//				matcherContentAdapter.notifyChanged(SmartEMFNotification.createAddNotification(source, ref, target, -1));
//			});
//		}
	}
	
	public void registerConstraintPattern(IBeXGTPattern constraintPattern, boolean forbid) {
		if(forbid) {
			constraintPattern.subscribeAppearing(m -> violations.add((IBeXGTMatch) m));
			constraintPattern.subscribeDisappearing(m -> repairs.add((IBeXGTMatch) m));
		}
		else {
			constraintPattern.subscribeAppearing(m -> repairs.add((IBeXGTMatch) m));
			constraintPattern.subscribeDisappearing(m -> violations.add((IBeXGTMatch) m));
		}
	}
	
	public API getApi() {
		return api;
	}
	
	public void loadModel(String model) {
		var path = api.getWorkspacePath() + "Refactoring/resources/" + model;
		try {
			api.addModel(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

