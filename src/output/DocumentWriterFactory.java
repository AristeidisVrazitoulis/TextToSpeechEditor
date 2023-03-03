package output;


public class DocumentWriterFactory {
	
	public DocumentWriterFactory() {
			
	}
		
	public DocumentWriter createWriter(String writerType, String encoding) {
		
		DocumentWriter writer = null;
		WriterDecorator decorator = null;
		
		switch(writerType) {
			case "txt":
				writer = new TextWriter();
				break;
			case "docx":
				writer = new WordWriter();
				break;
			case "xlsx":
				writer = new ExcelWriter();
				break;
		}
		
		if(writer == null) {
			System.out.println("file type not acceptable");
		}
		
		switch(encoding) {
			
			case "None":
				decorator = new WriterDecorator(writer);
				break;
			case "Atbash":
				decorator = new WriterAtbash(writer);
				break;
			case "Rot-13":
				decorator = new WriterRot13(writer);	
				break;
		}
		
		if(decorator == null) {
			System.out.println("encryption type not found");
		}
		
		return decorator;
	}
}