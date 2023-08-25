package org.emoflon.run.search.matching;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.emoflon.ibex.gt.api.IBeXGtAPI;
import org.emoflon.ibex.gt.engine.IBeXGTMatch;
import org.emoflon.ibex.gt.engine.IBeXGTRule;

public abstract class RuleMatchingInstance<API extends IBeXGtAPI<?,?,?>> extends GTInstance<API> {
	
	
	private Map<String, IBeXGTRule> name2rule = new HashMap<>();
	
	public RuleMatchingInstance(String modelPath) {
		createAPI();
		loadModel(modelPath);
		initialize();
	}
	
	public void registerRule(IBeXGTRule rule) {
		name2rule.put(rule.patternName, rule);
	}
	
	public Map<String, Collection<IBeXGTMatch>> findRuleMatches() {
		api.updateMatches();
		var output = new HashMap<String, Collection<IBeXGTMatch>>();
		for(var rule : name2rule.values()) {
			output.put(rule.patternName, rule.getMatches());
		}
		return output;
	}

	public void update() {
		api.updateMatches();
	}
	
	public Collection<IBeXGTRule> getRules() {
		return name2rule.values();
	}
}
