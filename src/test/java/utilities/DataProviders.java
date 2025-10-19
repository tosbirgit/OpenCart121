package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	
	//data Provider 1
	
@DataProvider(name="LoginData")
public String[][] getData() throws IOException
{
	String path =".\\TestData\\Opencart_loginData.xlsx"; //taking excel file from test data
	
	
	ExcelUtility xlutil = new ExcelUtility(path);   //creating object for excel utility
	
	int totalrows = xlutil.getRowCount("Sheet1");
	int totalcolm = xlutil.getCellcount("Sheet1",1);
	
	String logindata [][]= new String [totalrows][totalcolm]; //created two dimentional array which can store 
	for(int i=1; i<=totalrows;i++)       //1
	{
		for(int j=0;j<totalcolm;j++)          //0 i is the row and j is the column
		{
			logindata [i-1][j] = xlutil.getCelldata("Sheet1", i, j);   //0,1
		}
	} 
	
	
	return logindata;
}
//data Provider 2


//data Provider 2


//data Provider 2
	
	
}
