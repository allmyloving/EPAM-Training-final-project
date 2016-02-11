package ua.nure.serdyuk;

import java.util.HashMap;
import java.util.Map;

public class Errors {

	// field --> message
	private Map<String, String> container;

	public Errors() {
		container = new HashMap<>();
	}

	public void add(String key, String value) {
		container.put(key, value);
	}

	public String get(String key) {
		return container.get(key);
	}

	public Map<String, String> toMap() {
		return container;
	}
}
