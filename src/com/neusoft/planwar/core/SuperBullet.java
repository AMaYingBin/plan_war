package com.neusoft.planwar.core;

import java.awt.Graphics;
import java.util.List;

import com.neusoft.planwar.client.PlaneWarSystem;
import com.neusoft.planwar.constant.Constant;

public class SuperBullet extends Bullet {
	public SuperBullet(PlaneWarSystem pws) {
		this.pws = pws;
		this.x = 0;
		this.y = Constant.GAME_HEIGHT;
		this.img = Images.imgs.get("superbullet");

	}

	@Override
	public void move() {
		y -= speed;
	}

	@Override
	public void draw(Graphics g) {
		move();
		for (int i = 0; i < 10; i++) {
			g.drawImage(img, x + img.getWidth(null) * i, y, null);
		}
	}

	@Override
	public boolean hitPlane(List<EnemyPlane> enemies) {
		for (int i = 0; i < enemies.size(); i++) {
			EnemyPlane enemyPlane = enemies.get(i);

			enemyPlane.live = false;
			createExplode(enemyPlane);
		}
		// pws.enemies.clear();
		return true;
	}

	public boolean hitBullet(List<Bullet> bullets) {

		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			if (this.live && bullet.live && this.getRectangle().intersects(bullet.getRectangle()))

			{
				bullet.live = false;
			}
		}
		return true;
	}
}
