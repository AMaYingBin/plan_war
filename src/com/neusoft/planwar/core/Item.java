package com.neusoft.planwar.core;

import java.awt.Graphics;
/**
 * 所有道具類的父類
 * @author Administrator
 *
 */
public abstract class Item extends PlaneWarObject {
    public double speed;
    public double degree;
    public boolean live=true;
	@Override
	public abstract void move();

	@Override
	public abstract void draw(Graphics g) ;

}
