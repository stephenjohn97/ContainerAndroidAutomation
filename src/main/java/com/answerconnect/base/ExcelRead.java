package com.answerconnect.base;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelRead {

	public static final String FILE_NAME = "AndroidSheet.xlsx";

	public static String getData(String key) {
		FileInputStream inputStream = null;
		Workbook workbook = null;
		String value = null;
		try {
			inputStream = new FileInputStream(FILE_NAME);
			workbook = WorkbookFactory.create(inputStream);
			int sheetCount = workbook.getNumberOfSheets();
			for (int s = 0; s < sheetCount; s++) {
				Sheet sheet = workbook.getSheetAt(s);
				int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
				for (int i = 0; i <= rowCount; i++) {
					Row row = sheet.getRow(i);
					if (row == null) {
						continue;
					}
					Cell keyCell = row.getCell(0);
					if (keyCell == null || keyCell.getCellType() == CellType.BLANK) {
						continue;
					}
					if (keyCell.getStringCellValue().equals(key)) {
						Cell valueCell = row.getCell(1);
						if (valueCell == null || valueCell.getCellType() == CellType.BLANK) {
							continue;
						} else if (valueCell.getCellType() == CellType.NUMERIC) {
							DataFormatter formatter = new DataFormatter();
							value = formatter.formatCellValue(valueCell);
						} else {
							value = valueCell.getStringCellValue();
						}
						break;
					}
				}
				if (value != null) {
					break;
				}
			}
			if (value == null) {
				throw new RuntimeException("Key " + key + " is not found in any sheet");
			}
		} catch (IOException e) {
			System.err.println("Error reading data from the file: " + e.getMessage());
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					System.err.println("Error closing the workbook: " + e.getMessage());
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					System.err.println("Error closing the inputstream: " + e.getMessage());
				}
			}
		}
		return value;
	}
}
