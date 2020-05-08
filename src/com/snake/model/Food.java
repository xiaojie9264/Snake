package com.snake.model;

import java.util.Random;

public class Food {

	public static int x;
	public static int y;

	public static void create() {
		Random random = new Random();
		x = random.nextInt(35) * 25 + 10;
		y = random.nextInt(28) * 25 + 65;
		
		for (int i = 0; i < Snake.len; i++) {
			if (Snake.x[i] == x && Snake.y[i] == y) {
				create();
			}
		}
	}

}
