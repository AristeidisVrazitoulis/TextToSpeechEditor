package input;

public class ReaderAtbash extends ReaderDecorator{

	
	public ReaderAtbash(DocumentReader readerComponent) {
		super(readerComponent);
	}
	
	
	// decode
	public String read(String canonicalPath) {
		String message = readerComponent.read(canonicalPath);
		StringBuilder encoded = new StringBuilder();
	       
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
            	int newChar = 0;
            	
            	if(Character.isLowerCase(c)) {
	                newChar = (char) (('z' - c) + 'a');
            	}
            	else {
            		newChar = (char) (('Z' - c) + 'A');
            	}
            	encoded.append((char) newChar);
            } else {
                encoded.append(c);
            }
        }
        return encoded.toString();
	}
	
}
