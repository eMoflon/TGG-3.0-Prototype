package org.emoflon.refactoring;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.emoflon.ibex.gt.api.IBeXGtAPI;
import org.emoflon.ibex.gt.engine.IBeXGTMatch;
import org.emoflon.ibex.gt.engine.IBeXGTPattern;
import org.emoflon.refactoring.analysis.ConstraintCounter;
import org.emoflon.refactoring.analysis.OverlapCreator;

@SuppressWarnings("unused")
public abstract class RefactoringCase<API extends IBeXGtAPI<?,?,?>> {

	protected API api;
	
	protected Map<String, Collection<OverlapCreator>> name2overlapCreators = new HashMap<>();
	
	protected Set<IBeXGTPattern> rules = new HashSet<>();
	protected Set<IBeXGTPattern> violations = new HashSet<>();
	protected Set<IBeXGTPattern> repairs = new HashSet<>();
	
	protected ConstraintCounter constraintCounter;
	
	public RefactoringCase(String path) {
		createAPI();
		initializeAPI(path);
		createAndRegisterOverlaps();
		registerSubscriptions();
	}
	
	
	protected abstract void createAPI();
	
	private void initializeAPI(String path) {
		loadModel(path);
		api.initializeEngine();
	}

	protected abstract void createAndRegisterOverlaps();
	
	@SuppressWarnings("unchecked")
	private void registerSubscriptions() {
		for(var rule : rules) {
			rule.subscribeAppearing((o) -> constraintCounter.addRuleMatch((IBeXGTMatch) o));
			rule.unsubscribeAppearing((o) -> constraintCounter.removeRuleMatch((IBeXGTMatch) o));
		}
		
		for(var violation : violations) {
			violation.subscribeAppearing((o) -> constraintCounter.addViolation((IBeXGTMatch) o));
			violation.unsubscribeAppearing((o) -> constraintCounter.removeViolation((IBeXGTMatch) o));
		}
		
		for(var repair : repairs) {
			repair.subscribeAppearing((o) -> constraintCounter.addRepair((IBeXGTMatch) o));
			repair.unsubscribeAppearing((o) -> constraintCounter.removeRepair((IBeXGTMatch) o));
		}
	}

	public void loadModel(String model) {
		var path = api.getWorkspacePath() + "Refactoring/resources/" + model;
		try {
			api.addModel(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public API getApi() {
		return api;
	}
	
	public ConstraintCounter getConstraintCounter() {
		return constraintCounter;
	}
	
}
