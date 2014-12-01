package game.gamestate;

import game.main.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

import Entities.Player;
import Entities.Player2;
import Entities.Player3;
import Object.Block;
import Object.Bullet;
import Object.Bullet2;
import Object.PointBlock;
import Physics.Sounds;

public class Level1 extends GameState {
	
	public static Player player;
	private Player2 player2;
	public static Player3 player3;
	private PointBlock block;
	public static Block[] b;
	public static Bullet bullet;
	public static Bullet2 bullet2;
	private Image image;

	public Level1(GameStateManager gsm) {
		super(gsm);
	}

	
	public void init() {
		player = new Player(60, 90);
		player2 = new Player2(30, 30);
		player3 = new Player3(60, 89);
		block = new PointBlock(30, 30);
		bullet = new Bullet(1700, 400);
		bullet2 = new Bullet2(1700, 400);
		
		b = new Block[60];
		for(int i = 0; i <32; i++){
			b[i] = new Block(i*50.0, 750.0);
		}
		for(int i = 0; i <4; i++){
			b[i+32] = new Block(i*50, 550);
		}
		for(int i = 0; i <4; i++){
			b[i+36] = new Block(i*50 + 1400, 550);
		}
		for(int i = 0; i <8; i++){
			b[i+40] = new Block(i*50 + 600, 450);
		}
		for(int i = 0; i<2; i++){
			b[i+48] = new Block(i*50 + 250, 350);
		}
		for(int i = 0; i<2; i++){
			b[i+50] = new Block(i*50 + 1250, 350);
		}
		for(int i = 0; i<2; i++){
			b[i+52] = new Block(i*50, 200);
		}
		for(int i = 0; i<2; i++){
			b[i+54] = new Block(i*50 + 1500, 200);
		}
		for(int i = 0; i<4; i++){
			b[i+56] = new Block(i*50 + 700, 150);
		}
		
		//Sounds.Sounds("src/Resources/res/bounce.wav");
	}

	
	public void tick() {
		
		if(!player.lose && !player3.lose){
			player.tick();
			player2.tick();
			player3.tick();
			block.tick();
			bullet.tick();
			bullet2.tick();
		}
	}

	
	public void draw(Graphics g) {
		ImageIcon background = new ImageIcon("src/Resources/res/space_background.jpg");
		image = background.getImage();
		g.drawImage(image, 0, 0, null);
		//g.setColor(new Color(200, 25, 25));
		//g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setFont(new Font("Arial", Font.BOLD, 156));
		//block.draw(g);
		//player2.draw(g);
		for(int i = 0; i < b.length; i++){
			b[i].draw(g);
		}
		player.draw(g);
		player3.draw(g);
		bullet.draw(g);
		bullet2.draw(g);
		
		if(player.lose){
			g.setColor(Color.PINK);
			g.drawString("Player 2 Wins!", GamePanel.WIDTH / 2 - 500, 150);
		}
		if(player3.lose){
			g.setColor(Color.PINK);
			g.drawString("Player 1 Wins!", GamePanel.WIDTH / 2 - 500, 150);
		}
	}

	
	public void keyPressed(int k) {
		player.keyPressed(k);
		player2.keyPressed(k);
		player3.keyPressed(k);
		if(k == KeyEvent.VK_ESCAPE) gsm.states.push(new MenuState(gsm));
	}

	
	public void keyReleased(int k) {
		player.keyReleased(k);
		player2.keyReleased(k);
		player3.keyReleased(k);
	}
}
