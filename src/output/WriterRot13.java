package output;

public class WriterRot13 extends WriterDecorator {
	public WriterRot13(DocumentWriter writerComponent) {
		super(writerComponent);
	}
	
	
	// encode the data before calling the respective writer
	public void save(String canonicalPath, String data) {
		data = rot13Encrypt(data);
		writerComponent.save(canonicalPath, data);
	}
	
	public String rot13Encrypt(String message)
    {
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
