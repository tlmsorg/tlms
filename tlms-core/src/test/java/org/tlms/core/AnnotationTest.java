package org.tlms.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@Target(ElementType.METHOD)
public @interface AnnotationTest {
	public String name() default "ttt";
}
