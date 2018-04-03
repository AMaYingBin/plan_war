package com.neusoft.planwar.core;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.Random;

import com.neusoft.planwar.client.PlaneWarSystem;
import com.neusoft.planwar.constant.Constant;
import com.neusoft.planwar.util.GameUtil;

public class Bullet extends PlaneWarObject {

	Direction dir;
	public int speed = Constant.MYPLANE01_BULLET_SPEED;
	// 判断是不是自己发出来的子弹
	public boolean good;
	public boolean live;
	public static int diecount = 0;
	public static int onesnum = 0;
	public static int tensnum = 0;
	public static int hunsnum = 0;
    

	public Bullet() {

	}

	public Bullet(int x, int y, String imagePath) {
		this.x = x;
		this.y = y;
		this.img = GameUtil.getImage(imagePath);
		this.dir = Direction.UP;
	}

	public Bullet(PlaneWarSystem pws, int x, int y, Image img, Direction dir, int speed, boolean good) {
		this.pws = pws;
		this.x = x;
		this.y = y;
		this.img = img;
		this.dir = dir;
		this.speed = Constant.MYPLANE01_BULLET_SPEED;
		this.good = good;
		this.live = true;
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
		// 子弹出边界销毁问题
		if (this.x <= 0 || this.x >= Constant.GAME_WIDTH - this.img.getWidth(null) || this.y <= 24
				|| this.y >= Constant.GAME_WIDTH) {
			pws.bullets.remove(this);
		}
	}
	// 个位计算
		public int OnesReckon(int diecount) {
			onesnum = diecount % 10;
			return onesnum;
		}

		// 十位计算
		public int TensReckon(int diecount) {
			tensnum = diecount / 10%10;
			return tensnum;
		}

		// 百位计算
		public int HunsReckon(int diecount) {
			hunsnum = diecount / 100%10;
			return hunsnum;
		}
	@Override
	public void draw(Graphics g) {
		move();
		if (live) {
			g.drawImage(img, x, y, null);
		}
		if(hitPlane(pws.enemies)) {
			diecount += 1;
			OnesReckon(diecount);
			TensReckon(diecount);
			HunsReckon(diecount);
		}
		if(hitBoss(pws.bosss)) {
			diecount += 20;
			OnesReckon(diecount);
			TensReckon(diecount);
			HunsReckon(diecount);
		}
		g.drawImage(Images.imgs.get("num" + onesnum),
				Constant.GAME_WIDTH - Images.imgs.get("num" + onesnum).getWidth(null), 32, null);
		g.drawImage(Images.imgs.get("num" + tensnum),
				Constant.GAME_WIDTH - Images.imgs.get("num" + tensnum).getWidth(null) * 2, 32, null);
		g.drawImage(Images.imgs.get("num" + hunsnum),
				Constant.GAME_WIDTH - Images.imgs.get("num" + hunsnum).getWidth(null) * 3, 32, null);
        
	}

	/**
	 * 隨機生成道具
	 */
	public static Random r = new Random();

	/**
	 * 子弹打一架飞机
	 * 
	 * @param p
	 * @return
	 */
	public boolean hitPlane(Plane p) {
		if (this.live && p.live && this.getRectangle().intersects(p.getRectangle()) && p.good != this.good) {
			pws.bullets.remove(this);
			this.live = false;
			if (p.good) {
				p.boold -= 10;
				if (p.boold <= 0) {
					p.live = false;
					createExplode(p);
				}
			} else {
				createExplode(p);
				if (r.nextInt(1000) > 950) {
					Blood blood = new Blood(pws, p.x, p.y);
					pws.bloods.add(blood);
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 爆炸方法
	 */
	public void createExplode(Plane p) {
		int x = p.x + img.getWidth(pws) / 2 - Images.imgs.get("e1").getWidth(null) / 2;
		int y = p.y + p.img.getWidth(null) / 2 - Images.imgs.get("e1").getHeight(null) / 2;
		Explode e = new Explode(pws, x, y);
		pws.explodes.add(e);

	}

	/**
	 * 子弹打一群飞机
	 * 
	 * @param enemies
	 * @return
	 */
	public boolean hitPlane(List<EnemyPlane> enemies) {

		for (int i = 0; i < enemies.size(); i++) {
			EnemyPlane enemyplane = enemies.get(i);
			if (this.hitPlane(enemyplane)) {
				// 打到就死
				enemyplane.live = false;
				return true;
			}
		}
		return false;
	}
	/**
	 * 打Boss
	 */
	public boolean hitBoss(Boss b) {
		if (this.live && b.live && this.getRectangle().intersects(b.getRectangle()) && b.good != this.good) {
			this.live = false;
			if (!b.good) {
				b.boold -= 5;
				if (b.boold <= 0) {
					b.live = false;
					pws.bosss.remove(this);
					createExplode(b);
					return true;
				}
			} else {
				createExplode(b);
				if (r.nextInt(1000) > 950) {
					Blood blood = new Blood(pws, b.x, b.y);
					pws.bloods.add(blood);
				}
			}
		}
		return false;
	}
	/**
	 * 打一群Boss
	 */
	public boolean hitBoss(List<Boss> bosss) {
    
		for (int i = 0; i < bosss.size(); i++) {
			Boss boss = bosss.get(i);
			
			if (this.hitBoss(boss)) {
				return true;
			}
		}
		return false;
	}
}
