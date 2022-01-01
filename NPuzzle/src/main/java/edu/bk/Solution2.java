package edu.bk;

import java.util.ArrayList;

import javafx.css.Size;

public class Solution2 {
    private ArrayList<Matrix> open = new ArrayList<Matrix>();
    private ArrayList<String> closematrixs = new ArrayList<String>();
    private String [] path;
    private String[] arraySolution;
    private int step = 1;
    private String string = "";
    private int mode;
    private String calc(Matrix mt) {
    	String s = "";
    	if(mt == null) {    		
    		for(int i = 0; i < mode; i++) {
    			if(i % 2 == 0) {
    				for(int j = 0; j < mode; j++) {
    					s += i * mode + j + 1 + "";
    				}
    			} else {
    				for(int j = 0; j < mode; j++) {
    					s += i * mode + mode - j + "";
    				}
    			}
    		}
    		return s;
    	} else {
    		for(int i = 0; i < mode; i++) {
    			if(i % 2 == 0) {
    				for(int j = 1; j <= mode; j++) {
    					s += mt.getValue()[i][j] + "";
    				}
    			} else {
    				for(int j = mode; j >= 1; j--) {
    					s += mt.getValue()[i][j] + "";
    				}
    			}
    		}
//    		System.out.println(s);
//    		System.exit(1);
    		return s;
    	}
    }
    
    public void Solve(Matrix a, int mode){
    	this.mode = mode;
        open.clear();
        closematrixs.clear();
		string = calc(null);
        open.add(a);
        closematrixs.add(a.getClosedMatrix());
        System.out.println(a.getClosedMatrix());
        while (open.size() > 0){
            Matrix aa = open.get(0);
            open.remove(0);
            if(aa.getMtdistance() == 0){
            	path = aa.getMoveDirection().split("-");
                System.out.println(aa.getMoveDirection());
//                System.out.println(aa.getParentString());
                arraySolution = aa.getParentString().replace("25", " ").split("[-]");
//                for(int i = 0; i < path.length; i++){
//                    System.out.println(path[i]);
//                }
                break;
            }
            if(aa.moveBot() == true){
                Matrix bot = new Matrix();
                bot.isParentMoveBot(aa);
                if(checkclosed(bot.getClosedMatrix()) == true){
                    insertMatrix(bot);
                    closematrixs.add(bot.getClosedMatrix());
                    //System.out.println(bot.getClosedMatrix());
                }
            }
            if(aa.moveTop() == true){
                Matrix top = new Matrix();
                top.isParentMoveTop(aa);
                if(checkclosed(top.getClosedMatrix()) == true){
                    insertMatrix(top);
                    closematrixs.add(top.getClosedMatrix());
                    //System.out.println(top.getClosedMatrix());
                }
            }
            if(aa.moveLeft() == true){
                Matrix left = new Matrix();
                left.isParentMoveLeft(aa);
                if(checkclosed(left.getClosedMatrix()) == true){
                    insertMatrix(left);
                    closematrixs.add(left.getClosedMatrix());
                    //System.out.println(left.getClosedMatrix());
                }
            }
            if(aa.moveRight() == true){
                Matrix right = new Matrix();
                right.isParentMoveRight(aa);
                if(checkclosed(right.getClosedMatrix()) == true){
                    insertMatrix(right);
                    closematrixs.add(right.getClosedMatrix());
                    //System.out.println(right.getClosedMatrix());
                }
            }
        }
    }
    public void insertMatrix(Matrix b){
        int index = 0;
//        for(int i = 0; i < open.size(); i++){
//            if(b.getAstar() > open.get(i).getAstar()){
//                index++;
//            } else {
//                if(b.getAstar() == open.get(i).getAstar()) {
//                	if(b.getBstar() > open.get(i).getBstar()) {
//                		index++;
//                	}
//                } else {
//                	break;
//                }
//            }
//        }
        for(int i = 0; i < open.size(); i++){
            if(b.getBstar() > open.get(i).getBstar() || b.getAstar() > open.get(i).getAstar()){
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
    public String[] getArraySolution() {
        return arraySolution;
    }
	public ArrayList<Matrix> getOpen() {
		return open;
	}
	public void setOpen(ArrayList<Matrix> open) {
		this.open = open;
	}
	public ArrayList<String> getClosematrixs() {
		return closematrixs;
	}
	public void setClosematrixs(ArrayList<String> closematrixs) {
		this.closematrixs = closematrixs;
	}
	public void setArraySolution(String[] arraySolution) {
		this.arraySolution = arraySolution;
	}
	public String[] getPath() {
		return path;
	}
	public void setPath(String[] path) {
		this.path = path;
	}
	
    
}
