package com.neusoft.planwar.core;

import java.awt.Graphics;
/**
 * ���е���ĸ��
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
