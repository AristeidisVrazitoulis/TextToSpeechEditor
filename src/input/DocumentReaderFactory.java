package input;

public class DocumentReaderFactory {

	public DocumentReaderFactory() {
		
	}
	
	public DocumentReader createReader(String readerType, String encoding) {
		
		DocumentReader reader = null;
		ReaderDecorator decorator = null;
		
		switch(readerType) {
			case "txt":
				reader = new TextReader();
				break;
			case "docx":
				reader = new WordReader();
				break;
			case "xlsx":
				reader = new ExcelReader();
				break;
		}
		
		if(reader == null) {
			System.out.println("file type not acceptable");
		}
		
		switch(encoding) {
			
			case "None":
				decorator = new ReaderDecorator(reader);
				break;
			case "Atbash":
				decorator = new ReaderAtbash(reader);
				break;
			case "Rot-13":
				decorator = new ReaderRot13(reader);	
				break;
		}
		
		if(decorator == null) {
			System.out.println("encryption type not found");
		}
		
		return decorator;
	}
}
