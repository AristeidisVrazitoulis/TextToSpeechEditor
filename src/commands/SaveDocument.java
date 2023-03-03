package commands;



public class SaveDocument extends CommandDocument {
	
	
	
	public SaveDocument() {
		
	}
	
	
	@Override 
	public void actionPerformed() {
		// save only if document has opened
		if(document.getIsOpen()) {
			document.save();
		}
	}
}


