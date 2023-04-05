package com.answerconnect.base;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.answerconnect.utilities.CommonUtilities;

public class ExcelRead extends CommonUtilities {

	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static FileInputStream inputStream;

	public static String readExcelSheet(int rownum, int colnum) {
		try {
			inputStream = new FileInputStream(
					"/Users/stefan/Desktop/Answering Service /AC/ANCT_Android_BDD/AC_Cred.xlsx");
			workbook = new XSSFWorkbook(inputStream);
			sheet = workbook.getSheet("Sheet1");
			row = sheet.getRow(rownum);
			cell = row.getCell(colnum);
			if (cell == null) {
				return "";
			}
			return cell.getStringCellValue();

		} catch (Exception e) {
			System.out.println("In the Catch Block:" + e);
			return "Exception Occured";
		}
	}

	public static String validEmail = readExcelSheet(1, 1);
	public static String invalidEmail = readExcelSheet(3, 1);
	public static String password = readExcelSheet(2, 1);

}
