package commands;







/*
 * Updates the contents of document for every change
 */
public class EditDocument extends CommandDocument{
	
	
	String text;
	
	public EditDocument() {
		
	}
	
	public void setNewText(String newText) {
		text = newText;
	}
	
	public void actionPerformed() {
		// update text document
		document.setTextContents(text);
		
	}
	
	
}
