package edu.bk;

import java.io.IOException;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class SecondaryController extends Thread{
	private Button[] button = new Button[100];
	private int mode = 0;
	private int index2 = 0;
	@FXML
	GridPane grid = new GridPane();
	@FXML
    VBox vboxlayout = new VBox();
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    private void OptionGame1() {
        mode = 3;
        setButton(mode);
    }
    @FXML
    private void OptionGame2() {
        mode = 4;
        setButton(mode);
    }
    @FXML
    private void OptionGame3() {
        mode = 5;
        setButton(mode);
    }
    @FXML
    private void aplusAlgorithm(){
        int index = 1;
        int[][] aa = new int[mode + 1][mode + 1];
        for(int i = 0; i < mode; i++){
            for(int j = 1; j <= mode; j++){
                if(button[index].getText().equals(" ")){
                    aa[i][j] = mode * mode;
                    index++;
                    continue;
                }
                aa[i][j] = Integer.parseInt(button[index].getText());
                index++;
            }
        }
        Matrix a = new Matrix(aa, mode);
        a.display();
        Solution solution = new Solution();
        solution.Solve(a);
        String[] solutionarray = solution.getArraySolution();

    }
    private void setButton(int size){
        int size2 = size * size;
        vboxlayout.getChildren().clear();
        vboxlayout.setSpacing(10);
        int index = 1;
        for(int i = 0; i < mode; i++){
            HBox newHbox = new HBox();
            newHbox.setSpacing(10);
            newHbox.setPrefSize(vboxlayout.getPrefWidth(), vboxlayout.getPrefHeight() / 3 - 5);
            for(int j = 0; j < mode; j++){
                button[index] = new Button();
                button[index].setText(String.valueOf(index));
                button[index].getStyleClass().add("button2");
                button[index].getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
                int x = index % size, y;
                if(x == 0){
                    x = size;
                    y = index / size;
                } else {
                    y = index / size + 1;
                }
                int finalX = x;
                int finalY = y;
                int finalI= index;
                int finalsize = size;
                button[index].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if(finalX > 1){
                            int indexl = (y  -  1)* finalsize + finalX - 1;
                            if(button[indexl].getText().equals(" ")){
                                button[indexl].setText(button[finalI].getText());
                                button[finalI].setText(" ");
                                return;
                            }
                        }
                        if(finalY > 1){
                            int indexl = finalsize * (y - 2) + finalX;
                            if(button[indexl].getText().equals(" ")){
                                button[indexl].setText(button[finalI].getText());
                                button[finalI].setText(" ");
                                return;
                            }
                        }
                        if(finalX < finalsize){
                            int indexl = (y - 1) * finalsize + finalX + 1;
                            if(button[indexl].getText().equals(" ")){
                                button[indexl].setText(button[finalI].getText());
                                button[finalI].setText(" ");
                                return;
                            }
                        }
                        if(finalY < finalsize){
                            int indexl = y * finalsize + finalX;
                            if(button[indexl].getText().equals(" ")){
                                button[indexl].setText(button[finalI].getText());
                                button[finalI].setText(" ");
                                return;
                            }
                        }
                    }
                });
                button[index].setPrefSize(newHbox.getPrefWidth() / 3 - 5,newHbox.getPrefHeight());
                newHbox.getChildren().add(button[index]);
                //System.out.println(index);
                if(index == mode * mode){
                    button[index].setText(" ");
                }
                index++;
            }
            vboxlayout.getChildren().add(newHbox);
        }
    }
    private void setMatrix(String matrixstring){
        for(int i = 1; i <= mode * mode; i++){
            button[i].setText(String.valueOf(matrixstring.charAt(i -1)));
        }
    }
}