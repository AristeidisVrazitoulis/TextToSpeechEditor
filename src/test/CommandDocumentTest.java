package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runners.Parameterized.Parameters;

import commands.DocumentToSpeech;
import commands.EditDocument;
import commands.OpenDocument;
import commands.ReplayCommand;
import commands.SaveDocument;
import commands.StartRecording;
import commands.StopRecording;
import input.DocumentReader;
import input.DocumentReaderFactory;
import model.Document;
import model.FakeTTSFacade;
import model.TTSFacade;
import view.Text2SpeechEditorView;

import junit.runner.Version;
class CommandDocumentTest {
	
	private static Document document;
	private static String testFilesPath;
	private static DocumentReaderFactory readerFactory;
	
	@BeforeAll
	public static final void setUp() {
		System.out.println("initialized");
		document = new Document();
		readerFactory = new DocumentReaderFactory();
		testFilesPath = "C:/Users/Aristeidis/Documents/sem/SoftwareEngineering/TextToSpeechEditor/TestFiles/";
		
		// this will be the file that we test
		String fileName = "Test";
		String fileType = "docx";
		String encoding = "None";
		OpenDocument openDoc = new OpenDocument();
		String canonical = testFilesPath + fileName+"."+fileType;
		
		document.setCanonicalPath(canonical);
		document.setEncoding(encoding);
		document.setFileType(fileType);
		
		//opendocument
		openDoc.actionPerformed();
	}
	

	
	
	// test edit command
	@ParameterizedTest
	@ValueSource(strings = {"change1","small change2","and this is a big change 3"})
	public final void testEditDocument(String editString) {
		
		EditDocument editDoc = new EditDocument();
		
		
		// Actual Text
		String changedText = document.getStringContents() + editString;
		editDoc.setNewText(changedText);
		
		editDoc.actionPerformed();
		
		// Expected Test
		String contents = document.getStringContents();
		
		assertEquals(changedText, contents);
		
	}
	
	@ParameterizedTest
	@CsvSource({
		"textTest, txt, Atbash",
		"wordTest, docx, None",
		"excelTest, xlsx, None",
		"textTest2, txt, Rot-13"
		
		})
	public final void testSaveDocument(String fileName, String fileType, String encoding) {
		SaveDocument saveDoc = new SaveDocument();
		
		
		String sampleText = "Software Engineering is cool";
		String canonical = testFilesPath + fileName;
		
		document.setTextContents(sampleText);
		document.setIsOpen(true);
		
		document.setEncoding(encoding);
		document.setFileType(fileType);
		document.setCanonicalPath(canonical);
		
		String actualText = document.getStringContents();
		
		// Save Document
		saveDoc.actionPerformed();
		
		DocumentReader reader = readerFactory.createReader(fileType, encoding);
		String expectedTest = reader.read(canonical);
		// object contents with file contents
		assertEquals(actualText, expectedTest);
		
	}
	
	// Whole document
	@Test
	public final void testDocumentToSpeech() {
		String text = document.getStringContents();
		DocumentToSpeech command =  new DocumentToSpeech(text);
		FakeTTSFacade facade = new FakeTTSFacade();
		document.setAudioManager(facade);

		command.actionPerformed();
		assertEquals(document.getStringContents(), facade.getPlayedContents());		
		
	}
	
	// Part of document
	@Test
	public final void testDocumentToSpeechPartially() {
		String text = document.getStringContents();
		text = text.substring(0,10);
		DocumentToSpeech command =  new DocumentToSpeech(text);
		FakeTTSFacade facade = new FakeTTSFacade();
		document.setAudioManager(facade);

		command.actionPerformed();
		assertEquals(text, facade.getPlayedContents());		
		
	}
	
	
	
	@Test
	public final void testParametersSpeech() {
		
		FakeTTSFacade facade = new FakeTTSFacade();
		
		facade.setPitch(150f);
		facade.setVolume(0.8f);
		facade.setWordsPerMinute(150);
		// compares the value we set with the value of the Voice object 
		assertEquals(facade.getPitch(), facade.getParentPitch(), 0.1f);
		assertEquals(facade.getVolume(), facade.getParentVolume(), 0.1f);
		assertEquals(facade.getWordsPerMinute(), facade.getParentWordsPerMinute(), 0.1f);
		
	}
	
	@Test
	public final void testRecordingCommand() {
		StartRecording record = new StartRecording();
		record.setDocument(document);
		// enable record
		record.actionPerformed();
		assertTrue(record.getRelayManager().isActiveRecording());
		
		
	}
	
	@Test
	public final void testReplayCommand() {
		
		String text = document.getStringContents();
		String text1 = text.substring(0,10);
		String text2 = text.substring(50,68);
		String text3 = text.substring(75,83);
		
		String texts[] = {text1,text2,text3};
		
		StartRecording record = new StartRecording();
		//TTSFacade
		FakeTTSFacade facade = new FakeTTSFacade();
		ReplayCommand replay = new ReplayCommand();
		
		
		
		// start recording
		record.actionPerformed();
		
		
		String desiredText = "";
		for(String thisText : texts) {
			DocumentToSpeech docToSpeech = new DocumentToSpeech(thisText);
			docToSpeech.actionPerformed();
			desiredText += thisText;
		}
		document.setAudioManager(facade);
		replay.actionPerformed();

		assertEquals(facade.getPlayedContents(), desiredText);
		
		
	
	}

	@Test
	public final void testEndRecording() {
		StartRecording start = new StartRecording();
		StopRecording stop = new StopRecording();
		// enable record
		start.actionPerformed();
		stop.actionPerformed();
		assertFalse(stop.getRelayManager().isActiveRecording());
		
	}
	
	

}
