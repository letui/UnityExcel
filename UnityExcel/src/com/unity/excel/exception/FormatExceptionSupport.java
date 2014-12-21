package com.unity.excel.exception;

import java.util.Date;

public class FormatExceptionSupport  implements ExceptionSupport{

	@Override
	public Object setExportExceptionValue(Object filedValue,
			String formatPartten, Class<?> fieldType) {
		return new Object();
	}

	@Override
	public Object setImportExceptionValue(Object cellValue,
			String formatPartten, Class<?> fieldType) {
		if(fieldType.equals(Date.class)){
			return new Date();
		}else if(fieldType.equals(int.class)){
			return 0;
		}else if(fieldType.equals(String.class)){
			return "";
		}
		return new Object();
	}
}
