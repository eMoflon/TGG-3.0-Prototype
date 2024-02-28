package org.emoflon.refactoring;

import org.emoflon.ibex.gt.engine.IBeXGTMatch;

/**
 * 
 * @author Lars Fritsche
 *
 * This record represents a rule match with a gain represents the net repair gain when applying it
 */
public record RankedRuleApplication(IBeXGTMatch match, int gain, int repairs, int violations) {
	
}