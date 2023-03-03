package output;


public class WriterAtbash extends WriterDecorator{
										  
															
	public WriterAtbash(DocumentWriter writerComponent) {
		super(writerComponent);
	}
	
	
	// encode the data before calling the respective writer
	public void save(String canonicalPath, String data) {
		data = atbashEncrypt(data);
		writerComponent.save(canonicalPath, data);
	}
	
	public String atbashEncrypt(String message)
    {
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

