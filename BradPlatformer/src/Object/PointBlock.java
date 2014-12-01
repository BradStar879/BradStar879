package Object;

import game.main.GamePanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

public class PointBlock extends Rectangle{
	private static final long serialVersionUID = 1L;
	
	Random place = new Random();
	
	private int direction = place.nextInt(3)+1;
	private long startTime = System.nanoTime() / 1000000000;
	private long time = 3;
	
	public PointBlock(int width, int height) {
		setBounds(place.nextInt(GamePanel.WIDTH-30), place.nextInt(GamePanel.HEIGHT-30), width, height);
	}
	public void tick(){
		if(true){
			//if(direction == 1 && x < GamePanel.WIDTH-30) x = x+2;
			//else if(direction == 2 && x > 0) x = x-2;
			//else if(direction == 3 && y < GamePanel.HEIGHT-30) y = y+2;
			//else if(direction == 4 && y > 0) y = y-2;
			if(direction == 1 && x < GamePanel.WIDTH-30 && y < GamePanel.HEIGHT-30){
				x = x+2;
				y = y+2;
			}
			else if(direction == 2 && x < GamePanel.WIDTH-30 && y > 0){
				x = x+2;
				y = y-2;
			}
			else if(direction == 3 && x > 0 && y < GamePanel.HEIGHT-30){
				x = x-2;
				y = y+2;
			}
			else if(direction == 4 && x > 0 && y > 0){
				x = x-2;
				y = y-2;
			}
			else direction = place.nextInt(3)+1;
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}
	
}
