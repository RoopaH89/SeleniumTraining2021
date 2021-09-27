package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public String[][] readExcel(String filename) throws  IOException
	{
		XSSFWorkbook wb= new XSSFWorkbook("./data/" +filename+ ".xlsx");
		XSSFSheet sh=wb.getSheet("Sheet1");
		
		int rowCount=sh.getLastRowNum();
		int columnCount=sh.getRow(0).getLastCellNum();
		System.out.println("No.of Rows: "+rowCount);
		System.out.println("No.of Columns: "+columnCount);
		
		String[][] data=new String[rowCount][columnCount];
		
		for(int i=1;i<=rowCount;i++)
		{
			XSSFRow row=sh.getRow(i);
			
			for(int j=0;j<columnCount;j++)
			{
				XSSFCell cell=row.getCell(j);
				String value=cell.getStringCellValue();
				data[i-1][j]=value;
			}
		}
		return data;
	}
}
