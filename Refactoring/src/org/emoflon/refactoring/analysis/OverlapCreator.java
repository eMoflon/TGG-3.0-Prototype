package org.emoflon.refactoring.analysis;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.emoflon.ibex.gt.engine.IBeXGTMatch;

public class OverlapCreator {

	private int overlapSize = -1;
	private Map<String, List<String>> patternName2parameters = new HashMap<>();
	
	public OverlapCreator() {
		
	}
	
	/**
	 * This method is used to register overlaps, so that an overlap can be derived using an {@link IBeXGTMatch}
	 * @param patternName the name of the pattern from which the matches come
	 * @param parameters the elements in the overlap in the correct order
	 */
	public void registerOverlap(String patternName, String... parameters) {
		if(overlapSize == -1)
			overlapSize = parameters.length;
		else
			if(overlapSize != parameters.length)
				throw new RuntimeException("The registered overlap for pattern " + patternName + " has " + parameters.length + " parameters but was expected to have " + overlapSize);
		
				
		var parameterList = patternName2parameters.computeIfAbsent(patternName, (x) -> new LinkedList<String>());
		for(var parameter : parameters)
			parameterList.add(parameter);
	}
	
	public boolean supportsPattern(String name) {
		return patternName2parameters.containsKey(name);
	}
	
	/**
	 * Create an overlap from the given match
	 * @param match
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public MatchOverlap createOverlap(IBeXGTMatch match) {
		var parameters = patternName2parameters.get(match.getPatternName());
		if(parameters == null) {
			throw new RuntimeException("Cannot create an overlap for match of pattern " + match.getPatternName());
		}
		
		var overlap = new MatchOverlap(parameters.size());
		for(var i=0; i<parameters.size();i++) {
			overlap.nodes[i] = match.get(parameters.get(i));
		}
		return overlap;
	}
	
	
}
