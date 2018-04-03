package com.neusoft.planwar.core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.List;

import com.neusoft.planwar.client.PlaneWarSystem;
import com.neusoft.planwar.constant.Constant;
import com.neusoft.planwar.util.GameUtil;

public class Plane extends PlaneWarObject {
	public int speed = Constant.MYPLANE01_SPEED;
	public boolean left, up, right, down;
	public Direction dir;
	public boolean live = true;
	public boolean good;
	public int boold = 100;

	public Plane() {

	}

	public Plane(int x, int y, String imgPath) {
		this.x = x;
		this.y = y;
		this.img = GameUtil.getImage(imgPath);

	}

	public Plane(PlaneWarSystem pws, int x, int y, String imgPath) {
		this.pws = pws;
		this.x = x;
		this.y = y;
		this.img = GameUtil.getImage(imgPath);
		this.dir = Direction.STOP;
	}

	public Plane(PlaneWarSystem pws, int x, int y, Image img, boolean good) {
		this.pws = pws;
		this.x = x;
		this.y = y;
		this.img = img;
		this.dir = Direction.STOP;
		this.good = true;
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;

		case KeyEvent.VK_J:
			fire();
			Music m=new Music("src/com/neusoft/planwar/music/button.wav");
			break;
		case KeyEvent.VK_K:
			superFire();
			break;
		case KeyEvent.VK_L:
			SuperBullet s = new SuperBullet(pws);
			pws.superbullets.add(s);
			break;

		default:
			break;
		}
		confirmDirection();

	}

	private void fire() {
		int x = this.x + this.img.getWidth(null) / 2 - Images.imgs.get("myplane01_bullet").getWidth(null) / 2;
		int y = this.y;
		Bullet b = new Bullet(pws, x - 10, y, Images.imgs.get("myplane01_bullet"), Direction.UP,
				Constant.MYPLANE01_BULLET_SPEED, good);
		Bullet b2 = new Bullet(pws, x + 10, y, Images.imgs.get("myplane01_bullet"), Direction.UP,
				Constant.MYPLANE01_BULLET_SPEED, good);
		pws.bullets.add(b);
		pws.bullets.add(b2);
	}

	/**
	 * 超级子弹方法
	 */
	public void superFire() {
		// 拿到Direction枚举类型中的所有方向
		Direction[] dirs = Direction.values();
		for (int i = 0; i < dirs.length - 1; i++) {
			Bullet b = new Bullet(pws, x + Images.imgs.get("myplane01").getWidth(null) / 2, y,
					Images.imgs.get("myplane01_bullet"), dirs[i], Constant.MYPLANE01_BULLET_SPEED, good);
			pws.bullets.add(b);
		}
	}

	@Override
	public void move() {
		if (up)
			y -= speed;
		if (left)
			x -= speed;
		if (right)
			x += speed;
		if (down)
			y += speed;
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

		// 判断我放飞机出界问题
		if (this.x <= 0)
			this.x = 0;
		if (this.x >= Constant.GAME_WIDTH - this.img.getWidth(null))
			this.x = Constant.GAME_WIDTH - this.img.getWidth(null);
		if (this.y <= 23)
			this.y = 23;
		if (this.y >= Constant.GAME_HEIGHT - this.img.getHeight(null))
			this.y = Constant.GAME_HEIGHT - this.img.getHeight(null);
	}

	@Override
	public void draw(Graphics g) {
		move();
		g.drawImage(img, x, y, null);
		bloodBar.draw(g);

	}

	public BloodBar bloodBar = new BloodBar();

	/**
	 * 创建一个内部类BloodBar
	 * 
	 * @param e
	 */
	class BloodBar {
		public Image booldBarImg;
		public Image bloodOXImg;

		public BloodBar() {
			this.booldBarImg = Images.imgs.get("blood_back");
			this.bloodOXImg = Images.imgs.get("blood_ox");
		}

		public void draw(Graphics g) {
			g.drawImage(booldBarImg, x, y - 10, null);
			for (int i = 0; i < 10 * boold / 100; i++) {
				g.drawImage(bloodOXImg, x + bloodOXImg.getWidth(null) * i, y - 10, null);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;

		default:
			break;
		}
		// 每次按鍵后調用確定方向的方法
		confirmDirection();

	}

	/**
	 * 确定飞机方向的值
	 * 
	 */
	private void confirmDirection() {
		// 向左
		if (left && !up && !right && !down) {
			this.dir = Direction.LEFT;
		} else if (left && up && !right && !down) {
			this.dir = Direction.LEFT_UP;
		} else if (!left && up && !right && !down) {
			this.dir = Direction.UP;
		} else if (!left && !up && right && !down) {
			this.dir = Direction.RIGHT;
		} else if (!left && !up && !right && down) {
			this.dir = Direction.DOWN;
		} else if (!left && up && right && !down) {
			this.dir = Direction.RIGHT_UP;
		} else if (left && !up && !right && down) {
			this.dir = Direction.LEFT_DOWN;
		} else if (!left && !up && right && down) {
			this.dir = Direction.RIGHT_DOWN;
		} else {
			this.dir = Direction.STOP;
		}

	}

	/**
	 * 吃道具方法
	 */

	public boolean eatItem(Item item) {
		if (this.live && item.live && this.getRectangle().intersects(item.getRectangle())) {
			this.boold = 100;
			item.live = false;
			return true;
		}
		return false;
	}

	public boolean eatItem(List<Blood> bloods) {
		for (int i = 0; i < bloods.size(); i++) {
			Blood blood = bloods.get(i);
			if (this.eatItem(blood))
				return true;
		}
		return false;
	}
}
