package org.emoflon.refactoring.ac.analysis;

import java.util.Arrays;

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
