package com.neusoft.planwar.core;

import java.awt.Graphics;
import java.awt.Image;

import com.neusoft.planwar.client.PlaneWarSystem;
import com.neusoft.planwar.constant.Constant;
import com.neusoft.planwar.util.GameUtil;

public class EnemyPlane extends Plane {
	public int speed = Constant.ENEMYPLANE01_SPEED;
	public boolean left, up, right, down;
	public Direction dir;
	public PlaneWarSystem pws;
	public boolean live = true;
	
	public EnemyPlane() {

	}

	public EnemyPlane(int x, int y, String imgPath) {
		this.x = x;
		this.y = y;
		this.img = GameUtil.getImage(imgPath);

	}

	public EnemyPlane(PlaneWarSystem pws, int x, int y, String imgPath) {
		this.pws = pws;
		this.x = x;
		this.y = y;
		this.img = GameUtil.getImage(imgPath);
		this.dir = Direction.DOWN;
	}

	public EnemyPlane(PlaneWarSystem pws, int x, int y, Image img, Direction dir) {
		this.pws = pws;
		this.x = x;
		this.y = y;
		this.img = img;
		this.dir = dir;
		this.good = false;
	}

	/**
	 * 敌方飞机开火
	 */
	private void fire() {
		int x = this.x + this.img.getWidth(null) / 2 - Images.imgs.get("myplane01_bullet").getWidth(null) / 2;
		int y = this.y;
		Bullet b = new Bullet(pws, x, y, Images.imgs.get("myplane01_bullet"), Direction.DOWN,
				Constant.MYPLANE01_BULLET_SPEED, good);
		pws.bullets.add(b);
	}

	@Override
	public void move() {

		switch (dir) {
		case LEFT:
			x -= speed;
			break;
		case LEFT_UP:
			x -= speed;
			y -= speed;
			break;
		case UP:
			y -= speed;
			break;
		case RIGHT_UP:
			x += speed;
			y -= speed;
			break;
		case RIGHT:
			x += speed;
			break;
		case RIGHT_DOWN:
			x += speed;
			y += speed;
			break;
		case DOWN:

			y += speed;
			break;
		case LEFT_DOWN:
			x -= speed;
			y += speed;
			break;
		default:
			break;
		}
		if (Math.random() * 1000 > 950)
			fire();
	}


	@Override
	public void draw(Graphics g) {
		if (pws.myplane.live && !live || this.x > Constant.GAME_WIDTH || this.x < 0 || this.y > Constant.GAME_HEIGHT) {
			pws.enemies.remove(this);
			
		}
		
		// 如果飞机是活的就画
		if (live) {
			move();
			g.drawImage(img, x, y, null);
		}
		
	}

}
