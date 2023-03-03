package output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class TextWriter implements DocumentWriter {
	
	public void save(String canonicalPath, String data) {
		
		try {
			File destination = new File(canonicalPath);
			FileOutputStream outputStream = new FileOutputStream(destination);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
			
//			String[] lines = data.split("\n");
//			
//			for(String line : lines) {
//				writer.write(line);
//				writer.newLine();
//			}
			
			writer.write(data);
			
			writer.close();
			
	    } catch (FileNotFoundException e) {
		      e.printStackTrace();
	    } catch (IOException e) {
			e.printStackTrace();
		}
	}
}
