package input;

public class ReaderDecorator implements DocumentReader{
	
	protected DocumentReader readerComponent;
	
	public ReaderDecorator(DocumentReader readerComponent) {
		this.readerComponent = readerComponent;
	}
	
	@Override
	public String read(String canonicalPath) {
		return readerComponent.read(canonicalPath);
	}

	
}
