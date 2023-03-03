package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EncodingSelectionWindow {
	
	private Label encodingMessageLabel;
	// can add aditional encodings easily
	private String encodingNames[] = {"None","Rot-13","Atbash"};
	private MenuItem encodingsMenu[];
	private MenuButton menu;
	private Button submitButton;
	
	public EncodingSelectionWindow(){
		// initialize
		submitButton = new Button("OK");
		encodingMessageLabel = new Label("Please choose encoding:");
		encodingsMenu = new MenuItem[encodingNames.length];
		
		//Create menu items
		for(int i = 0; i < encodingNames.length; i++) {
			encodingsMenu[i] = new MenuItem(encodingNames[i]);
		}
		
		menu = new MenuButton("None",null,encodingsMenu);
		
		for(int i = 0; i < encodingNames.length; i++) {
			// thesse variables need to be final so can be seen by inner class
			final int index = i;
			final MenuItem currentItem = encodingsMenu[i];
			currentItem.setOnAction(e -> {
				menu.setText(encodingsMenu[index].getText());
				
			});
		}
		
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		
     
        root.getChildren().addAll(encodingMessageLabel,menu,submitButton);        
        VBox.setMargin(encodingMessageLabel, new Insets(0,0,10,0));
        VBox.setMargin(submitButton, new Insets(10,0,0,0));
        
        Scene scene = new Scene(root, 200, 200);
        Stage stage = new Stage();
        
        submitButton.setOnAction(e->{
			stage.close();
		});
        stage.setTitle("Encoding Selection");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
		
	}
	
	
	public String getEncodingChoice() {
		return menu.getText();
	}

}
