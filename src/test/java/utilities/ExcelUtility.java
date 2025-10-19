package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility 
{

	    public static FileInputStream fi;
		public static FileOutputStream fo;
		public static XSSFWorkbook workbook;
		public static XSSFSheet sheet;
		public static XSSFRow row;
		public static XSSFCell cell;
		public static CellStyle style;
		static  String path;
		
		public ExcelUtility(String path)
		{
			this.path= path;
		}
		
		public static int getRowCount(String sheetName) throws IOException
		{   
			fi =new FileInputStream(path);
			workbook = new XSSFWorkbook(fi);
			sheet= workbook.getSheet(sheetName);
			int rowcount = sheet.getLastRowNum();
			workbook.close();
			fi.close();
			return rowcount;
		}
		
		public static int getCellcount(String sheetName,int rownum) throws IOException
		{
			fi =new FileInputStream(path);
			workbook = new XSSFWorkbook(fi);
			sheet= workbook.getSheet(sheetName);
			row=sheet.getRow(rownum);
			int cellcount =row.getLastCellNum();
			workbook.close();
			fi.close();
			return cellcount;
		}
		public static String getCelldata(String sheetName,int rownum,int colnum) throws IOException
		{
			fi =new FileInputStream(path);
			workbook = new XSSFWorkbook(fi);
			sheet= workbook.getSheet(sheetName);
			row =sheet.getRow(rownum);
			cell=row.getCell(colnum);
			DataFormatter formatter = new DataFormatter();
			String data;
			try 
			{
				data= cell.toString();
				
				data= formatter.formatCellValue(cell);
			}
			catch(Exception e)
			{
				data ="";
				
			}
			workbook.close();
			fi.close();
			return data;
		}
		
		public static void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
		{
			File xlfile = new File(path);
			if (!xlfile.exists())         //if file does not exists then create new file
			{
				workbook = new XSSFWorkbook();
				fo = new FileOutputStream(path);
				workbook.write(fo);
			}
			fi =new FileInputStream(path);
			workbook = new XSSFWorkbook(fi);
			
			if(workbook.getSheetIndex(sheetName)==-1) //if sheet does nor exist then create new sheet
			workbook.createSheet(sheetName);
			sheet= workbook.getSheet(sheetName);
			
			if (sheet.getRow(rownum)==null); //if row does nor exist then create new row
			sheet.createRow(rownum);
			row =sheet.getRow(rownum);
			
			
			cell=row.createCell(colnum);
			cell.setCellValue(data);
			fo = new FileOutputStream(path);
			workbook.write(fo);
			workbook.close();
			fi.close();
			fo.close();
		}
		//https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india-sbi/fixed-deposit-calculator-SBI-BSB001.html?classic=true

	}


