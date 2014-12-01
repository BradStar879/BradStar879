package Entities;

import game.main.GamePanel;
import game.gamestate.Level1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Player2 extends Rectangle{
	private static final long serialVersionUID = 1L;
	
	private boolean right = false, left = false, up = false, down = false;
	
	public Player2(int width, int height) {
		setBounds(GamePanel.WIDTH / 2 + 100, GamePanel.HEIGHT / 2, width, height);
	}
	
	public void tick() {
		if(right && x < GamePanel.WIDTH - 30){
			x = x+2;
		}
		
		if(left && x > 0){
			x = x-2;
		}
		
		if(up && y > 0){
			y = y-2;
		}
		
		if(down && y < GamePanel.HEIGHT - 30){
			y = y+2;
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_RIGHT) right = true;
		if(k == KeyEvent.VK_LEFT) left = true; 
		if(k == KeyEvent.VK_UP) up = true;
		if(k == KeyEvent.VK_DOWN) down = true; 
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_RIGHT) right = false;
		if(k == KeyEvent.VK_LEFT) left = false; 
		if(k == KeyEvent.VK_UP) up = false;
		if(k == KeyEvent.VK_DOWN) down = false; 
	}
	
	

}
