package Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import Entities.Player3;

import game.gamestate.Level1;
import game.main.GamePanel;

public class Bullet {
	private static final long serialVersionUID = 1L;
	
	public double x;
	public double y;
	public static double width = 60;
	public static double height = 20;
	private Image image;
	
	public Bullet(double o, double p) {
		x = o;
		y = p;	
	}

	public void tick(){
		if(x < GamePanel.WIDTH && x > 0 && Level1.player.rightRangedAttack) x = x+15;
		else Level1.player.rightRangedAttack = false;
		if(x + width > 0 && x < GamePanel.WIDTH && Level1.player.leftRangedAttack) x = x-15;
		else Level1.player.leftRangedAttack = false;
		if((x <= Level1.player3.x + Level1.player3.width && x + width >= Level1.player3.x) && 
		(y <= Level1.player3.y + Level1.player3.height && y + height >= Level1.player3.y)) {
			x = 1700;
			Level1.player3.health -= 10;
		}
	}
	
	public void draw(Graphics g) {
		ImageIcon bullet = new ImageIcon("src/Resources/res/bullet.png");
		ImageIcon leftBullet = new ImageIcon("src/Resources/res/left_bullet.png");
		if(Level1.player.rightRangedAttack) image = bullet.getImage();
		else if(Level1.player.leftRangedAttack) image = leftBullet.getImage();
		g.drawImage(image, (int)x, (int)y, null);
	}

}
