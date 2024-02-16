package org.emoflon.search.matching;

import java.util.Collection;

import org.emoflon.ibex.gt.engine.IBeXGTMatch;

public record WeightedMatch(IBeXGTMatch appliedRuleMatch, Collection<IBeXGTMatch> violations, Collection<IBeXGTMatch> repairs) {

}
