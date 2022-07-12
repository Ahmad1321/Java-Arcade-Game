package model;

import java.awt.Graphics;

public abstract class Item {
	
	
	protected byte ID;
	
	public Item(byte ID) {
		this.ID = ID;
	}
	
	public abstract void Render(Graphics G);
	
	public abstract void tick();
	
}
