package com.unity.excel.exception;

public class MapperExceptionSupport implements  ExceptionSupport{

	@Override
	public Object setExportExceptionValue(Object filedValue,
			String jsonMap, Class<?> fieldType) {
		if(fieldType.equals(String.class)){
			return "";
		}else if(fieldType.equals(int.class)){
			return 0;
		}else if(fieldType.equals(double.class)){
			return 0.0;
		}else if(fieldType.equals(float.class)){
			return 0.0;
		}else if(fieldType.equals(short.class)){
			return 0;
		}
		return "";
	}

	@Override
	public Object setImportExceptionValue(Object cellValue,
			String formatPartten, Class<?> fieldType) {
		if(fieldType.equals(String.class)){
			return "";
		}else if(fieldType.equals(int.class)){
			return 0;
		}else if(fieldType.equals(double.class)){
			return 0.0;
		}else if(fieldType.equals(float.class)){
			return 0.0;
		}else if(fieldType.equals(short.class)){
			return 0;
		}
		return "";
	}

}
