package com.unity.excel.mapper;

import com.google.gson.Gson;
import com.unity.excel.exception.ExceptionSupport;

public interface UnityMapper {
	Gson gson=new Gson();
	Object exportMapping(Object value,String JsonMap,Class<?> fieldType);
	Object importMapping(Object value,String JsonMap,Class<?> fieldType);
	ExceptionSupport getExceptionSupport();
}
