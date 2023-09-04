package softwareSystem;

import java.lang.String;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

public enum SIL implements Enumerator {
	
	 LOW(0, "LOW", "LOW"), MED(1, "MED", "MED"), HIGH(2, "HIGH", "HIGH");
	
	public static final int LOW_VALUE = 0;
	public static final int MED_VALUE = 1;
	public static final int HIGH_VALUE = 2;
	
	private static final SIL[] VALUES_ARRAY = new SIL[] {LOW,MED,HIGH};

	public static final List<SIL> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	public static SIL get(String literal) {
	for (int i = 0; i < VALUES_ARRAY.length; ++i) {
		SIL result = VALUES_ARRAY[i];
		if (result.toString().equals(literal)) {
			return result;
		}
	}
	return null;
	}

	public static SIL getByName(String name) {
	for (int i = 0; i < VALUES_ARRAY.length; ++i) {
		SIL result = VALUES_ARRAY[i];
		if (result.getName().equals(name)) {
			return result;
		}
	}
	return null;
	}

	public static SIL get(int value) {
		switch (value) {
		case LOW_VALUE:
			return LOW;
		case MED_VALUE:
			return MED;
		case HIGH_VALUE:
			return HIGH;
		}
		return null;
	}

	private final int value;

	private final String name;

	private final String literal;

	private SIL(int value, String name, String literal) {
	this.value = value;
	this.name = name;
	this.literal = literal;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public String getName() {
	return name;
	}

	@Override
	public String getLiteral() {
		return literal;
	}

	@Override
	public String toString() {
	return literal;
	}

}

