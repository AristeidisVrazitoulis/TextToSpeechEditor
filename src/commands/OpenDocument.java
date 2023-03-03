package commands;

import java.io.File;
import java.io.IOException;

import javafx.stage.FileChooser;

import javafx.stage.Stage;
import view.EncodingSelectionWindow;


public class OpenDocument extends CommandDocument {
	
	private String fileNameClean, fileSuffix;
	
	public OpenDocument() {
				
	}
	
	
	// creates file chooser and returns fileName and FileNameCanonical
	public String[] getFileData() {
		// [0]: fileName
		// [1]: canonicalPath
		
		String fileData[] = new String[2];
		// Create a file chooser
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Select a file:");
		// available file types
		chooser.setInitialDirectory(new File("TestFiles"));
		chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".docx", "*.docx"),
											 new FileChooser.ExtensionFilter(".xlsx", "*.xlsx"),
											 new FileChooser.ExtensionFilter(".txt", "*.txt"));
		
		
		File file = chooser.showOpenDialog(new Stage());
		
		// check for possible errors
		if(file == null) 
		{
			System.out.println("File null..");
			return null;
		}
		else 
		{
			try {
				fileData[0] = file.getName();
				String fullName[] = fileData[0].split("\\.");
				fileNameClean = fullName[0];
				fileSuffix = fullName[1];
				fileData[1] = file.getCanonicalPath();
				System.out.println(fileData[1]);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return fileData;
		
	}
	
	
	public String getFileName() {
		return fileNameClean;
	}
	
	public String getFileType() {
		return fileSuffix;
	}
	
	
	@Override
	public void actionPerformed() {
		document.open();	
	}
	


	

	

}
