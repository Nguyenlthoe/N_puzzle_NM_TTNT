package edu.bk;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GenMatrix {
	private ArrayList<Matrix> open = new ArrayList<Matrix>();
    private ArrayList<String> closematrixs = new ArrayList<String>();
    private String[] arraySolution;
    public Matrix Solve(int num, int size){
        open.clear();
        closematrixs.clear();
        int[][] tmp = new int[size + 1][size + 1];
		for(int i = 0; i < size * size; i++) {
			tmp[i / size][(i % size) + 1] = i + 1;
		}
		Matrix a = new Matrix(tmp, size);
		a.display();
        open.add(a);
        closematrixs.add(a.getClosedMatrix());
        System.out.println(a.getClosedMatrix());
        while (num > 0){
            Matrix aa = open.get(0);
            num--;
			int mark = 0;
			while (mark == 0) {
				int res = (int) (Math.random() * 4);
				if(aa.moveBot() == true && res == 0){
					Matrix bot = new Matrix();
					bot.isParentMoveBot(aa);
					if(checkclosed(bot.getClosedMatrix()) == true){
						insertMatrix(bot);
						closematrixs.add(bot.getClosedMatrix());
						mark = 1;
						//System.out.println(bot.getClosedMatrix());
					}
				}
				if(aa.moveTop() == true && res == 1){
					Matrix top = new Matrix();
					top.isParentMoveTop(aa);
					if(checkclosed(top.getClosedMatrix()) == true){
						insertMatrix(top);
						closematrixs.add(top.getClosedMatrix());
						mark = 1;
						//System.out.println(top.getClosedMatrix());
					}
				}
				if(aa.moveLeft() == true && res == 2){
					Matrix left = new Matrix();
					left.isParentMoveLeft(aa);
					if(checkclosed(left.getClosedMatrix()) == true){
						insertMatrix(left);
						closematrixs.add(left.getClosedMatrix());
						mark = 1;
						//System.out.println(left.getClosedMatrix());
					}
				}
				if(aa.moveRight() == true && res == 3){
					Matrix right = new Matrix();
					right.isParentMoveRight(aa);
					if(checkclosed(right.getClosedMatrix()) == true){
						insertMatrix(right);
						closematrixs.add(right.getClosedMatrix());
						mark = 1;
						//System.out.println(right.getClosedMatrix());
					}
				}
			}
        }
        Matrix ans = open.get(0);
        ans.display();
        return ans;
    }
    public void insertMatrix(Matrix b){
        open.add(0, b);
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

}
