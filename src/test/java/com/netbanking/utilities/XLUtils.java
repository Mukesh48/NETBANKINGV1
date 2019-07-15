package com.netbanking.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils
{
public static FileInputStream fi;
public static FileOutputStream fo;
public static XSSFWorkbook wb;
public static XSSFSheet sh;
public static XSSFRow row;
public static XSSFCell cell;

public static int getRowCount(String xlfile,String xlsheet) throws IOException
{
	fi=new FileInputStream(xlfile);
	wb=new XSSFWorkbook(fi);
	sh=wb.getSheet(xlsheet);
	int rowcount=sh.getLastRowNum();
	wb.close();
	fi.close();
	return rowcount;
}
public static int getCellCount(String xlfile,String xlsheet,int rownum) throws IOException
{	
	fi=new FileInputStream(xlfile);
	wb=new XSSFWorkbook(fi);
	sh=wb.getSheet(xlsheet);
	row=sh.getRow(rownum);
	int cellcount=row.getLastCellNum();
	wb.close();
	fi.close();
	return cellcount;
}
public static String getCellData(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
{
	fi=new FileInputStream(xlfile);
	wb=new XSSFWorkbook(fi);
	sh=wb.getSheet(xlsheet);
	row=sh.getRow(rownum);
	cell=row.getCell(colnum);
	String data;
	try
	{
	DataFormatter formatter=new DataFormatter();
	String celldata=formatter.formatCellValue(cell);
	return celldata;
	}
	catch (Exception e) {
		data="";
	}
	wb.close();
	fi.close();
	return data;
}
public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
{
	fi=new FileInputStream(xlfile);
	wb=new XSSFWorkbook(fi);
	sh=wb.getSheet(xlsheet);
	row=sh.getRow(rownum);
	cell=row.createCell(colnum);
	cell.setCellValue(colnum);
	fo=new FileOutputStream(xlfile);
	wb.write(fo);
	fi.close();
	fo.close();
	
	
}
}
