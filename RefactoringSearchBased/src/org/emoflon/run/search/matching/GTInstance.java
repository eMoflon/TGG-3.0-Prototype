package org.emoflon.run.search.matching;

import org.emoflon.ibex.gt.api.IBeXGtAPI;

public abstract class GTInstance<API extends IBeXGtAPI<?,?,?>>{
	protected API api;

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
	
	protected void initialize() {
		api.initializeEngine();
	}
	
	protected abstract void createAPI();
}
