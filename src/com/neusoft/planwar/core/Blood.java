package com.neusoft.planwar.core;

import java.awt.Graphics;

import com.neusoft.planwar.client.PlaneWarSystem;
import com.neusoft.planwar.constant.Constant;

public class Blood extends Item {
    
    double speed=40;
	
	double degree=Math.PI/4;
	
	 
	
	
	public Blood() {}
	public Blood(PlaneWarSystem pws,int x,int y) {
		this.pws=pws;
		this.x=x;
		this.y=y;
		this.img=Images.imgs.get("addblood");
	}
	@Override
	public void move() {
		x+=(int)(speed*Math.cos(degree));
		y+=(int)(speed*Math.sin(degree));
		if(x<=0||x>=Constant.GAME_WIDTH-img.getWidth(null)) {
			degree=Math.PI-degree;
		}
		if(y<=32||y>Constant.GAME_HEIGHT-img.getHeight(null)) {
			degree=-degree;
		}
	}

	@Override
	public void draw(Graphics g) {
		if(!live) {
			pws.bloods.remove(this);
			return;
		}
		move();
		g.drawImage(img, x, y, null);
	}

}
