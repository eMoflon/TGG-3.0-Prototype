package org.emoflon.ac;

import java.util.HashSet;
import java.util.Set;

import org.emoflon.ac.analysis.ConstraintCounter;
import org.emoflon.ibex.gt.api.IBeXGtAPI;
import org.emoflon.ibex.gt.engine.IBeXGTMatch;
import org.emoflon.ibex.gt.engine.IBeXGTPattern;
import org.emoflon.ibex.gt.engine.IBeXGTRule;
import org.emoflon.logging.LoggingConfig;

/**
 * 
 * @author Lars Fritsche
 *
 * @param <API> is the {@link IBeXGtAPI} that is used to collect rule and
 *              pattern matches
 *
 *              The class is the base of our evaluation cases and manages
 *              initialisation and provides the abstract control flow
 */
@SuppressWarnings("unused")
public abstract class RefactoringCase<API extends IBeXGtAPI<?, ?, ?>> {

	protected API api;

//	private Map<String, Collection<OverlapCreator>> name2overlapCreators = new HashMap<>();

	protected Set<IBeXGTRule> rules = new HashSet<>();
	protected Set<IBeXGTPattern> violations = new HashSet<>();
	protected Set<IBeXGTPattern> repairs = new HashSet<>();

	protected ConstraintCounter constraintCounter;

	public RefactoringCase(String path) {
		initializeMetamodel();
		createAPI();
		initializeAPI(path);
		createAndRegisterOverlaps();
		registerSubscriptions();
	}

	protected abstract void initializeMetamodel();

	protected abstract void createAPI();

	private void initializeAPI(String path) {
		loadModel(path);
		api.initializeEngine();
	}

	protected abstract void createAndRegisterOverlaps();

	@SuppressWarnings("unchecked")
	private void registerSubscriptions() {
		for (var rule : rules) {
			rule.subscribeAppearing((o) -> constraintCounter.addRuleMatch((IBeXGTMatch) o));
			rule.subscribeDisappearing((o) -> constraintCounter.removeRuleMatch((IBeXGTMatch) o));
		}

		for (var violation : violations) {
			violation.subscribeAppearing((o) -> constraintCounter.addViolation((IBeXGTMatch) o));
			violation.subscribeDisappearing((o) -> constraintCounter.removeViolation((IBeXGTMatch) o));
		}

		for (var repair : repairs) {
			repair.subscribeAppearing((o) -> constraintCounter.addRepair((IBeXGTMatch) o));
			repair.subscribeDisappearing((o) -> constraintCounter.removeRepair((IBeXGTMatch) o));
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

	public boolean performOneStep() {
		var nextRankedMatch = constraintCounter.getNextMatch();
		if (nextRankedMatch == null) {
			LoggingConfig.log("Abort:", "No match found");
			return false;
		}

		var nextMatch = nextRankedMatch.match();
		for (var rule : rules) {
			if (rule.patternName.equals(nextMatch.getPatternName())) {
				LoggingConfig.logln("Applying Match:", nextMatch);
				rule.apply(nextMatch);
				return true;
			}
		}
		return false;
	}

	public API getApi() {
		return api;
	}

	public ConstraintCounter getConstraintCounter() {
		return constraintCounter;
	}

}
