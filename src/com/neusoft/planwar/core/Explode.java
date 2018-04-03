package com.neusoft.planwar.core;

import java.awt.Graphics;
import java.awt.Image;

import com.neusoft.planwar.client.PlaneWarSystem;
import com.neusoft.planwar.constant.Constant;
import com.neusoft.planwar.util.GameUtil;

public class Explode extends PlaneWarObject {
	/**
	 * ���ڱ�ըͼƬ��һ��ͼƬ������һ������
	 */
	public static Image[] imgs = { GameUtil.getImage(Constant.IMGPATH_PRE + "explode/e1.png"),
			GameUtil.getImage(Constant.IMGPATH_PRE + "explode/e2.png"),
			GameUtil.getImage(Constant.IMGPATH_PRE + "explode/e3.png"),
			GameUtil.getImage(Constant.IMGPATH_PRE + "explode/e4.png") };

	public Explode() {
	}

	public Explode(PlaneWarSystem pws, int x, int y) {
		this.pws = pws;
		this.x = x;
		this.y = y;
		Music m=new Music("src/com/neusoft/planwar/music/bigexplosion.wav");
		
	}

	@Override
	public void move() {
	}

	/**
	 * ����ͼƬ���׻���һ�ż�����
	 */
	public boolean live = true;
	int count = 0;

	@Override
	public void draw(Graphics g) {
		if (!live) {
			pws.explodes.remove(this);
			return;
		}
		if (count > imgs.length - 1) {
			count = 0;
			live = false;
			return;
		}
		if (live) {
			g.drawImage(imgs[count], x + Images.imgs.get("enemyplane01").getWidth(null) / 2, y, null);
			count++;
		}

	}

}
