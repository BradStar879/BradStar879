package Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Block{
	private static final long serialVersionUID = 1L;
	
	public static final double blockSize = 50;
	
	public double x;
	public double y;
	public double width = blockSize;
	public double height = blockSize;
	private Image image;
	
	public Block(double o, double p) {
		x = o;
		y = p;	
	}

	public void draw(Graphics g) {
		ImageIcon brickBlock = new ImageIcon("src/Resources/res/brick_block.png");
		image = brickBlock.getImage();
		g.drawImage(image, (int)x, (int)y, null);
		//g.setColor(Color.BLACK);
		//g.fillRect((int)x, (int)y, (int)width, (int)height);
	}
	
	//public Rectangle getBounds() {
	//	return new Rectangle(x, y, width, height);
	//}
}
