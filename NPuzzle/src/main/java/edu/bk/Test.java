package edu.bk;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int[][] aa = new int[4][4];
        for(int i = 0; i < 3; i++){
            for(int j = 1; j <= 3; j++){
                aa[i][j] = scan.nextInt();
            }
        }
        int size = 3;
        Matrix a = new Matrix(aa, size);
        a.display();
        Solution solution = new Solution();
        solution.Solve(a);
//        System.out.println(a.getMtdistance());
////        Matrix b = new Matrix();
////        b.isParentMoveTop(a);
////        b.display();
//        System.out.println(a.moveBot());
//        System.out.println(a.moveTop());
//        System.out.println(a.moveLeft());
//        System.out.println(a.moveRight());
//        ArrayList<String> e = new ArrayList<String>();
//        e.add("Dfsa");
//        e.add("dafssaf");
//        System.out.println(e.size());
//        e.remove(1);
//        System.out.println(e.size());
    }
}
