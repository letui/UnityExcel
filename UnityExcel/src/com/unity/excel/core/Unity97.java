package com.unity.excel.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.unity.excel.annotations.UTable;

public class Unity97 implements Unity {
	POIFSFileSystem fs;
	HSSFWorkbook wb;

	private void destroy(InputStream ins) {
		if (wb != null) {
			try {
				ins.close();
				wb.close();
				wb = null;
				fs = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public OutputStream exportUTable(List<? extends Object> list,
			OutputStream ous) {
		if (list.size() > 0) {
			Object temp = list.get(0);
			UTable ut = temp.getClass().getAnnotation(UTable.class);
			List<FieldWrapper> fws = FieldLoader.loadUColumnField(temp
					.getClass());

			HSSFWorkbook wb = new HSSFWorkbook();
			Sheet sh = wb.createSheet();
			Row title = sh.createRow(0);

			// 添加序号列
			if (ut.EnableSenquence()) {
				Cell cell = title.createCell(0);
				cell.setCellValue(ut.SequenceHead());
			}
			// 添加表头
			for (int i = 0; i < fws.size(); i++) {
				Cell cell = title.createCell(i + 1);
				cell.setCellValue(fws.get(i).getHead());
			}

			// 内容
			for (int i = 0; i < list.size(); i++) {
				Row row = sh.createRow(i + 1);
				Cell cell = row.createCell(0);
				cell.setCellValue(i + 1);
				for (int j = 0; j < fws.size(); j++) {
					cell = row.createCell(j + 1);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(fws.get(j).getExportValue(list.get(i))
							.toString());
					fws.get(j).injectStyle(cell, wb.createCellStyle());
				}
			}
			try {
				wb.write(ous);
				wb.close();
				return ous;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public OutputStream exportUTable(List<? extends Object> list,
			OutputStream ous, boolean autoCloseStream) {
		ous = exportUTable(list, ous);
		if (autoCloseStream) {
			try {
				ous.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ous;
	}

	@Override
	public <T> List<T> importUTable(Class<T> clazz, InputStream ins) {
		List<T> rst = new ArrayList<T>();
		init(ins);
		HSSFSheet sheet = wb.getSheetAt(0);
		UTable ut = clazz.getAnnotation(UTable.class);
		List<FieldWrapper> list = FieldLoader.loadUColumnField(clazz);
		// 遍历数据行
		try {
			for (int i = ut.TitleRowsCount(); i < sheet
					.getPhysicalNumberOfRows(); i++) {
				Row row = sheet.getRow(i);
				int startColumnCount = ut.EnableSenquence() ? 1 : 0;
				T instance = clazz.newInstance();
				for (int j = startColumnCount; j < row
						.getPhysicalNumberOfCells(); j++) {
					FieldWrapper fws = list.get(j - 1);
					Cell cell = row.getCell(j);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					Object value = fws
							.getImportValue(cell.getStringCellValue());
					fws.getField().setAccessible(true);
					fws.getField().set(instance, value);
				}
				rst.add(instance);
			}
			destroy(ins);
			return rst;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <T> List<T> importUTable(Class<T> clazz, InputStream ins,
			boolean autoCloseStream) {
		List<T> rt = importUTable(clazz, ins);
		if (autoCloseStream) {
			destroy(ins);
		}
		return rt;
	}

	private void init(InputStream ins) {
		try {
			if (fs == null) {
				fs = new POIFSFileSystem(ins);
				wb = new HSSFWorkbook(fs);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T> T readRow(Class<T> clazz, InputStream ins, int rowIndex) {
		try {
			init(ins);
			HSSFSheet sheet = wb.getSheetAt(0);
			Row row = sheet.getRow(rowIndex);
			UTable ut = clazz.getAnnotation(UTable.class);
			List<FieldWrapper> list = FieldLoader.loadUColumnField(clazz);
			int startColumnCount = ut.EnableSenquence() ? 1 : 0;
			T instance = clazz.newInstance();
			for (int j = startColumnCount; j < row.getPhysicalNumberOfCells(); j++) {
				FieldWrapper fws = list.get(j - 1);
				Cell cell = row.getCell(j);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				Object value = fws.getImportValue(cell.getStringCellValue());
				fws.getField().setAccessible(true);
				fws.getField().set(instance, value);
			}
			wb.close();
			return instance;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <T> T readRow(Class<T> clazz, InputStream ins, int rowIndex,
			boolean autoCloseStream) {
		T t = readRow(clazz, ins, rowIndex);
		if (autoCloseStream) {
			destroy(ins);
		}
		return t;
	}

	@Override
	public List<String> readRow(InputStream ins, int rowIndex) {
		return readRow(ins, rowIndex, false);
	}

	@Override
	public List<String> readRow(InputStream ins, int rowIndex,
			boolean autoCloseStream) {
		List<String> list = new ArrayList<String>();
		init(ins);
		HSSFSheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(rowIndex);
		for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
			Cell cell = row.getCell(j);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			list.add(cell.getStringCellValue());
		}
		if (autoCloseStream) {
			destroy(ins);
		}
		return list;
	}

}
