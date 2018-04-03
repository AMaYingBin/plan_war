package com.neusoft.planwar.core;

import java.awt.Graphics;

import com.neusoft.planwar.constant.Constant;

public class BackGround extends PlaneWarObject {
	int y1;
	int y2;
	int speed = 1;

	public BackGround() {
		this.img = Images.imgs.get("bg");
		this.x = 0;
		this.y1 = 0;
		this.y2 = -Images.imgs.get("bg").getHeight(null);
	}

	@Override
	public void move() {
		y1 += speed;
		if (y1 > Constant.GAME_HEIGHT) {
			y1 = -Images.imgs.get("bg").getHeight(null);
		}
		if (y2 > Constant.GAME_HEIGHT) {
			y2 = -Images.imgs.get("bg").getHeight(null);
		}
		y2 += speed;
	}

	@Override
	public void draw(Graphics g) {
		move();
		g.drawImage(img, x, y1, null);
		g.drawImage(img, x, y2, null);
	}

}
