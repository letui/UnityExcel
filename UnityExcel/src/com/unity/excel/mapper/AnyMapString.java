package com.unity.excel.mapper;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.unity.excel.exception.ExceptionSupport;
import com.unity.excel.exception.MapperExceptionSupport;

public class AnyMapString implements UnityMapper{
	
	@Override
	public Object exportMapping(Object filedValue, String JsonMap,Class<?> fieldType) {
		Map<String,String> map=gson.fromJson(JsonMap, new TypeToken<HashMap<String,String>>(){}.getType());
		if(map.containsKey(filedValue.toString())){
			return map.get(filedValue.toString());	
		}
		return getExceptionSupport().setExportExceptionValue(filedValue, JsonMap, fieldType);
	}

	@Override
	public Object importMapping(Object value, String JsonMap,Class<?> fieldType) {
		Map<String,String> map=gson.fromJson(JsonMap, new TypeToken<HashMap<String,String>>(){}.getType());
		for(String key:map.keySet()){
			if(map.get(key).equals(value)){
				return key;
			}
		}
		return getExceptionSupport().setImportExceptionValue(value, JsonMap, fieldType);
	}

	@Override
	public ExceptionSupport getExceptionSupport() {
		return new MapperExceptionSupport();
	}
	
}
