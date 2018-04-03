package com.neusoft.planwar.client;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.neusoft.planwar.constant.Constant;
import com.neusoft.planwar.core.BackGround;
import com.neusoft.planwar.core.Blood;
import com.neusoft.planwar.core.Boss;
import com.neusoft.planwar.core.Bullet;
import com.neusoft.planwar.core.Direction;
import com.neusoft.planwar.core.EnemyPlane;
import com.neusoft.planwar.core.Explode;
import com.neusoft.planwar.core.Images;
import com.neusoft.planwar.core.Music;
import com.neusoft.planwar.core.Plane;
import com.neusoft.planwar.core.SuperBullet;
import com.neusoft.planwar.util.MyFrame;

public class PlaneWarSystem extends MyFrame {
	public Plane myplane = new Plane(this, 400, 700, Images.imgs.get("myplane01"), true);
	// SuperBullet s=new SuperBullet();
	
	public List<Bullet> bullets = new ArrayList<>();
	public List<EnemyPlane> enemies = new ArrayList<>();
	public List<Explode> explodes = new ArrayList<>();
	public List<SuperBullet> superbullets = new ArrayList<>();
	public List<Boss> bosss = new ArrayList<>();

	// public Blood blood=new Blood(this, 600, 300);
	public List<Blood> bloods = new ArrayList<>();
	
	
	@Override
	public void launchFrame() {
		super.launchFrame();
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				myplane.keyPressed(e);

			}

			@Override
			public void keyReleased(KeyEvent e) {
				myplane.keyReleased(e);
			}
		});
		// 出场初始化敌机
		for (int i = 0; i < 8; i++) {
			EnemyPlane enemyPlane = new EnemyPlane(this, 100 + i * 130, 100, Images.imgs.get("enemyplane01"),
					Direction.DOWN);
			enemies.add(enemyPlane);
		}
		
      
  	 Music m=new Music();
  	 m.playBackground();
    	bosss.add(b);
	}
	
	 BackGround bg = new BackGround();
	 Boss b=new Boss(this);
     
	
	@Override
	public void paint(Graphics g) {
		bg.draw(g);
		
		for (int i = 0; i < bosss.size(); i++) {
			Boss boss = bosss.get(i);
			boss.draw(g);
		}
		for (int i = 0; i < bloods.size(); i++) {
			Blood blood = bloods.get(i);
			blood.draw(g);
		}

		myplane.draw(g);
		myplane.eatItem(bloods);
		if (enemies.size() == 0) {
			Random r = new Random();
			switch (r.nextInt(3)) {
			case 0:
				for (int i = 0; i < 3; i++) {
					EnemyPlane enemyPlane = new EnemyPlane(this, i * Images.imgs.get("enemyplane01").getWidth(null),
							i * Images.imgs.get("enemyplane01").getHeight(null), Images.imgs.get("enemyplane01"),
							Direction.RIGHT_DOWN);
					EnemyPlane enemyPlane2 = new EnemyPlane(this,
							Constant.GAME_WIDTH - i * Images.imgs.get("enemyplane01").getWidth(null) - 69,
							i * Images.imgs.get("enemyplane01").getHeight(null), Images.imgs.get("enemyplane01"),
							Direction.LEFT_DOWN);
					enemies.add(enemyPlane);
					enemies.add(enemyPlane2);
				}
				break;
			case 1:
				for (int i = 0; i < 8; i++) {
					EnemyPlane enemyPlane = new EnemyPlane(this, 100 + i * 130, 100, Images.imgs.get("enemyplane01"),
							Direction.DOWN);
					enemies.add(enemyPlane);
				}
				break;
			case 2:
				for (int i = 0; i < 4; i++) {
					EnemyPlane enemyPlane = new EnemyPlane(this, i * 130, Constant.GAME_HEIGHT / 2,
							Images.imgs.get("enemyplane01"), Direction.RIGHT);
					EnemyPlane enemyPlane1 = new EnemyPlane(this, Constant.GAME_WIDTH - i * 130,
							Constant.GAME_HEIGHT / 2, Images.imgs.get("enemyplane01"), Direction.LEFT);
					enemies.add(enemyPlane);
					enemies.add(enemyPlane1);
				}
				break;
			default:
				break;
			}
		}

		for (int i = 0; i < enemies.size(); i++) {
			EnemyPlane enemyPlane = enemies.get(i);
			enemyPlane.draw(g);
		}

		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			bullet.draw(g);
			bullet.hitPlane(enemies);
			bullet.hitPlane(myplane);
			bullet.hitBoss(bosss);
		}
		for (int i = 0; i < superbullets.size(); i++) {
			SuperBullet superbullet = superbullets.get(i);
			superbullet.draw(g);
			superbullet.hitPlane(enemies);
			superbullet.hitBullet(bullets);
		}
		for (int i = 0; i < explodes.size(); i++) {
			Explode e = explodes.get(i);
			e.draw(g);
		}
		g.drawString("子弹个数：" + bullets.size(), 50, 50);
		g.drawString("敌方飞机：" + enemies.size(), 50, 70);
		g.drawString("爆炸数:" + explodes.size(), 50, 90);
		g.drawString("生命值:" + myplane.boold, 50, 110);
		g.drawString("得分:" + new Bullet() .diecount, 50, 130);
		// blood.draw(g);
		// s.draw(g);
	}

	public static void main(String[] args) {
		new PlaneWarSystem().launchFrame();
	}

}
