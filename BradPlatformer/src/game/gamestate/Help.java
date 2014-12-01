package game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.main.GamePanel;

public class Help extends GameState{
	
	private String[] helpText = {"Move Player 1 with 'a', 'd', and space bar.", "Move Player 2 with the arrow keys.", "Menu"};

	public Help(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {}
	
	public void tick() {}

	public void draw(Graphics g) {
		g.setColor(new Color(50, 150, 200));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		for(int i = 0; i < helpText.length; i++){
			if(i > 1){
				g.setColor(Color.GREEN);
				g.setFont(new Font("Arial", Font.BOLD, 72));
			}
			else{
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", Font.PLAIN, 24));
			}
			g.drawString(helpText[i], 100, 150 + i * 150);
		}
		
		
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) gsm.states.push(new MenuState(gsm));
		
	}

	public void keyReleased(int k) {}
	
}
