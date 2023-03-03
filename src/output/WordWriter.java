package output;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;


public class WordWriter implements DocumentWriter {

	public void save(String canonicalPath, String data) {
		
		 try (XWPFDocument doc = new XWPFDocument()) {

			 	XWPFParagraph p1 = doc.createParagraph();
	            p1.setAlignment(ParagraphAlignment.LEFT);

	            // set font
	            XWPFRun r1 = p1.createRun();
	            r1.setFontSize(13);
	            r1.setFontFamily("New Roman");
	            r1.setText(data);

	            // save it to .docx file
	            try (FileOutputStream out = new FileOutputStream(canonicalPath)) {
	                doc.write(out);
	            }

	        } catch (IOException e) {
				e.printStackTrace();
			}
	
	}
	
}
