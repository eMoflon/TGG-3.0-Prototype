package org.emoflon;

import org.emoflon.ibex.gt.engine.IBeXGTMatch;

/**
 * 
 * @author Lars Fritsche
 *
 * This record represents a rule match with a gain represents the net repair gain when applying it
 */
public record RankedRuleApplication(IBeXGTMatch match, double gain, double repairs, double violations) {
	
}