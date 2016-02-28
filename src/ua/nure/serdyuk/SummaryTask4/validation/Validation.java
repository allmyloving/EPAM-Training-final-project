package ua.nure.serdyuk.SummaryTask4.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Validation {

	String message() default "Field is not valid";

	String regex() default "";

	int length() default 0;

	boolean required() default true;

	int lowerBound() default Integer.MIN_VALUE;

	int upperBound() default Integer.MAX_VALUE;
}
