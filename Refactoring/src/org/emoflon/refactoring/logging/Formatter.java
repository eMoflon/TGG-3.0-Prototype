package org.emoflon.refactoring.logging;

import org.emoflon.ibex.gt.engine.IBeXGTMatch;

import softwareSystem.Component;

public class Formatter {

	
	public static String printWithTab(String name, int tabs) {
		var out = name;
		var numOfTabs = tabs - Math.ceil(name.length() / 8);
		for(int i=1; i < numOfTabs; i++) {
			out += "\t";
		}
		return out;
	}
	
	public static String toString(Object object) {
		if(object instanceof IBeXGTMatch match) {
			var out = printWithTab(match.getPatternName(), 4) + " [";
			for(var entry : match.getObjects()) {
				out += "\t" + toString(entry);
			}
			out += " ]";
			return out;
		}
		if(object instanceof softwareSystem.System system) {
			return printWithTab("System: " + system.getName(), 3);
		}
		if(object instanceof Component component) {
			return printWithTab("Component: " + component.getName(), 5);
		}
		return object.toString();
	}
}
