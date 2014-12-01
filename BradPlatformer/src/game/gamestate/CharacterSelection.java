package game.gamestate;

import game.main.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class CharacterSelection extends GameState {
	
	private String[] options = {"Banana Man", "Lobster Corgi", "Shrek"};
	private int currentSelection = 0;
	private Image image;
	private Image image2;
	private Image image3;
	
	private int characterSelect = 1;
	private int character1;

	public CharacterSelection(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {}

	public void tick(){}

	public void draw(Graphics g) {
		g.setColor(new Color(50, 150, 200));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		for(int i = 0; i < options.length; i++){
			if(i == currentSelection){
				g.setColor(Color.GREEN);
				g.setFont(new Font("Arial", Font.BOLD, 50));
			}
			else{
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", Font.PLAIN, 30));
			}
			g.drawString(options[i], 100 + (i*575), GamePanel.HEIGHT / 2 - 300);
		}
		ImageIcon banana = new ImageIcon("src/Resources/res/large_banana_man.jpg");
		image = banana.getImage();
		ImageIcon corgi = new ImageIcon("src/Resources/res/large_lobster_corgi.jpg");
		image2 = corgi.getImage();
		ImageIcon shrek = new ImageIcon("src/Resources/res/large_shrek.jpg");
		image3 = shrek.getImage();
		g.drawImage(image, 100, GamePanel.HEIGHT / 2 - 200, null);
		g.drawImage(image2, 600, GamePanel.HEIGHT / 2 - 200, null);
		g.drawImage(image3, 1100, GamePanel.HEIGHT / 2 - 200, null);
		
		if(characterSelect == 1){
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.PLAIN, 108));
			g.drawString("PLAYER 1: SELECT", 300, GamePanel.HEIGHT - 50);
		}
		else if(characterSelect == 2){
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.PLAIN, 108));
			g.drawString("PLAYER 2: SELECT", 300, GamePanel.HEIGHT - 50);
		}
		
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_RIGHT){
			currentSelection++;
			if(currentSelection >= options.length){
				currentSelection = 0;
			}
		}
		else if(k == KeyEvent.VK_LEFT){
			currentSelection--;
			if(currentSelection < 0){
				currentSelection = options.length - 1;
			}
		}
		
		if(k == KeyEvent.VK_ENTER){
			if(characterSelect == 1) {
				if(currentSelection == 0){
					characterSelect++;
					character1 = 0;
					currentSelection = 0;
				}
				else if(currentSelection == 1){
					characterSelect++;
					character1 = 1;
					currentSelection = 0;
				}
				else if(currentSelection == 2){
					characterSelect++;
					character1 = 2;
					currentSelection = 0;
				}
			}
			else if(characterSelect == 2) {
				if(currentSelection == 0){
					gsm.states.push(new Level1(gsm));
					Level1.player3.banana = true;
				}
				else if(currentSelection == 1){
					gsm.states.push(new Level1(gsm));
					Level1.player3.corgi = true;
				}
				else if(currentSelection == 2){
					gsm.states.push(new Level1(gsm));
					Level1.player3.shrek = true;
				}
				if(character1 == 0) Level1.player.banana = true;
				else if(character1 == 1) Level1.player.corgi = true;
				else if(character1 == 2) Level1.player.shrek = true;
			}
			
		}
		else if(k == KeyEvent.VK_ESCAPE) gsm.states.push(new MenuState(gsm));
	}

	public void keyReleased(int k) {}

}
