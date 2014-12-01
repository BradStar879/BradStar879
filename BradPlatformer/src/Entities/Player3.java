package Entities;

import game.main.GamePanel;

import game.gamestate.Level1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

import Object.Block;
import Physics.Collision;
import Physics.Sounds;
import Resources.Images;

public class Player3{
	
	private boolean right = false, left = false, jumping = false, falling = true, leftStop = false, rightStop = false;
	public double x, y;
	public static double width;
	public static double height;
	public int blockTouch;
	
	public boolean banana = false;
	public boolean corgi = false;
	public boolean shrek = false;
	
	private int jumpCount = 0;
	private boolean colorChange = false;
	private boolean imageChange = false;
	public boolean facingRight = false;
	public boolean facingLeft = true;
	public boolean rightRangedAttack = false;
	public boolean leftRangedAttack = false;
	private boolean projectileExists = false;
	public boolean lose = false;
	private boolean healthSet = false;
	
	private double jumpSpeed = 10;
	private double currentJumpSpeed = jumpSpeed;
	public int health;
	
	private double maxFallSpeed =10;
	private double currentFallSpeed = .1;
	
	private Image image;
	private Image image2;
	private Image image3;
	
	public Player3(double width, double height) {
		this.width = width;
		this.height = height;
		x = GamePanel.WIDTH - 200 - this.width ;
		y = GamePanel.HEIGHT - 200;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}
	
	public void tick() {

		if(!healthSet){
			if(banana) {
				health = 100;
			}
			if(corgi) {
				health = 80;
				jumpSpeed = 12;
			}
			if(shrek) {
				health = 130;
				jumpSpeed = 9;
			}
			healthSet = true;
		}

		if(right && x < GamePanel.WIDTH - 30 && !rightStop){
			if(banana) x = x+5;
			if(corgi) x = x+8;
			if(shrek) x = x+3;
			facingLeft = false;
			facingRight = true;
		}
		
		if(left && x > 0 && !leftStop){
			if(banana) x = x-5;
			if(corgi) x = x-8;
			if(shrek) x = x-3;
			facingRight = false;
			facingLeft = true;
		}
		
		if(jumping){
			falling = false;
			y -= currentJumpSpeed;
			currentJumpSpeed -= .3;
			
			if(currentJumpSpeed <= 0){
				currentJumpSpeed = jumpSpeed;
				falling = true;
				jumping = false;
				jumpCount++;
			}
		}
		if(!jumping) currentJumpSpeed = jumpSpeed;
		
		if(falling){
			y += currentFallSpeed;
			if(currentFallSpeed < maxFallSpeed){
				currentFallSpeed += .3;
			}
		}
		if(!falling) {
			currentFallSpeed = .1;
		}
		
		if(!falling && !jumping){
			jumpCount = 0;
		}
		
		for(int i = 0; i < Level1.b.length; i++) {
			if((this.y + this.height >= Level1.b[i].y && this.y + this.height <= Level1.b[i].y + 10) && (this.x + this.width > Level1.b[i].x && this.x < Level1.b[i].x + Block.blockSize)){
				falling = false;
				blockTouch = i;
			}
		}
		
		if(!((this.y + this.height >= Level1.b[blockTouch].y && this.y + this.height <= Level1.b[blockTouch].y + 10) && (this.x + this.width > Level1.b[blockTouch].x && this.x < Level1.b[blockTouch].x + Block.blockSize)))
			falling = true;
		
		if((this.x == Level1.player.x + Player.width) && (this.y < Level1.player.y + Player.height && this.y + this.height > Level1.player.y))
			leftStop = true;
		else if(this.x > Level1.player.x) leftStop = false;
		
		if((this.x + this.width == Level1.player.x) && (this.y < Level1.player.y + Player.height && this.y + this.height > Level1.player.y))
			rightStop = true;
		else if(this.x < Level1.player.x + Player.width) rightStop = false;
		
		if(this.y + this.height >= GamePanel.HEIGHT) falling = false;
		if(this.y < 0) { 
			falling = true;
			jumping = false;
			jumpCount++;
		}
		if(this.x <=0) x = 0;
		if(this.x + this.width >= GamePanel.WIDTH) x = GamePanel.WIDTH - 60;
		
		if(rightRangedAttack && !projectileExists){
			projectileExists = true;
			Level1.bullet2.x = this.x + this.width;
			Level1.bullet2.y = this.y + 30;
			Sounds.Sounds("src/Resources/res/hit_marker.wav");
		}
		if(leftRangedAttack && facingLeft && !projectileExists){
			projectileExists = true;
			Level1.bullet2.x = this.x;
			Level1.bullet2.y = this.y + 30;
			Sounds.Sounds("src/Resources/res/hit_marker.wav");
		}
		if(Level1.bullet2.x + Level1.bullet2.width <= 0 || Level1.bullet2.x >= GamePanel.WIDTH) projectileExists = false;
		
		if(health <= 0) {
			health = 0;
			lose = true;
		}
	}
	
	public void draw(Graphics g) {
		//g.setColor(Color.WHITE);
		//g.fillRect((int)x, (int)y, width, height);
		ImageIcon rightBanana = new ImageIcon("src/Resources/res/banana_man.jpg");
		ImageIcon leftBanana = new ImageIcon("src/Resources/res/left_banana_man.jpg");
		ImageIcon rightCorgi = new ImageIcon("src/Resources/res/lobster_corgi.jpg");
		ImageIcon leftCorgi = new ImageIcon("src/Resources/res/left_lobster_corgi.jpg");
		ImageIcon rightShrek = new ImageIcon("src/Resources/res/shrek.jpg");
		ImageIcon leftShrek = new ImageIcon("src/Resources/res/left_shrek.jpg");
		ImageIcon q = new ImageIcon("src/Resources/res/luigi2.jpg");
		if(banana) g.fillRect((int)this.x, (int)this.y - 20, 6*(health/10), 10);
		if(corgi) g.fillRect((int)this.x + 14, (int)this.y - 20, 6*(health/10), 10);
		if(shrek) g.fillRect((int)this.x + 11, (int)this.y - 20, 6*(health/10), 10);
		if(!imageChange && facingRight && banana) {
			image = rightBanana.getImage();
			g.drawImage(image, (int)x, (int)y, null);
		}
		if(!imageChange && facingLeft && banana) {
			image = leftBanana.getImage();
			g.drawImage(image, (int)x, (int)y, null);
		}
		if(!imageChange && facingRight && corgi) {
			image = rightCorgi.getImage();
			g.drawImage(image, (int)x, (int)y, null);
		}
		if(!imageChange && facingLeft && corgi) {
			image = leftCorgi.getImage();
			g.drawImage(image, (int)x, (int)y, null);
		}
		if(!imageChange && facingRight && shrek) {
			image = rightShrek.getImage();
			g.drawImage(image, (int)x, (int)y, null);
		}
		if(!imageChange && facingLeft && shrek) {
			image = leftShrek.getImage();
			g.drawImage(image, (int)x, (int)y, null);
		}
		//if(colorChange) {
			//g.setColor(Color.CYAN);
			//g.fillRect((int)x, (int)y, width, height);
		//}
		if(imageChange) {
			image = q.getImage();
			g.drawImage(image, (int)x, (int)y, null);
		}
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_RIGHT) right = true;
		if(k == KeyEvent.VK_LEFT) left = true; 
		if(k == KeyEvent.VK_UP && jumpCount < 2) jumping = true;
		if(k == KeyEvent.VK_DOWN && facingRight && !leftRangedAttack) rightRangedAttack = true;
		if(k == KeyEvent.VK_DOWN && facingLeft && !rightRangedAttack) leftRangedAttack = true;
		//if(k == KeyEvent.VK_Q) colorChange = true;
		if(k == KeyEvent.VK_Q) imageChange = true; 
		if(k == KeyEvent.VK_SHIFT) {
			if(corgi){
				Sounds.Sounds("src/Resources/res/dog_bite.wav");
			}
			if(banana){
				Sounds.Sounds("src/Resources/res/banana_taunt.wav");
				}
			if(shrek){
				Sounds.Sounds("src/Resources/res/shrek_taunt.wav");
				}
		}
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_RIGHT) right = false;
		if(k == KeyEvent.VK_LEFT) left = false; 
		//if(k == KeyEvent.VK_Q) colorChange = false;
		if(k == KeyEvent.VK_Q) imageChange = false; 
	}
}
