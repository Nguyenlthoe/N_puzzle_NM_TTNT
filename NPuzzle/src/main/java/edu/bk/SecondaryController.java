package edu.bk;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class SecondaryController {
	private Button[] button = new Button[100]; 
	@FXML
	GridPane grid = new GridPane();
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    private void OptionGame1() {
    
    }
    @FXML
    private void OptionGame2() {
    	
    }
    @FXML
    private void OptionGame3() {
    	
    }
}