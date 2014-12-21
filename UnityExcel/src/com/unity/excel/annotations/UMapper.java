package com.unity.excel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.unity.excel.mapper.AnyMapString;
import com.unity.excel.mapper.UnityMapper;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UMapper {
	Class<? extends UnityMapper> Mapper() default AnyMapString.class; 
	String JsonMap() default "#";
}
