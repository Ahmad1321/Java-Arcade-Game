package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.sound.sampled.Clip;

import javax.swing.JFrame;

import viewmodel.KeyHandler;
import viewmodel.LevelHandler;
import model.Player;
import viewmodel.Sound;

public class Window extends Canvas implements Runnable{
	
	//variable
	private static final long serialVersionUID = 1L;
	private Thread thread;
	public boolean running = false;
        private String name;
	
	private KeyHandler Klistener = new KeyHandler(this);
        Clip clip;
        public Sound bgm = new Sound();
	
	public LevelHandler level = new LevelHandler(this);
	
	public GameState gs = GameState.Game;
	
	
	
	//Run when a new window is created
	public Window(String Title) {
		JFrame frame = new JFrame(Title);
		
		//set size
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//set visible
		frame.setResizable(true);
		frame.setVisible(true);
		frame.add(this);
	}
	
	//Start the game logic
	public void start() {
		running = true;
		
		//Create the thread
		thread = new Thread(this);
		thread.start();
		
                //Putar Musik
                clip = bgm.playSound(this.clip, "menu.wav");
                
                //Panggil Player
		new Player(this,100,100, 64, 64);
	}
	
	public void stop() {
		running = false;
                
                //Tutup Musik
		bgm.stopSound(clip);
		//stop the thread
		try {
			thread.join();
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountsOfTicks = 60.0;
		double ns = 1000000000 / amountsOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			Render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		
		stop();
	}
	
	public void tick() {
		level.tick();
	}
        
        public void Setusername(String n){
            this.name = n;
        }
        public String Getusername(){
            return this.name;
        }
	
	public void Render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(2);
			bs = this.getBufferStrategy();
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(48, 25, 52));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		
		
		
		level.Render(g);
		
		bs.show();
		g.dispose();
	}
	
}
