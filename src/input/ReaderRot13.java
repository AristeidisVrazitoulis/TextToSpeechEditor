package input;

public class ReaderRot13 extends ReaderDecorator{
	
	public ReaderRot13(DocumentReader readerComponent) {
		super(readerComponent);
	}
	
	// decode
	public String read(String canonicalPath) {
		String message = readerComponent.read(canonicalPath);
		
		StringBuilder encoded = new StringBuilder();
	       
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
            	if       (c >= 'a' && c <= 'm') c += 13;
                else if  (c >= 'A' && c <= 'M') c += 13;
                else if  (c >= 'n' && c <= 'z') c -= 13;
                else if  (c >= 'N' && c <= 'Z') c -= 13;
            	encoded.append((char) c);
            } else {
                encoded.append(c);
            }
        }
        return encoded.toString();
    }
		

}
