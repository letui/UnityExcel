package com.unity.excel.core;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface Unity {
	 <T> List<T> importUTable(Class<T> clazz,InputStream ins);
	 <T> List<T> importUTable(Class<T> clazz,InputStream ins,boolean autoCloseStream);
	 <T> T readRow(Class<T> clazz,InputStream ins,int rowIndex);
	 <T> T readRow(Class<T> clazz,InputStream ins,int rowIndex,boolean autoCloseStream);
	 List<String> readRow(InputStream ins,int rowIndex);
	 List<String> readRow(InputStream ins,int rowIndex,boolean autoCloseStream);
	 OutputStream exportUTable(List<? extends Object> list,OutputStream ous);
	 OutputStream exportUTable(List<? extends Object> list,OutputStream ous,boolean autoCloseStream);
	 
}
 