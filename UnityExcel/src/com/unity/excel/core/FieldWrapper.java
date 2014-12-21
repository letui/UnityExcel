package com.unity.excel.core;

import java.lang.reflect.Field;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import com.unity.excel.annotations.UColumn;
import com.unity.excel.annotations.UFormatter;
import com.unity.excel.annotations.UMapper;
import com.unity.excel.annotations.UStyleBinder;
import com.unity.excel.formater.UnityFormater;
import com.unity.excel.mapper.UnityMapper;
import com.unity.excel.style.CellStyleBinder;

public class FieldWrapper implements Comparable<FieldWrapper> {
	private UColumn ucolumn;
	private Field field;
	private int order;
	private String head;
	private UMapper mapper;
	private UFormatter formatter;
	
	public FieldWrapper(){
		
	}
	public FieldWrapper(Field field,UColumn uc){
		this.field=field;
		this.order=uc.Index();
		this.head=uc.Head().equals("#")?field.getName():uc.Head();
		this.ucolumn=uc;
		this.mapper=field.getAnnotation(UMapper.class);
		this.formatter=field.getAnnotation(UFormatter.class);
	}
	@Override
	public int compareTo(FieldWrapper arg0) {
		return this.order-arg0.order;
	}
	public Object getExportValue(Object target){
		try {
			field.setAccessible(true);
			Object before= field.get(target);
			if(formatter!=null){
				UnityFormater uf=(UnityFormater)formatter.Formater().newInstance();
				before=uf.exportProcess(before,formatter.FormatPartten(),field.getType());
			}
			if(mapper!=null){
				UnityMapper um=(UnityMapper)mapper.Mapper().newInstance();
				before=um.exportMapping(before, mapper.JsonMap(),field.getType());
			}
			if(!ucolumn.Prefix().equals("")){
				before=ucolumn.Prefix()+before;
			}
			if(!ucolumn.Suffix().equals("")){
				before+=ucolumn.Suffix();
			}
			return before;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Field getField() {
		return field;
	}
	public String getHead() {
		return head;
	}
	public Object getImportValue(Object value){
		try {
			if(!ucolumn.Prefix().equals("")){
				value=value.toString().replaceAll(ucolumn.Prefix(), "");
			}
			if(!ucolumn.Suffix().equals("")){
				value=value.toString().replaceAll(ucolumn.Suffix(), "");
			}
			
			if(formatter!=null){
				UnityFormater uf=(UnityFormater)formatter.Formater().newInstance();
				value=uf.importProcess(value,formatter.FormatPartten(),field.getType());
			}
			if(mapper!=null){
				UnityMapper um=(UnityMapper)mapper.Mapper().newInstance();
				value=um.importMapping(value, mapper.JsonMap(),field.getType());
			}
			
			if(field.getType().equals(String.class)){
				value=String.valueOf(value);
			}else if(field.getType().equals(int.class)){
				value=Integer.valueOf(value==null?"0":value.toString());
			}else if(field.getType().equals(double.class)){
				value=Double.valueOf(value==null?"0":value.toString());
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return value;
	}
	public void injectStyle(Cell cell,CellStyle style){
		UStyleBinder ustyle=field.getAnnotation(UStyleBinder.class);
		if(ustyle!=null){
			try {
				CellStyleBinder binder=(CellStyleBinder)ustyle.Style().newInstance();
				cell.setCellStyle(binder.bindStyle(style));
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getOrder() {
		return order;
	}
	public UColumn getUcolumn() {
		return ucolumn;
	}

	public void setField(Field field) {
		this.field = field;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
	public void setUcolumn(UColumn ucolumn) {
		this.ucolumn = ucolumn;
	}
	
	@Override
	public String toString() {
		return "FieldWrapper [field=" + field + ", order=" + order + "]";
	}
	
}
