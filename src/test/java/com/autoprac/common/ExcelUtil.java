package com.autoprac.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.RowIdLifetime;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IconMultiStateFormatting.IconSet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil extends Base{

	public static XSSFWorkbook wbFile;
	public static XSSFSheet shFile;
	public static XSSFRow row;
	public static XSSFCell cell;

	//Get Excel File
	public static void getExcel(String filePath) throws IOException {
		try {
			FileInputStream datafile = new FileInputStream(filePath);
			wbFile = new XSSFWorkbook(datafile);
		} catch (Exception e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
	}


	//Get Excel Sheet
	public static void getSheet(int sheetno) throws IOException {
		try {
			shFile = wbFile.getSheetAt(sheetno);
		} catch (Exception e) {
			System.out.println("Sheet not found");
			e.printStackTrace();
		}
	}


	//Row Count
	public static int getRowCount() {
		int rowCount = 0; 
		try {
			rowCount = shFile.getLastRowNum()+1;
			System.out.println("No of rows are: "+rowCount);
		}catch(Exception e) {
			System.out.println("Did not get Rows");
			e.printStackTrace();
		}

		return rowCount;
	}


	//Column Count
	public static int getColumnCount(){
		int colCount=0;
		try {
			colCount = shFile.getRow(0).getLastCellNum();
			System.out.println("No of columns are: "+colCount);
		}catch(Exception e) {
			System.out.println("Did not get Columns");
			e.printStackTrace();
		}
		return colCount;
	}


	//Get String Value
	public static String getCellDataString(int rowNum, int colNum) {
		String cellData = null;
		try {
			cellData = shFile.getRow(rowNum).getCell(colNum).getStringCellValue();
		}catch(Exception e) {
			System.out.println("Data not found");
			e.printStackTrace();
		}
		return cellData;
	}


	//Get Numeric Value
	public static double getCellDataNumber(int rowNum, int colNum) {
		double cellData = 0;
		try {
			cellData = shFile.getRow(rowNum).getCell(colNum).getNumericCellValue();
		}catch(Exception e) {
			System.out.println("Data not found");
			e.printStackTrace();
		}
		return cellData;
	}


	//Change data to String
	public static String setCellDataToString(int rowNum, int colNum) {
		XSSFCell cell = null;
		String cellData = null;
		try {
			cell = shFile.getRow(rowNum).getCell(colNum);
			cell.setCellType(CellType.STRING);
			cellData = cell.getStringCellValue();
		}catch(Exception e) {
			System.out.println("Data not found");
			e.printStackTrace();
		}
		return cellData;
	}


	//DataProvider from Excel
	public static Object[][] getData() throws IOException{

		int rowCount = ExcelUtil.getRowCount();
		int colCount = ExcelUtil.getColumnCount();

		Object[][] data = new Object[rowCount-1][colCount];

		for(int i=1; i<rowCount; i++)
		{
			for(int j=0; j<colCount; j++)
			{
				data[i-1][j] = ExcelUtil.setCellDataToString(i, j);
			}
		}
		return data;
	}




	//Create Sheet
	public static int createExcelSheet() throws IOException {
		XSSFSheet newSheet = wbFile.createSheet();
		String shName = newSheet.getSheetName();
		int newSheetno = wbFile.getSheetIndex(shName);
		return newSheetno;
	}


	//Create Row
	public static void createRow() {
		int rowCount = ExcelUtil.getRowCount();
		try {
			shFile.createRow(rowCount);
		} catch (Exception e) {
			System.out.println("New Row did not ccreated");
		}
	}


	//Create Column
	public static void createColumn() {

	}


	//Write into Excel
	public static void writeIntoExcel(String filePath, String dataToWrite) throws IOException {
		int lastRow = ExcelUtil.getRowCount();
		int lastColumn = ExcelUtil.getColumnCount();

		for(int i=1; i<=lastRow; i++){
			Row row = shFile.getRow(i);
			Cell cell = row.createCell(lastColumn);
			cell.setCellValue(dataToWrite);
			OutputStream  fos = new FileOutputStream(filePath);
			wbFile.write(fos);
		}
	}
}