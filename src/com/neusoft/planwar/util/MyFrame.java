package com.neusoft.planwar.util;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.neusoft.planwar.constant.Constant;

public class MyFrame extends Frame{
	@Override
	public void paint(Graphics g) {
		
	}
	
	public void launchFrame() {
		
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		
		this.setResizable(false);
		this.setVisible(true);
		
		this.setLocationRelativeTo(null);
		
		this.setBackground(Color.BLACK);
		
		this.setTitle("开舒克的飞机");
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		new MyThread().start();
		
	}
	
		Image backImg = null;
		@Override
		public void update(Graphics g) {
			if (backImg == null) {
				backImg = createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
			}
			Graphics backg = backImg.getGraphics();
			Color c = backg.getColor();
			backg.setColor(Color.white);
			backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
			backg.setColor(c);
			paint(backg);
			g.drawImage(backImg, 0, 0, null);
		}
		
		class MyThread extends Thread {
			@Override
			public void run() {
				while (true) {
					repaint();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	
}













