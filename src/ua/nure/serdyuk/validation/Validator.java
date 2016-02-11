package ua.nure.serdyuk.validation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import ua.nure.serdyuk.Message;

public class Validator {

	private static final long LOWER_BOUND = getDefaultLower();

	private static final long UPPER_BOUND = getDefaultUpper();

	/**
	 * Validates an entity using @Validation annotation.
	 * 
	 * @param obj
	 *            object to be validated
	 * @return a list of errors found, if none -- empty list
	 */
	public static List<String> validate(Object obj) {
		List<String> errors = new ArrayList<>();
		Class<?> clazz = obj.getClass();
		for (Field f : clazz.getDeclaredFields()) {
			if (f.isAnnotationPresent(Validation.class)) {
				errors.addAll(validateField(f, obj));
			}
		}
		return errors;
	}

	// add `required` validation
	private static List<String> validateField(Field f, Object obj) {
		List<String> errors = new ArrayList<>();
		Validation v = f.getAnnotation(Validation.class);

		f.setAccessible(true);

		Object value = 0;
		try {
			value = f.get(obj);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new AssertionError("This should have never happened", e);
		}

		if (!v.regex().isEmpty()) {
			String strValue = (String) value;
			if (strValue != null && !strValue.matches(v.regex())) {
				errors.add(v.message());
			}
		}
		if (v.upperBound() != UPPER_BOUND) {
			long longValue = (long) value;
			if (longValue > v.upperBound()) {
				errors.add(v.message());
			}
		}

		if (v.lowerBound() != LOWER_BOUND) {
			long longValue = (long) value;
			if (longValue < v.lowerBound()) {
				errors.add(v.message());
			}
		}

		if (v.required()) {
			if (value == null) {
				errors.add(Message.FIELD_REQUIRED);
			}
		}
		return errors;
	}

	private static long getDefaultUpper() {
		Class<?> clazz = Validation.class;
		try {
			return (Long) clazz.getMethod("upperBound").getDefaultValue();
		} catch (NoSuchMethodException | SecurityException e) {
			throw new AssertionError(
					"Shouldn't happen unless an entity has changed", e);
		}
	}

	private static long getDefaultLower() {
		Class<?> clazz = Validation.class;
		try {
			return (Long) clazz.getMethod("lowerBound").getDefaultValue();
		} catch (NoSuchMethodException | SecurityException e) {
			throw new AssertionError(
					"Shouldn't happen unless an entity has changed", e);
		}
	}
}