package viewmodel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.GameState;
import view.Window;

public class KeyHandler implements KeyListener {
	
	private Window w;

	public KeyHandler(Window w) {
		this.w = w;
		w.addKeyListener(this);
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	private boolean MovingLeft = false;
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(w.gs == GameState.Game) {
			if(key == KeyEvent.VK_D) {
				w.level.player.velx = w.level.player.speed;
				MovingLeft = false;
			}
			if(key == KeyEvent.VK_A) {
				w.level.player.velx = -w.level.player.speed;
				MovingLeft = true;
			}
			if(key == KeyEvent.VK_W) {
				//Kondisi untuk loncat hanya sekali
				if(w.level.player.vely == 0) {
					w.level.player.vely = -w.level.player.Jump;
				}
				
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(w.gs == GameState.Game) {
			if(key == KeyEvent.VK_D && !MovingLeft) {
				w.level.player.velx = 0;
			}
			if(key == KeyEvent.VK_A && MovingLeft) {
				w.level.player.velx = 0;
			}
		}
	}	
}
