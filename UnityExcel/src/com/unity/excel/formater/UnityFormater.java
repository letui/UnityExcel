package com.unity.excel.formater;

import com.unity.excel.exception.ExceptionSupport;

public interface UnityFormater {
	Object exportProcess(Object filedValue,String formatPartten,Class<?> fieldType);
	Object importProcess(Object cellValue,String formatPartten,Class<?> fieldType);
	ExceptionSupport getExceptionSupport();
}
