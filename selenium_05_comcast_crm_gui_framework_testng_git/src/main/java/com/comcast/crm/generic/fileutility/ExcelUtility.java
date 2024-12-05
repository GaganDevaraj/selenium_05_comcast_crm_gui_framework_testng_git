package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public String getDataFromExcelFile(String sheetName, int rowNumber, int coloumnNumber) throws Exception {
		FileInputStream fin1 = new FileInputStream("testScriptData/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fin1);
		Sheet sheet = wb.getSheet(sheetName);
		Row cell = sheet.getRow(rowNumber);
		String cellValue = cell.getCell(coloumnNumber).getStringCellValue();
		return cellValue;
	}

	public int getRowCount(String sheetName) throws Exception {
		FileInputStream fin1 = new FileInputStream("testScriptData/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fin1);
		Sheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		return rowCount;
	}

	public int getColoumnCount(String sheetName, int rowNumber) throws Exception {
		FileInputStream fin1 = new FileInputStream("testScriptData/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fin1);
		Sheet sheet = wb.getSheet(sheetName);
		Row row = sheet.getRow(rowNumber);
		int coloumnCount = row.getLastCellNum();
		return coloumnCount;
	}

	public void setDataIntoExcelSheet(String sheetName, int rowNumber, int coloumnNumber, String data)
			throws Exception {
		FileInputStream fin1 = new FileInputStream("testScriptData/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fin1);
		Sheet sheet = wb.getSheet(sheetName);
		Row row = sheet.getRow(rowNumber);
		Cell cell = row.createCell(coloumnNumber);
		cell.setCellValue(data);

		FileOutputStream fout = new FileOutputStream("testScriptData/testScriptData.xlsx");
		wb.write(fout);
		wb.close();
	}
}
