package com.neusoft.planwar.core;

import java.awt.Graphics;
import java.awt.Image;

import com.neusoft.planwar.client.PlaneWarSystem;
import com.neusoft.planwar.constant.Constant;
import com.neusoft.planwar.core.Plane.BloodBar;
import com.neusoft.planwar.util.GameUtil;

public class Boss extends EnemyPlane {
	public int boold = 100;
	
	
	public Boss(PlaneWarSystem pws) {
		this.pws = pws;
		this.x = Constant.GAME_WIDTH / 2;
		this.y = 32;
		this.img = Images.imgs.get("boss");
	}
	

	@Override
	public void draw(Graphics g) {
		//new BossThread().start();
		move();
		if (!live) {
			pws.bosss.remove(this);
			return;
		}
		if (live) {
			
			g.drawImage(img, x, y, null);
			bloodBar.draw(g);
		}
		
	}
	public BloodBar bloodBar = new BloodBar();
	boolean right = true;

	@Override
	public void move() {

		//y += 1;
			if (right) {
				x += 5;

			} else {
				x -= 5;
			}
			if (x < 0) {
				right = true;
			}
			if (x > Constant.GAME_WIDTH - Images.imgs.get("boss").getWidth(null)) {
				right = false;
			}


	}
	
	class BossThread extends Thread {
		@Override
		public void run() {
				try {
						pws.bosss.add(new Boss(pws));
					Thread.sleep(100000);
				} catch (InterruptedException e) {
					e.printStackTrace();
			}
		}
	}
	
	//创建一个内部类
	class BloodBar {
		public Image booldBarImg;
		public Image bloodOXImg;

		public BloodBar() {
			this.booldBarImg = Images.imgs.get("blood_back");
			this.bloodOXImg = Images.imgs.get("blood_ox");
		}

		public void draw(Graphics g) {
			g.drawImage(booldBarImg, x+30, y +23, null);
			for (int i = 0; i < 10 * boold / 100; i++) {
				g.drawImage(bloodOXImg, x + bloodOXImg.getWidth(null) * i+30, y +23, null);
			}
		}
	}
}
