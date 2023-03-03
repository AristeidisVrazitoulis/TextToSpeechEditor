package output;


import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter implements DocumentWriter {

	public void save(String canonicalPath, String data) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet = workbook.createSheet("Saved sheet");
	        
	        String[] lines = data.split("\n");
	        
	        
	        int rowCount = 0;
	        
	        for(String line : lines) {
	        	String[] columns = line.split(" - ");
	        	Row row = sheet.createRow(rowCount++);
	        	
	        	int columnCount = 0;
	        	
	        	for(String column : columns) {
	        		Cell cell = row.createCell(columnCount++);
	        		cell.setCellValue(column);
	        	}
	        }
	        
	        FileOutputStream outputStream = new FileOutputStream(canonicalPath);
	        workbook.write(outputStream);
		}
        
        catch (IOException e) {
        	e.printStackTrace();
		}
	}
	
	
}
