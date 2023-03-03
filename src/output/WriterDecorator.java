package output;


public class WriterDecorator implements DocumentWriter{

	protected DocumentWriter writerComponent;
	
	public WriterDecorator(DocumentWriter writerComponent) {
		this.writerComponent = writerComponent;
	}
	
	@Override
	public void save(String canonicalPath, String data) {
		writerComponent.save(canonicalPath, data);
	}
}
