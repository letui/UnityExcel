package com.unity.excel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.unity.excel.style.CellStyleBinder;
import com.unity.excel.style.NoneStyle;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UStyleBinder {
	Class<? extends CellStyleBinder> Style() default NoneStyle.class;
}
