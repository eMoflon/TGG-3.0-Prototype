package org.emoflon.refactoring.logging;

import java.util.Collection;
import java.util.HashSet;

import org.emoflon.ibex.gt.engine.IBeXGTMatch;

public class LoggingConfig {

	public static boolean activateLogging = false;
	public static boolean activateMatchLogging = false;
	public static boolean useFormatter = true;
	
	public static final Collection<String> matchSubStringsToLog = new HashSet<>();
	
	public static void log(String prefix, Object o) {
		if(activateLogging) {
			var out = "";
			if(prefix != null && prefix.length() != 0)
				out += prefix + ": \t";
			
			if(o instanceof IBeXGTMatch && !activateMatchLogging)
				return;
			
			if(matchSubStringsToLog.isEmpty()) {
				System.out.println(out + (useFormatter ? Formatter.toString(o) : o.toString()));
				return;
			}
			
			for(var subString : matchSubStringsToLog) {
				if(o.toString().contains(subString)) {
					System.out.println(out + (useFormatter ? Formatter.toString(o) : o.toString()));
					return;
				}
			}
		}
	}
}
