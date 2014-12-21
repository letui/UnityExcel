package com.unity.excel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UColumn {
	String Head() default "#";
	int Index();
	String Prefix() default "";
	String Suffix() default "";
}
