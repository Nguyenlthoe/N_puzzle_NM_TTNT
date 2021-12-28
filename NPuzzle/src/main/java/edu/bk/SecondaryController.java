package edu.bk;

import java.io.IOException;
import java.util.Collection;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class SecondaryController extends Thread{
	private Button[] button = new Button[100];
	private int mode = 0;
	private int index2 = 0;
	private GenMatrix genMatrix = new GenMatrix();
	private Matrix src = new Matrix();
	private int[] randomMT= new int[100];
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
    	//src = genMatrix.Solve(16, mode);
    	randommatrix();
        setButton(mode);
    }
    @FXML
    private void OptionGame2() {
        mode = 4;
    	//src = genMatrix.Solve(16, mode);
        randommatrix();
        setButton(mode);
    }
    @FXML
    private void OptionGame3() {
        mode = 5;
    	//src = genMatrix.Solve(16, mode);
        randommatrix();
        setButton(mode);
    }
    @FXML
    private void aplusAlgorithm(){
    	//setButton(mode);
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
                System.out.println(aa[i][j]);
                index++;
            }
        }
        Matrix a = new Matrix(aa, mode);
        a.display();
        Solution solution = new Solution();
        solution.Solve(a);
        String[] solutionarray = solution.getArraySolution();

    }
    @FXML
    private void heuAlgorithm(){
    	//setButton(mode);
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
        Heuristic heuristic = new Heuristic();
        heuristic.Solve(a, mode);
        String[] solutionarray = heuristic.getArraySolution();

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
                //button[index].setText(src.getValue()[i][j + 1] + "");
                button[index].setText(randomMT[index] + "");
                if(randomMT[index] == mode * mode){
                    button[index].setText(" ");
                }
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
                button[finalI].setOnAction(new EventHandler<ActionEvent>() {
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
//                if(src.getValue()[i][j + 1] == mode * mode){
//                    button[index].setText(" ");
//                }
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
    private void randommatrix(){
        int size2 = mode * mode;
        Random random = new Random();
        boolean[] check = new boolean[size2 + 1];
        while (true){
            for(int i = 1; i  <= size2 ; i++){
                check[i] = false;
            }
            for(int i = 1; i <= size2; i++){
                while (true){
                    int tmp = random.nextInt(size2) + 1;
                    if(check[tmp] == false){
                        randomMT[i] = tmp;
                        check[tmp] = true;
                        break;
                    }
                }
            }
            if(cansolve() == true){
                break;
            }
        }
    }
    private boolean cansolve(){
        int N = 0;
        int size2 = mode * mode;
        int indexx = 0;
        int tmp = 0;
        for(int i = 1; i <= size2; i++){
            tmp = 0;
            int k = randomMT[i];
            if(k == 1){
                continue;
            }
            if(k == size2){
                indexx = i;
                continue;
            }
            for(int j = i + 1; j <= size2; j++){
                if(k > randomMT[j]){
                    tmp++;
                }
            }
            N += tmp;
        }
        if(mode % 2 == 1){
            if(N % 2 == 0){
                return true;
            } else {
                return false;
            }
        } else {
            int kk;
            if(indexx % mode == 0){
                kk = indexx / mode;
            } else {
                kk = indexx / mode + 1;
            }
            if(N % 2 == 0){
                if(kk % 2 == 0){
                    return true;
                } else {
                    return false;
                }
            } else {
                if(kk % 2 == 1){
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
}