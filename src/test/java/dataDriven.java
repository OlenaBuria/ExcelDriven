import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {
	
	

	public ArrayList<String> getData(String testcaseName) throws IOException {
		// TODO Auto-generated method stub

		//fileInputStream argument
		
		ArrayList<String> a = new ArrayList<String>();
		
		FileInputStream fis = new FileInputStream("/Users/vburiol/Documents/Automation_Java/ExcelDriven/demoData.xlsx");	
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		int sheets = workbook.getNumberOfSheets();
		
		for (int i=0;i<sheets;i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase("testdata"))
			{	
			XSSFSheet sheet = workbook.getSheetAt(i);
			//Identify Testcases column by scanning the entire 1st row
			
			Iterator<Row> rows = sheet.iterator();
			Row firstrow = rows.next();
			Iterator<Cell> cell = firstrow.cellIterator();
			int k=0;
			int column =0;
			while (cell.hasNext())
			{
				Cell value = cell.next();
				if(value.getStringCellValue().equalsIgnoreCase("Test Cases"))
				{
					//desired column
					column=k;
				}
				k++;
			}
	
			System.out.println(column);
			
			//once column is identified then scan entire testcase column to identify purchase testcase row
			while (rows.hasNext())
			{
				Row r = rows.next();
				
				if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName))
					
				{
				//after you grab purchase testcase row = pull all the data of that row and feed into test
					
				Iterator<Cell> cv = r.cellIterator();
				while(cv.hasNext())
				{
				Cell c = cv.next();
				if (c.getCellTypeEnum()==CellType.STRING)	
				{
				a.add(c.getStringCellValue());
				}
				else 
				{
					a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
				}
				
				
				}
			}
					//return a;	
			}
			
			}
			
			//System.out.println(i);
		}
		return a;
		
	}
	
	public static void main(String[] args)
	{
		
	}

}