package com.DsAlgo.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileReader
{
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	public ExcelFileReader(String path)
	{
		this.path=path;
	}
	public int getRowCount(String sheetName) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}

	public int getCellCount(String sheetName, int rownum) throws IOException {
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi); 
		sheet=workbook.getSheet(sheetName); 
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum); 
		cell=row.getCell(colnum);
		DataFormatter formatter = new DataFormatter();
		String data;
		try
		{
			data = formatter.formatCellValue(cell);
		}
		catch(Exception e)
		{
			data="";
		}
		workbook.close(); 
		fi.close(); 
		return data;
	}
	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException
	{
		File xlfile=new File(path);
		if(!xlfile.exists()) // If file not exists then create new file
		{
			workbook=new XSSFWorkbook();
		}

		fo=new FileOutputStream(path);
		workbook.write(fo);

		fi=new FileInputStream(path); 
		workbook=new XSSFWorkbook(fi);

		if(workbook.getSheetIndex(sheetName)==-1) // If sheet not exists then create new Sheet workbook.createSheet(sheetName);
			sheet=workbook.getSheet(sheetName);

		if(sheet.getRow(rownum)==null) // If row not exists then create new Row sheet.createRow(rownum);
			row=sheet.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
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
				key = formatter.formatCellValue(r.getCell(0));
				value = formatter.formatCellValue(r.getCell(1));
			} catch (Exception e) {
				key = "";
				value = "";
			}
			m.put(key, value);
		}
		workbook.close();
		fi.close();
		return m;
	}
}

