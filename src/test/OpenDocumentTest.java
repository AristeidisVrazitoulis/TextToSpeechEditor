package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import commands.OpenDocument;
import input.DocumentReader;
import input.DocumentReaderFactory;
import model.Document;

class OpenDocumentTest {

	private static Document document;
	private static String testFilesPath;
	private static DocumentReaderFactory readerFactory;
	
	@BeforeAll
	public static final void setUp() {
		System.out.println("initialized");
		document = new Document();
		readerFactory = new DocumentReaderFactory();
		testFilesPath = "C:/Users/Aristeidis/Documents/sem/SoftwareEngineering/TextToSpeechEditor/TestFiles/";
	}
	
	@ParameterizedTest
	@CsvSource({
		"atbashWord, docx, Atbash",
		"Test, docx, None",
		"addresses, xlsx, None",
		"rot13test, txt, Rot-13"
		
	})
	public final void testOpenDocument(String fileName, String fileType, String encoding) {
		OpenDocument openDoc = new OpenDocument();
		String canonical = testFilesPath + fileName+"."+fileType;
		
		document.setCanonicalPath(canonical);
		document.setEncoding(encoding);
		document.setFileType(fileType);
		
		openDoc.actionPerformed();
		
		DocumentReader reader = readerFactory.createReader(fileType, encoding);
		
		// check document object contents with file contents
		assertEquals(document.getStringContents(), reader.read(canonical));
	
		
	}

}
