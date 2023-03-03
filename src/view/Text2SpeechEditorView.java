package view;

import commands.*;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Document;
import model.TTSFacade;


// This is the controller class
public class Text2SpeechEditorView {
	// constructor method

	private TTSFacade facade = TTSFacade.getInstance();
	private Document document = Document.getInstance();
	private TextArea documentText;
	@FXML
	private BorderPane borderPane;
	@FXML
	private Button textToSpeechBtn;
	@FXML
	private ToggleButton recordBtn;
	@FXML 
	private Button replayRecordBtn;

	@FXML
	private Slider volumeSlider;
	@FXML
	private Slider pitchSlider;
	
	@FXML
	private Button slowForwardBtn;
	@FXML
	private Button fastForwardBtn;

	
	
	
	// we will need to get this instance from anywhere
	private static Text2SpeechEditorView instance;
	
	public Text2SpeechEditorView() {
//		invoker = new Invoker();
		instance = this;		
	}
	
	
	@FXML
	public void openFile(ActionEvent event) {
		OpenDocument openDoc = new OpenDocument();
		//Invokes a window for encoding choice
		String fileData[] = openDoc.getFileData(); 
		
		if(fileData==null) {
			System.out.println("Thats null");
			return;
		}
		
		
		EncodingSelectionWindow encodingWindow = new EncodingSelectionWindow();
		String encodingChoice = encodingWindow.getEncodingChoice();
		
		this.setTextArea();
		
		document.setFileName(openDoc.getFileName());
		document.setFileType(openDoc.getFileType());
		document.setEncoding(encodingChoice);
		document.setCanonicalPath(fileData[1]);
		document.setIsOpen(true);
		
		//performAction
		openDoc.actionPerformed();
		
		this.displayDocumentToScreen(document.getStringContents());
		
		this.addChangeTextListener();
		// enable buttons that cannot be pressed if there is not an opened file
		fastForwardBtn.setDisable(false);
		slowForwardBtn.setDisable(false);
		textToSpeechBtn.setDisable(false);
		
	}
	
	@FXML
	public void saveFile(ActionEvent event) {
		SaveDocument saveDoc = new SaveDocument();
		saveDoc.actionPerformed();		
	}
	
	@FXML
	public void saveAsFile(ActionEvent event) {
		SaveAsDocument saveAsDoc = new SaveAsDocument();
		boolean closedUnexpectedly = saveAsDoc.setFileData();
		
		if(closedUnexpectedly) {
			System.out.println("Thats null");
			return;
		}
		
		EncodingSelectionWindow encodingWindow = new EncodingSelectionWindow();
		String encodingChoice = encodingWindow.getEncodingChoice();
		
		saveAsDoc.setEncoding(encodingChoice);
		saveAsDoc.actionPerformed();
		
	}
	@FXML
	public void convertTextToSpeech(ActionEvent event) {
		// if there is selected text convert that text
		String conversionText = documentText.getSelectedText();
				
		// if not, convert the whole document
		if(conversionText.equals("")) {
			conversionText = document.getStringContents();
		}
		
		DocumentToSpeech docToSpeech = new DocumentToSpeech(conversionText);
		docToSpeech.actionPerformed();
		
	}
	
	@FXML
	public void handleRecord(ActionEvent event) {
		
		if(recordBtn.isSelected()) {
			StartRecording startRec = new StartRecording();
			startRec.actionPerformed();
			recordBtn.setText("StopRecord");
		}else {
			StopRecording stopRec = new StopRecording();
			stopRec.actionPerformed();
			recordBtn.setText("Record");
		}
		
		//toggle button after operation
		this.toggleButton(replayRecordBtn);
		
	}
		
	@FXML
	public void handleReplay(ActionEvent event) {
		ReplayCommand replay = new ReplayCommand();
		replay.actionPerformed();
	}
	
	
	
	@FXML
	public void handleVolume(MouseEvent event) {
		float sliderValue = (float)volumeSlider.getValue();
		facade.setVolume(sliderValue);
	}
	
	@FXML
	public void handlePitch(MouseEvent event) {
		float sliderValue = (float)pitchSlider.getValue();
		facade.setPitch(sliderValue);
	}
	
	@FXML
	public void handleSpeechRate(ActionEvent event) {
		String buttonName = ((ButtonBase)event.getSource()).getText();
		if(buttonName.equals("Fast")) {
			facade.incrementRate();
		}else {
			facade.decrementRate();
		}
		facade.setRate();
	}
	
	@FXML
	public void handleCloseButtonAction(ActionEvent event) {
	    Stage stage = Main.getMainWindow();
	    stage.close();
	}
	
	
	
	@FXML
	public void handleAbout(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText("A simple Text To Speech Application");
		alert.setContentText("For the course of Software Engineering");
		alert.showAndWait();
	}

	
	// EditDocument / for every change we make on the editor
	public void addChangeTextListener() {
		// create edit document once
		EditDocument editCommand = new EditDocument();
		
		//add a listener
		documentText.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
	            // this will run whenever text is changed
	        	editCommand.setNewText(newValue);
	        	editCommand.actionPerformed();	  
	        }
	    });
	}
	
	// Sets text area to the editor
	public void setTextArea() {
		documentText = new TextArea();
		borderPane.setCenter(documentText);
		documentText.setWrapText(true);
	}
	
	// Displays the text to the gui
	public void displayDocumentToScreen(String contents) {
		documentText.setText(contents);
		documentText.setFont(Font.font ("Verdana", 14));
		
	}
	

	// toggles the button between enable and disable state
	public void toggleButton(ButtonBase button) {
		;
		if(button.isDisabled()) {
			button.setDisable(false);
		}else {
			button.setDisable(true);
		}
	}		

	
	public static Text2SpeechEditorView getInstance() {
		if(instance == null) {
			instance = new Text2SpeechEditorView();
		}
		return instance;
	}

	
}





