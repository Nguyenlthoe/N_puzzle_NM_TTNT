package edu.bk;

import java.io.IOException;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

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
        vboxlayout.getChildren().clear();
        int index = 1;
        for(int i = 0; i < 3; i++){
            HBox newHbox = new HBox();
            newHbox.setSpacing(10);
            newHbox.setPrefSize(vboxlayout.getPrefWidth(), vboxlayout.getPrefHeight() / 3 - 5);
            for(int j = 0; j < 3; j++){
                button[index] = new Button();
                //button[index].setText(String.valueOf(index));
                button[index].setPrefSize(newHbox.getPrefWidth() / 3 - 5,newHbox.getPrefHeight());
                newHbox.getChildren().add(button[index]);
                System.out.println(index);
                index++;
            }
            vboxlayout.getChildren().add(newHbox);
        }
        String firstmatrix = "2415386 7";
        for(int i = 1; i < 10; i++){
            button[i].setText(String.valueOf(firstmatrix.charAt(i-1)));
        }
    }
    @FXML
    private void OptionGame2() {
        vboxlayout.getChildren().clear();
        mode = 4;
        int index = 1;
        for(int i = 0; i < 4; i++){
            HBox newHbox = new HBox();
            newHbox.setSpacing(10);
            newHbox.setPrefSize(vboxlayout.getPrefWidth(), vboxlayout.getPrefHeight() / 3 - 5);
            for(int j = 0; j < 4; j++){
                button[index] = new Button();
                button[index].setText(String.valueOf(index));
                button[index].setPrefSize(newHbox.getPrefWidth() / 3 - 5,newHbox.getPrefHeight());
                newHbox.getChildren().add(button[index]);
                System.out.println(index);
                index++;
            }
            vboxlayout.getChildren().add(newHbox);
        }
    }
    @FXML
    private void OptionGame3() {
        vboxlayout.getChildren().clear();
        int index = 1;
        mode = 5;
        for(int i = 0; i < 5; i++){
            HBox newHbox = new HBox();
            newHbox.setSpacing(10);
            newHbox.setPrefSize(vboxlayout.getPrefWidth(), vboxlayout.getPrefHeight() / 3 - 5);
            for(int j = 0; j < 5; j++){
                button[index] = new Button();
                button[index].setText(String.valueOf(index));
                button[index].setPrefSize(newHbox.getPrefWidth() / 3 - 5,newHbox.getPrefHeight());
                newHbox.getChildren().add(button[index]);
                System.out.println(index);
                index++;
            }
            vboxlayout.getChildren().add(newHbox);
        }
    }
    @FXML
    private void aplusAlgorithm(){
        int index = 1;
        int[][] aa = new int[mode + 1][mode + 1];
        for(int i = 0; i < mode; i++){
            for(int j = 1; j <= mode; j++){
                if(button[index].getText().equals(" ")){
                    aa[i][j] = 9;
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
//        for(int i = 0; i < solutionarray.length; i++){
//            System.out.println(solutionarray[i]);
//        }


//        index2 = 0;
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                if(index2 == solutionarray.length - 1){
//                    cancel();
//                }
//                for(int i = 0; i < solutionarray.length; i++){
//                    setMatrix(solutionarray[i]);
//                }
//                index2++;
//            }
//        };
//        Timer timer = new Timer("Timer");
//        timer.schedule(timerTask, 1000);

//        for(int j = 0; j < solutionarray.length; j++) {
//            try{
//                setMatrix(solutionarray[j]);
//                Thread.sleep(500);
//            } catch (InterruptedException e){
//                System.out.println("time out");
//            }
//        }

    }
    private void setMatrix(String matrixstring){
        for(int i = 1; i <= mode * mode; i++){
            button[i].setText(String.valueOf(matrixstring.charAt(i -1)));
        }
    }
}