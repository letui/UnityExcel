package com.unity.excel.exception;

public interface ExceptionSupport {
	Object setExportExceptionValue(Object filedValue,String formatPartten_Or_JsonMap,Class<?> fieldType);
	Object setImportExceptionValue(Object cellValue,String formatPartten_Or_JsonMap,Class<?> fieldType);
}
