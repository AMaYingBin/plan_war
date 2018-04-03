package com.neusoft.planwar.core;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import com.neusoft.planwar.constant.Constant;
import com.neusoft.planwar.util.GameUtil;

public class Images {
	public static final Map<String, Image> imgs = new HashMap<>();
	static {
		imgs.put("myplane01", GameUtil.getImage(Constant.IMGPATH_PRE + "Plane.png"));
		imgs.put("myplane01_bullet", GameUtil.getImage(Constant.IMGPATH_PRE + "myplane01_bullet.png"));

		imgs.put("enemyplane01", GameUtil.getImage(Constant.IMGPATH_PRE + "enemyplane01.png"));
		// ±¬Õ¨Í¼×é
		for (int i = 1; i < 5; i++) {
			imgs.put("e" + i, GameUtil.getImage(Constant.IMGPATH_PRE + "explode/e" + i + ".png"));
		}
		// BossÍ¼Æ¬
		imgs.put("boss", GameUtil.getImage(Constant.IMGPATH_PRE + "boss/Boss.png"));
		// ÑªÌõÍ¼Æ¬
		imgs.put("blood_back", GameUtil.getImage(Constant.IMGPATH_PRE + "blood_back.png"));
		imgs.put("blood_div", GameUtil.getImage(Constant.IMGPATH_PRE + "blood_div.png"));
		imgs.put("blood_ox", GameUtil.getImage(Constant.IMGPATH_PRE + "blood_ox.png"));
		// ¼ÓÑªµÀ¾ß
		imgs.put("addblood", GameUtil.getImage(Constant.IMGPATH_PRE + "addblood.png"));
		// ³¬¼¶È«Õ¨
		imgs.put("superbullet", GameUtil.getImage(Constant.IMGPATH_PRE + "superbullet.png"));
		// ±³¾°Í¼Æ¬
		imgs.put("bg", GameUtil.getImage(Constant.IMGPATH_PRE + "bg.jpg"));
		// »ý·ÖÍ¼Æ¬×é
		imgs.put("num0", GameUtil.getImage(Constant.IMGPATH_PRE + "number/num0.png"));
		imgs.put("num1", GameUtil.getImage(Constant.IMGPATH_PRE + "number/num1.png"));
		imgs.put("num2", GameUtil.getImage(Constant.IMGPATH_PRE + "number/num2.png"));
		imgs.put("num3", GameUtil.getImage(Constant.IMGPATH_PRE + "number/num3.png"));
		imgs.put("num4", GameUtil.getImage(Constant.IMGPATH_PRE + "number/num4.png"));
		imgs.put("num5", GameUtil.getImage(Constant.IMGPATH_PRE + "number/num5.png"));
		imgs.put("num6", GameUtil.getImage(Constant.IMGPATH_PRE + "number/num6.png"));
		imgs.put("num7", GameUtil.getImage(Constant.IMGPATH_PRE + "number/num7.png"));
		imgs.put("num8", GameUtil.getImage(Constant.IMGPATH_PRE + "number/num8.png"));
		imgs.put("num9", GameUtil.getImage(Constant.IMGPATH_PRE + "number/num9.png"));
	}

}
