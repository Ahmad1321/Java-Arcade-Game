package viewmodel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JOptionPane;

import model.Item;
import model.ObjectIDS;
import model.Platform;
import model.Player;
import model.TabelScore;
import view.Window;
import view.Menu;

public class LevelHandler {
	
	public double Gravity = 4;
	public Window w;
        public Menu m = null;
        public Sound s;
	
	public LinkedList<Item> items = new LinkedList<Item>();
	
	public double CameraX, CameraY;
	public int StartingX=150, StartingY=300;
	
	//Game Object
	public Player player = null;
	
	public int getidx =48;
	
	//Runs when a new level is created
	public LevelHandler(Window w) {
		this.w = w;
		
		//Set Random
		Random r = new Random();
		
		
		player = new Player(w,StartingX,StartingY,32,32);
		for(int i=0; i<50; i++) {
		int lantai = 0;
		
		int []ran = {250,300,350,400,450,225,175,200,325,425};
		items.add(new Platform(ObjectIDS.Floor, (i*500)+lantai, 500, 500, 500 ,Color.black));
		
		//(Position x, Position y, lebar, panjangnya)
		int seed1 = r.nextInt(10);
		int seed2 = r.nextInt(10);
		int seed3 = r.nextInt(10);
		int seed4 = r.nextInt(10);
		int seed5 = r.nextInt(10);
		items.add(new Platform(ObjectIDS.Platform, (i*500)+700, ran[seed1], 40, 500-ran[seed1] ,Color.yellow));
		items.add(new Platform(ObjectIDS.Platform, (i*500)+300, ran[seed2], 50, 500-ran[seed2] ,Color.gray));
		items.add(new Platform(ObjectIDS.Platform, (i*500)+400, ran[seed3], 45, 500-ran[seed3] ,Color.orange));
		items.add(new Platform(ObjectIDS.Platform, (i*500)+500, ran[seed4], 60, 500-ran[seed4] ,Color.blue));
		items.add(new Platform(ObjectIDS.Platform, (i*500)+600, ran[seed5], 65, 500-ran[seed5] ,Color.pink));
			if(i==getidx) {
				items.add(new Platform(ObjectIDS.Platform, (i*500)+200, 250, 30, 200 ,Color.green));
			}
		lantai = lantai + 500;
		}
	}
	
	public void Render(Graphics g) {
		g.translate(-(int)CameraX, -(int)CameraY);
		for(Item i: items) {
			i.Render(g);
		}
		player.Render(g);
		g.drawString("Adapt: "+ (int)player.adapt , 5+(int)CameraX, 10+(int)CameraY);
		g.drawString("Touch Here!!" , (getidx*500+200), 230);
		g.drawString("To Finish" , (getidx*500+200), 240);
                g.drawString("Fall: "+ (int)player.fall , 5+(int)CameraX, 20+(int)CameraY);
		g.translate((int)CameraX, (int)CameraY);
	}
	
	public void tick() {
		CameraX+=1;
		CameraY = player.y-350;
		for(Item i:items) {
			i.tick();
		}
		player.tick();
		if(CameraX >= player.x) {
                    RestartLevel();
		}
	}
	
	public void RestartLevel() {
		player.x= StartingX;
		player.y= StartingY;
		player.velx = 0;
		CameraX = 0;
		CameraY = 0;
                uploadScore();
                new Menu().setVisible(true);
                // menutup frame game
                w.setVisible(false);
                w.stop();
                
                
                
	}
        
        public void uploadScore(){
            try {
                TabelScore tscore = new TabelScore();
                tscore.insertData(w.Getusername(), (int)player.adapt, (int)player.fall);
            } catch (Exception ex) {
                System.err.println(ex.toString());
            }
            JOptionPane.showMessageDialog(null, "Username : " + w.Getusername() + "\n" + "Adapt : " + (int)player.adapt + "\n" + "Fall : " + (int)player.fall);
        }
        
        
	
}
