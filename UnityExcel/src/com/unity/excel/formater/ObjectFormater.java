package com.unity.excel.formater;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.unity.excel.exception.ExceptionSupport;
import com.unity.excel.exception.FormatExceptionSupport;

public class ObjectFormater implements UnityFormater{

	@Override
	public Object exportProcess(Object fieldValue, String formatPartten,Class<?> fieldType) {
		if(fieldValue instanceof Double  || fieldValue instanceof Float){
			String[] pt=formatPartten.split("[.]");
			NumberFormat fmt=NumberFormat.getInstance();
			fmt.setMaximumIntegerDigits(pt[0].length());
			fmt.setMinimumIntegerDigits(pt[0].length());
			fmt.setMaximumFractionDigits(pt[1].length());
			fmt.setMinimumFractionDigits(pt[1].length());
			return fmt.format(fieldValue);
		}else if(fieldValue instanceof Date){
			SimpleDateFormat fmt=new SimpleDateFormat(formatPartten);
			return fmt.format(fieldValue);
		}else if(fieldValue instanceof String){
			return fieldValue.toString();
		}
		return null;
	}

	@Override
	public Object importProcess(Object cellValue, String formatPartten,Class<?> fieldType) {
		Object rtValue=null;
		try {
			if (fieldType.equals(double.class) || fieldType.equals(float.class)) {
				String[] pt = formatPartten.split("[.]");
				NumberFormat fmt = NumberFormat.getInstance();
				fmt.setMaximumIntegerDigits(pt[0].length());
				fmt.setMinimumIntegerDigits(pt[0].length());
				fmt.setMaximumFractionDigits(pt[1].length());
				fmt.setMinimumFractionDigits(pt[1].length());
				rtValue=fmt.parse(cellValue.toString());
			} else if (fieldType.equals(Date.class)) {
				SimpleDateFormat fmt = new SimpleDateFormat(formatPartten);
				rtValue=fmt.parse(cellValue.toString());
			}
		} catch (ParseException e) {
			rtValue=getExceptionSupport().setImportExceptionValue(cellValue, formatPartten, fieldType);
		}
		return rtValue;
	}
	@Override
	public ExceptionSupport getExceptionSupport() {
		return new FormatExceptionSupport();
	}
	

}
