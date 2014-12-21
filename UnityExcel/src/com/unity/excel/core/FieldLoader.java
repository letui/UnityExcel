package com.unity.excel.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;

import com.unity.excel.annotations.UColumn;

public class FieldLoader {
	public static ArrayList<FieldWrapper> loadUColumnField(Class<?> clazz){
		ArrayList<FieldWrapper> uColumnField=new ArrayList<FieldWrapper>();
		Field[] fds=clazz.getDeclaredFields();
		for(int i=0;i<fds.length;i++){
			UColumn uc=fds[i].getAnnotation(UColumn.class);
			if(uc!=null){
				FieldWrapper wr=new FieldWrapper(fds[i],uc);
				uColumnField.add(wr);
			}
		}
		Collections.sort(uColumnField);
		return uColumnField;
	}
}
