package edu.bk;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GenMatrix {
	private ArrayList<String> path = new ArrayList<String>();
	private Queue<String> queue = new LinkedList<String>(), close = new LinkedList<String>();
	private String des = "12345678 ";

	public GenMatrix() {
		super();
	}

	private String getState(int size) {
		String s = "";
		for(int i = 1; i <= size * size; i++) {
			s += "" + i;
		}
//		System.out.println(s);
		return s;
	}

	public Matrix Solve(int num, int size) {
		queue.clear();
		close.clear();
		queue.offer(getState(size));
		while (!queue.isEmpty() && num > 0) {
			String string = queue.poll();
			num--;
			int mark = 0;
			while (mark == 0) {
				int res = (int) (Math.random() * 4);
				String newPos = "";
				int pos = string.indexOf("9");
				if (pos / size > 0 && res == 0) { // Up
					newPos = string.substring(0, pos - size) + string.charAt(pos) + string.substring(pos - size + 1, pos)
							+ string.charAt(pos - size) + string.substring(pos + 1);
					if (!close.contains(newPos)) {
						queue.offer(newPos);
						mark = 1;
					}
				}
				if (pos / size < (size - 1) && res == 1) { // Down
					newPos = string.substring(0, pos) + string.charAt(pos + size) + string.substring(pos + 1, pos + size)
							+ string.charAt(pos) + string.substring(pos + size + 1);
					if (!close.contains(newPos)) {
						queue.offer(newPos);
						mark = 1;
					}
				}
				if (pos % size > 0 && res == 2) { // Left
					newPos = string.substring(0, pos - 1) + string.charAt(pos) + string.charAt(pos - 1)
							+ string.substring(pos + 1);
					if (!close.contains(newPos)) {
						queue.offer(newPos);
						mark = 1;
					}
				}
				if (pos % size < (size - 1) && res == 3) { // Right
					newPos = string.substring(0, pos) + string.charAt(pos + 1) + string.charAt(pos)
							+ string.substring(pos + 1 + 1);
					if (!close.contains(newPos)) {
						queue.offer(newPos);
						mark = 1;
					}
				}
				close.offer(string);
			}

		}
		String ans = queue.poll();
//		System.out.println(ans);
		int[][] aa = new int[size + 1][size + 1];
		for(int i = 0; i < size * size; i++) {
			aa[i / size][(i % size) + 1] = Integer.parseInt(ans.charAt(i) + "");
		}
		Matrix a = new Matrix(aa, size);
		return a;
	}

	public ArrayList<String> getPath() {
		ArrayList<String> pathRev = new ArrayList<String>();
		for (int i = path.size() - 1; i >= 0; i--) {
			pathRev.add(path.get(i));
		}
		return pathRev;
	}

	public Queue<String> getClose() {
		return close;
	}

}
