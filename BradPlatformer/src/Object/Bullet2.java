package Object;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import game.gamestate.Level1;
import game.main.GamePanel;

public class Bullet2 {
private static final long serialVersionUID = 1L;
	
	public double x;
	public double y;
	public static double width = 60;
	public static double height = 20;
	private Image image;
	
	public Bullet2(double o, double p) {
		x = o;
		y = p;	
	}

	public void tick(){
		if(x < GamePanel.WIDTH && x > 0 && Level1.player3.rightRangedAttack) x = x+15;
		else Level1.player3.rightRangedAttack = false;
		if(x + width > 0 && x < GamePanel.WIDTH && Level1.player3.leftRangedAttack) x = x-15;
		else Level1.player3.leftRangedAttack = false;
		if((x <= Level1.player.x + Level1.player.width && x + width >= Level1.player.x) && 
		(y <= Level1.player.y + Level1.player.height && y + height >= Level1.player.y)) {
			x = 1700;
			Level1.player.health -= 10;
		}
	}
	
	public void draw(Graphics g) {
		ImageIcon bullet = new ImageIcon("src/Resources/res/bullet.png");
		ImageIcon leftBullet = new ImageIcon("src/Resources/res/left_bullet.png");
		if(Level1.player3.rightRangedAttack) image = bullet.getImage();
		else if(Level1.player3.leftRangedAttack) image = leftBullet.getImage();
		g.drawImage(image, (int)x, (int)y, null);
	}

}
