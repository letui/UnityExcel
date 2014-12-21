package com.unity.excel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.unity.excel.formater.ObjectFormater;
import com.unity.excel.formater.UnityFormater;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UFormatter {
	String FormatPartten() default "#";
	Class<? extends UnityFormater> Formater() default ObjectFormater.class;
}
