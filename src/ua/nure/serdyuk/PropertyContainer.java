package ua.nure.serdyuk;

import java.util.Collections;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.constants.Path;

public final class PropertyContainer {

	private static final Logger LOG = Logger.getLogger(PropertyContainer.class);

	private static ResourceBundle bundle;

	static {
		bundle = ResourceBundle.getBundle(Path.SQL_RES);

		LOG.debug(Collections.list(bundle.getKeys()));
	}

	public static String get(String key) {
		try {
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			LOG.error(String.format("Resource was not found -- %s",
					e.getMessage()));
			throw new RuntimeException(String
					.format("Resource was not found -- %s", e.getMessage()));
		}
	}
}
