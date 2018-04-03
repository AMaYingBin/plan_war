package com.neusoft.planwar.core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.neusoft.planwar.client.PlaneWarSystem;


public abstract class PlaneWarObject implements Drawable,Moveable{
	public int x;
	public int y;
	public Image img;
	public PlaneWarSystem pws;
    @Override
    public abstract void move();
    
    @Override
    public abstract void draw(Graphics g);
    
    public Rectangle getRectangle() {
    	return new Rectangle(x, y, img.getWidth(null),img.getHeight(null));
    }
}
