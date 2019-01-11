package com.tlms.core.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface AnnotationType {
	public String tableName() default "t_user";
}
