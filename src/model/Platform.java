package model;

import java.awt.Color;
import java.awt.Graphics;

public class Platform extends Item{
	
	public int x,y,width,height;
	public Color c;
	
	
	public Platform(byte ID, int x, int y ,int width, int height, Color c) {
		
		super(ID);
		this.x = x;
		this.c = c;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void tick() {
		
	}
	
	public void Render(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, width, height);
	}
	
	
}
