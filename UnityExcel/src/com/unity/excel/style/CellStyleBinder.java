package com.unity.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;

public interface CellStyleBinder {
	CellStyle bindStyle(CellStyle st);
}
