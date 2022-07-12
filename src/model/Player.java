package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import view.Window;

public class Player {
	
	public Window w;
	
	public int width, height;
	public double x,y;
	public double velx,vely;
	
	public double speed = 2;
	public double Jump = 4;
	public boolean Falling = false;
	
	public double score = 0;
	public double fall = 0;
	public double adapt = 0;
	public double air =0;
	public double temp = 0;
	
	
	public Player (Window w, double x, double y, int width, int height) {
		this.w = w;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;	
	}
	
	public void tick() {
		x+=velx;
		y+=vely;
		
		if(vely < w.level.Gravity && Falling) {
			vely+=0.1;
		}
		else if(!Falling && vely > 0) {
			vely= 0;
		}
		
		Collisions();
		
		
		
		/*Jatuh di floor
		if(y+vely < 400) {
			
		}
		else {
			vely = 0;
		}*/
	}
	
	public void Collisions() {
		Falling = true;
		for(Item i: w.level.items) {
			if(i.ID == ObjectIDS.Platform) {
				Platform p = (Platform)i; 
 				if(new Rectangle((int)x,(int)y+(int)vely,width,height).intersects(p.x,p.y,p.width,p.height)) {
					Falling = true;
					
					if(p.c == Color.green) {
						
						w.level.RestartLevel();
					}
					else {
						if(y == 469 && p.y-height+1-(int)y < -50) {
							vely = 0;
							y = p.y-height+1;
						}
						if(y + height <= p.y+1) {
							Falling = false;
							if(vely >0) {
								vely = 0;
								y = p.y - height + 1;
							}
						}else if(y< p.y) {
							velx = 0;
							x = p.x-width -2;
						}
						if(y >= p.y-1) {
							x = p.x-width -2;
							velx = 0;
						}
					}
					
					//Score
					if(fall == 0 && score == 0) {
						temp = (int)y;
						score++;
					}
					if(temp != y && vely == 0) {
						if(y == 469) {
							fall++;
						}
						else if(y != 469) {
							adapt++;
						}
						temp = (int)y;
					}
					
					//Ketika berada di lantai maka langsung ke atas
					/*if(y > 450 && p.y-(int)y < -50) {
						vely = 0;
						y = p.y-height+1;
					}
					
					//Cannot pass the block
					if(y >= p.y-1) {
						x = p.x-width;
						velx = -0.1;
					}
					
					//When in jumping
					if(vely < 0) {
						Falling = true;
						vely = -vely;
						y-=(vely+1);
					}
					*/
					/*if(p.y != 500) {
						velx = 0;
					}*/
					
					
					
				}
				
			}
		}
	}
	
	public void Render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, width, height);
	}
	
}
