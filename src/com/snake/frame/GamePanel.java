package com.snake.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.snake.model.Food;
import com.snake.model.Snake;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	894
//	771

	int frameWidth;
	int frameHeight;
	int gameWidth = 875;
	int gameHeight = 700;

	private boolean isStarted = false;
	private boolean isBoolin = true;
	private boolean isGameOver = false;

	private int score = 0;

	Timer timer = new Timer(100, this);

	public GamePanel(int frameWidth, int frameHeight) {
		this.setFocusable(true);
		this.frameHeight = frameHeight;
		this.frameWidth = frameWidth;
		this.setVisible(true);
		Snake.init();
		Food.create();
		this.addKeyListener(this);
		timer.start();
	}

	@Override
	public void paint(Graphics g) {
		// 画出标题部分
		g.setColor(Color.ORANGE);
		g.fillRect(10, 10, 875, 50);

		// 标题文字
		g.setColor(Color.DARK_GRAY);
		String title = "Snake";
		Font font = new Font("STXingkai", Font.BOLD, 30);
		g.setFont(font);
		int[] drawStr = drawStr(g.getFontMetrics(font), title);
		g.drawString(title, frameWidth / 2 - drawStr[0], 35 + drawStr[1]);

		// 分数
		g.setColor(Color.RED);
		String lentie = String.format("长度:%d", Snake.len);
		Font font1 = new Font("STXinwei", Font.PLAIN, 20);
		g.setFont(font1);
		int[] lentieindex = drawStr(g.getFontMetrics(font1), lentie);
		g.drawString(lentie, 840 - lentieindex[0], 20 + lentieindex[1]);
		// 长度
		g.setColor(Color.RED);
		String scoretie = String.format("分数:%d", score);
		g.setFont(font1);
		int[] scoretieindex = drawStr(g.getFontMetrics(font1), scoretie);
		g.drawString(scoretie, 840 - scoretieindex[0], 45 + scoretieindex[1]);

		// 活动区域
		g.setColor(Color.BLACK);
		g.fillRect(10, 65, gameWidth, gameHeight);

		// 画出开始图示语
		if (isBoolin) {
			String tips = "请敲击空格键开始游戏";
			g.setColor(Color.GREEN);
			g.setFont(font);
			int[] tipX = drawStr(g.getFontMetrics(font), tips);
			g.drawString(tips, frameWidth / 2 - tipX[0], frameHeight / 2 + tipX[1]);
		}

		// 画出蛇头
		Snake.getHeadIcon().paintIcon(this, g, Snake.x[0], Snake.y[0]);
		// 画出蛇身
		for (int i = 1; i < Snake.len; i++) {
			Data.body.paintIcon(this, g, Snake.x[i], Snake.y[i]);
		}

		// 画出食物
		Data.food.paintIcon(this, g, Food.x, Food.y);

		// 游戏结束
		if (isGameOver) {
			String tips = "游戏结束";
			g.setColor(Color.GREEN);
			g.setFont(font);
			int[] tipX = drawStr(g.getFontMetrics(font), tips);
			g.drawString(tips, frameWidth / 2 - tipX[0], frameHeight / 2 + tipX[1]);
		}

	}

	/**
	 * 计算文字出现的位置 设置为居中
	 * 
	 * @param fm  字体测量
	 * @param str 测量文字
	 * @return
	 */
	private int[] drawStr(FontMetrics fm, String str) {
		int sHeight = fm.getHeight();
		int sWidth = fm.stringWidth(str);
		int ascent = fm.getAscent();
		int x[] = new int[2];
		x[0] = sWidth / 2;
		x[1] = ascent - sHeight / 2;
		return x;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if (!isStarted && !isGameOver) {
			isBoolin = !isBoolin;
		}
		if (isStarted && !isGameOver) {
			isGameOver = Snake.move();
			if (Snake.x[0] == Food.x && Snake.y[0] == Food.y) {
				Snake.len++;
				isGameOver = Snake.move();// 吃到十五立即刷新长度
				score += (Snake.len / 10) + 1;
				Food.create();
			}
			if (isGameOver) {
				timer.stop();
			}
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_SPACE:
			if (!isGameOver) {
				isStarted = !isStarted;
				if (isStarted) {
					isBoolin = false;
					timer.setDelay(200);
				} else {
					timer.setDelay(100);
				}
				repaint();
			} else {
				timer.setDelay(100);
				isBoolin = true;
				isStarted = false;
				isGameOver = false;
				Snake.init();
				score = 0;
				timer.restart();
			}
			break;
		case KeyEvent.VK_DOWN:
			if (!"U".equals(Snake.direction)) {
				Snake.direction = "D";
			}
			break;
		case KeyEvent.VK_UP:
			if (!"D".equals(Snake.direction)) {
				Snake.direction = "U";
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (!"L".equals(Snake.direction)) {
				Snake.direction = "R";
			}
			break;
		case KeyEvent.VK_LEFT:
			if (!"R".equals(Snake.direction)) {
				Snake.direction = "L";
			}
			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
