package model;



import input.*;
import output.DocumentWriter;
import output.DocumentWriterFactory;


public class Document {
	private String fileName;
	private String fileType;
	private String encoding;
	private String canonicalPath;
	
	private boolean isOpen;

	private String contentsTextForm;
	
	private DocumentReaderFactory docReaderFactory = new DocumentReaderFactory();
	private DocumentWriterFactory docWriterFactory = new DocumentWriterFactory();
	// Hold single instance during runtime
	private static Document instance;
	
	private TTSFacade audioManager;
	
	public Document() {
		instance = this;
		audioManager = TTSFacade.getInstance();
	}
	public Document(String fileName, String fileType, String encoding) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.encoding = encoding;
	}
	
	
	// opens file document
	public void open() {
		
		// when opening we should initialize to empty string
		contentsTextForm = "";
		
		// creates a suitable reader for our case
		DocumentReader reader = docReaderFactory.createReader(fileType, encoding);
		contentsTextForm = reader.read(canonicalPath);
		
	}
	
	// save document to disk
	public void save() {
		DocumentWriter writer =  docWriterFactory.createWriter(fileType, encoding);
		writer.save(canonicalPath, contentsTextForm);
	}
	
	public void saveAs(String fileName, String fileType, String encoding, String canonicalPath) {
		DocumentWriter writer =  docWriterFactory.createWriter(fileType, encoding);
		writer.save(canonicalPath, contentsTextForm);
	}
	
	public void playContents(String textToSpeech) {
		audioManager.play(textToSpeech);
	
	}
	
	// SETTERS
	public void setAudioManager(TTSFacade facade) {
		audioManager = facade;
	}
	
	public void setTextContents(String textContents) {
		contentsTextForm = textContents;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	public void setCanonicalPath(String canonicalPath) {
		this.canonicalPath = canonicalPath;
	}
	
	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	// GETTERS
	public String getFileName(String fileName) {
		return fileName;
	}
	
	public String getFileType(String fileType) {
		return fileType;
	}
	
	public String getEncoding(String encoding) {
		return encoding;
	}
	
	public String getCanonicalPath(String canonicalPath) {
		return canonicalPath;
	}
	
	public String getStringContents() {
		return contentsTextForm;
	}
	
	public boolean getIsOpen() {
		return isOpen;
	}
	
	
	// Singleton pattern
	public static Document getInstance() {
		if(instance==null) {
			instance = new Document();
		}
		return instance;
	}
	
	// Debug method. Prints contents to console
	public void printContents() {
		System.out.println(contentsTextForm);
	}
}
