package com.snake.frame;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer width = 900;//窗口宽度
	private Integer height = 800;//窗口高度
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private int x = (int) ((screen.getWidth() - width) / 2);//窗口x坐标
	private int y = (int) ((screen.getHeight() - height) / 2);//窗口y坐标
	
	
	Image icon = new ImageIcon("ico.png").getImage();
	

	public GameFrame() throws HeadlessException {
		this.setIconImage(icon);
		this.setTitle("Snake");
	}

	public void init() {
		this.setBounds(x, y, width, height);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		Container contentPane = this.getContentPane();
		GamePanel panel = new GamePanel(contentPane.getWidth(), contentPane.getHeight());
		this.add(panel);
		
		
	}
	
	
}
