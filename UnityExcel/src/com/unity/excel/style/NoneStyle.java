package com.unity.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

public class NoneStyle implements CellStyleBinder{

	@Override
	public CellStyle bindStyle(CellStyle cellStyle) {
//		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);  
//        cellStyle.setBorderTop(CellStyle.BORDER_THIN);  
//        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);  
//        cellStyle.setBorderRight(CellStyle.BORDER_THIN);  
//        //设置一个单元格边框颜色  
//        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());  
//        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());  
//        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());  
//        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());        
        return cellStyle;
	}

}
