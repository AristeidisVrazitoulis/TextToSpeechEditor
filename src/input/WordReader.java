package input;

import java.util.List;

import java.io.FileInputStream;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;



public class WordReader implements DocumentReader{
	

	
	public WordReader() {

	}
	
	@Override
	public String read(String canonicalPath) {
		String wordText = "";
		
		try {
			FileInputStream fis = new FileInputStream(canonicalPath);
			XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
			
			XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(xdoc); // headers and footers

			XWPFHeader header = policy.getDefaultHeader();
			if (header != null) {
				wordText += header.getText();
			}

			XWPFFooter footer = policy.getDefaultFooter();
			if (footer != null) {
				wordText += footer.getText();
			}

			List<XWPFParagraph> paragraphList = xdoc.getParagraphs(); // paragraphs
			//TODO: Tables
			
			//fills the list of paragraphs by lines
			for (XWPFParagraph paragraph : paragraphList) {


				String paragraphText[] = paragraph.getText().split("\n");
				
				for(String line : paragraphText) {
					wordText += line + "\n";
				}
				wordText = wordText.substring(0, wordText.length() - 1);
				
				
				
				}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return wordText;
	}
	

}
