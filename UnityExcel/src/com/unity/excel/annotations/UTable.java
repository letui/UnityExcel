package com.unity.excel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UTable {
	int TitleRowsCount() default 1;
	boolean EnableSenquence() default true;
	String SequenceHead() default "序号";
}
