package edu.bk;

import java.util.ArrayList;

public class Solution {
    private ArrayList<Matrix> open = new ArrayList<Matrix>();
    private ArrayList<String> closematrixs = new ArrayList<String>();
    public void Solve(Matrix a){
        open.add(a);
        closematrixs.add(a.getClosedMatrix());
        System.out.println(a.getClosedMatrix());
        while (open.size() > 0){
            Matrix aa = open.get(0);
            open.remove(0);
            if(aa.getMtdistance() == 0){
                System.out.println(aa.getMoveDirection());
                break;
            }
            if(aa.moveBot() == true){
                Matrix bot = new Matrix();
                bot.isParentMoveBot(aa);
                if(checkclosed(bot.getClosedMatrix()) == true){
                    insertMatrix(bot);
                    //System.out.println(bot.getClosedMatrix());
                }
            }
            if(aa.moveTop() == true){
                Matrix top = new Matrix();
                top.isParentMoveTop(aa);
                if(checkclosed(top.getClosedMatrix()) == true){
                    insertMatrix(top);
                    //System.out.println(top.getClosedMatrix());
                }
            }
            if(aa.moveLeft() == true){
                Matrix left = new Matrix();
                left.isParentMoveLeft(aa);
                if(checkclosed(left.getClosedMatrix()) == true){
                    insertMatrix(left);
                    //System.out.println(left.getClosedMatrix());
                }
            }
            if(aa.moveRight() == true){
                Matrix right = new Matrix();
                right.isParentMoveRight(aa);
                if(checkclosed(right.getClosedMatrix()) == true){
                    insertMatrix(right);
                    //System.out.println(right.getClosedMatrix());
                }
            }
        }
    }
    public void insertMatrix(Matrix b){
        int index = 0;
        for(int i = 0; i < open.size(); i++){
            if(b.getAstar() > open.get(i).getAstar()){
                index++;
            } else {
                break;
            }
        }
        open.add(index, b);
    }
    public boolean checkclosed(String closedmatrix){
        for(int i = 0; i < closematrixs.size(); i++){
            if(closedmatrix.equals(closematrixs.get(i))){
                return false;
            }
        }
        return true;
    }
}
