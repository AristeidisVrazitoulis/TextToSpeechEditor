package input;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;


import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader implements DocumentReader {
	


	public ExcelReader() {
		
	}
	
	@Override
	public String read(String canonicalPath){
		String excelText = "";
		try {
			FileInputStream inputStream = new FileInputStream(new File(canonicalPath));  
	        Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0); // the first excel sheet is in all probability the one that the user wants
	        Iterator<Row> rowIterator = firstSheet.iterator();
	        
	        while(rowIterator.hasNext()) {
	        	Row nextRow = rowIterator.next();
	        	String rowString = "";
	        	int firstCell = 1;
	            Iterator<Cell> cellIterator = nextRow.cellIterator();
	            
	            while (cellIterator.hasNext()) {
	            	if(firstCell == 0) {
	            		rowString += " - ";
	            	}
	            	firstCell = 0;
	            	Cell cell = cellIterator.next();
	            	//CellType type = cell.getCellType();
	            	rowString += cell;
	            }
	            
	     
	            excelText += rowString + "\n";
	            
	        }
	        
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		excelText = excelText.substring(0, excelText.length() - 1);
        
        return excelText;
        
	}
	

	
}