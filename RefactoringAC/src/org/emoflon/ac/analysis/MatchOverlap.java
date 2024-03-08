package org.emoflon.ac.analysis;

import java.util.Arrays;

/**
 * 
 * @author Lars Fritsche
 * 
 * This class represents a match overlap 
 *
 */
public class MatchOverlap {

	public final Object[] nodes;
	
	public MatchOverlap(int overlapSize) {
		nodes = new Object[overlapSize];
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MatchOverlap other) {
			return Arrays.equals(nodes, other.nodes);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Arrays.hashCode(nodes);
	}
}
