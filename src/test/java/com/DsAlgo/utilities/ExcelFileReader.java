package com.DsAlgo.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileReader {

	public static ExcelFileReader excelFileReader = null;

	public ExcelFileReader(String filePath) {
		path = filePath;
	}

	public static ExcelFileReader getInstance() {
		if (excelFileReader != null) {
			return excelFileReader;
		} else {
			String filePath = ConfigFileReader.getInstance().getExcelPath();
			excelFileReader = new ExcelFileReader(filePath);
			return excelFileReader;
		}
	}

	private FileInputStream fi;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;
	private String path;

	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}

	public int getCellCount(String sheetName, int rownum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}

	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		workbook.close();
		fi.close();
		return data;
	}

	public Map<String, String> getKeyPair(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		XSSFRow r;
		Map<String, String> m = new HashMap<String, String>();
		DataFormatter formatter = new DataFormatter();
		for (int i = 0; i < rowCount; i++) {
			r = sheet.getRow(i);
			String key, value;
			try {
				key= formatter.formatCellValue(r.getCell(0));
				value = formatter.formatCellValue(r.getCell(1));
			} catch (Exception e) {
				key= "";
				value="";
			}
			m.put(key,value);
		}
		workbook.close();
		fi.close();
		return m;
	}
}
