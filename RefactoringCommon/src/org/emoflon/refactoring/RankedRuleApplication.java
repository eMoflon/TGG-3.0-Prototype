package org.emoflon.refactoring;

import org.emoflon.ibex.gt.engine.IBeXGTMatch;

public record RankedRuleApplication(IBeXGTMatch match, int gain, int repairs, int violations) {
	
}