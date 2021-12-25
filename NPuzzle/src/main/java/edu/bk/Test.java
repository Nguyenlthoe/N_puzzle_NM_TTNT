package edu.bk;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int[][] aa = new int[6][6];
        for(int i = 0; i < 5; i++){
            for(int j = 1; j <= 5; j++){
                aa[i][j] = scan.nextInt();
            }
        }
        int size = 5;
        Matrix a = new Matrix(aa, size);
        a.display();
        Solution solution = new Solution();
        solution.Solve(a);
//        System.out.println(a.moveTop());
//        System.out.println(a.moveLeft());
//        System.out.println(a.moveRight());
//        System.out.println(a.moveBot());
//        Matrix b = new Matrix();
//        b.isParentMoveBot(a);
//        b.display();
//        System.out.println(b.getClosedMatrix());
    }
}
