package commands;

import java.io.File;
import java.io.IOException;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import view.EncodingSelectionWindow;


public class SaveAsDocument extends CommandDocument {
	
	
	String chosenFileNameCanonical, chosenFileName, encoding;
	String fileNameClean, fileSuffix;
	
	public SaveAsDocument() {

	}
	
	
	public boolean setFileData() {
		
		// Create a file chooser
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Select a file:");
		// available file types
		chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".docx", "*.docx"),
											 new FileChooser.ExtensionFilter(".xlsx", "*.xlsx"),
											 new FileChooser.ExtensionFilter(".txt", "*.txt"));
		
		
		
		File file = chooser.showSaveDialog(new Stage());
		
		// check for possible errors
		if(file == null) 
		{
			System.out.println("File null..");
			return true;
		}
		else 
		{
			try {
				chosenFileName = file.getName();
				String fullName[] = chosenFileName.split("\\.");
				fileNameClean = fullName[0];
				fileSuffix = fullName[1];
				chosenFileNameCanonical = file.getCanonicalPath();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	
	@Override 
	public void actionPerformed() {
		
		if(document.getIsOpen()) {
			document.saveAs(fileNameClean,fileSuffix,encoding,chosenFileNameCanonical);
		}
		
	}
	
	
	public String getFileName() {
		return fileNameClean;
	}
	
	public String getFileType() {
		return fileSuffix;
	}
	
	public void setEncoding(String enc) {
		encoding = enc;
	}
	
	
	
	

}
