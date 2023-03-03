package input;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class TextReader implements DocumentReader{
	
	
	public TextReader() {
		
	}
	
	@Override
	public String read(String canonicalPath){
		String text = "";
		try {
			File myObj = new File(canonicalPath);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
		        String row = myReader.nextLine();
		        if(!myReader.hasNextLine()) {
		        	text += row;
		        }else {
		        text += row + "\n";
		        }
			}
	    
			myReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
		return text;
	}
	
	

	

}