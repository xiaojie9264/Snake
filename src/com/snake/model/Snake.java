package com.snake.model;

import javax.swing.ImageIcon;

import com.snake.frame.Data;

public class Snake {
	// 定义蛇的最大长度
	public static int[] x = new int[980];
	public static int[] y = new int[980];

	public static int len = 3;

	public static String direction = "R";

	// 初始化蛇
	public static void init() {
		x[0] = 60;
		x[1] = 35;
		x[2] = 10;
		y[0] = 65;
		y[1] = 65;
		y[2] = 65;
		len = 3;
		direction = "R";
	}

	public static ImageIcon getHeadIcon() {
		ImageIcon img = null;
		switch (direction) {
		case "R":
			img = Data.right;
			break;
		case "D":
			img = Data.down;
			break;
		case "L":
			img = Data.left;
			break;
		case "U":
			img = Data.up;
			break;
		}
		return img;
	}

	public static boolean move() {
		for (int i = len - 1; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}
		switch (direction) {
		case "L":
			x[0] = x[0] <= 10 ? 860 : (x[0] -= 25);
			break;
		case "R":
			x[0] = x[0] >= 860 ? 10 : (x[0] += 25);
			break;
		case "U":
			y[0] = y[0] <= 65 ? 740 : (y[0] -= 25);
			break;
		case "D":
			y[0] = y[0] >= 740 ? 65 : (y[0] += 25);
			break;
		}
		
		for (int i = 1; i < len; i++) {
			if (x[0] == x[i] && y[0] == y[i]) {
				return true;
			}
		}
		return false;
		
	}
	
	

}
