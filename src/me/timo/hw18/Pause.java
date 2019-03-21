package me.timo.hw18;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import me.timo.hw18.Game.State;
import me.timo.hw18.user.User;
import me.timo.hw18.utils.Utils;

public class Pause implements KeyListener {

	private Game game;
	private State lastState;
	private BufferedImage frenzel2, frenzelKopf;
	
	public Pause(Game game) {
		this.game = game;
		frenzel2 = Utils.loadImage("frenzel2.jpg");
		frenzelKopf = Utils.loadImage("frenzel_kopf.png");
	}

	public void keyPressed(KeyEvent e) {
		if (game.getGameState() != State.MENU || game.getGameState() != State.PAUSE) {
			if (e.getKeyCode() == 27) {

				if (game.getGameState() != State.USER_LIST)
					lastState = game.getGameState();
				game.setGameState(State.PAUSE);
			}
		}

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void tick() {

	}

	public void render(Graphics g) {
		g.drawImage(frenzel2, 350, 100, null);
		Font font = new Font("Impact", Font.BOLD, 70);
		Font font1 = new Font("Impact", Font.PLAIN, 60);

		g.setColor(Color.ORANGE);
		g.setFont(font);
		g.drawString("Pause", 850, 190);

		g.setColor(Color.LIGHT_GRAY);
		g.setFont(font1);

		g.drawRect(850, 450, 200, 100);
		g.drawString("Weiter", 870, 520);

		g.drawRect(850, 590, 200, 100);
		g.drawString("Config", 870, 663);
		
		g.drawRect(850, 730, 200, 100);
		g.drawString("User", 890, 805);

		if (game.getGameState() == State.USER_LIST) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			g.setFont(font);
			g.setColor(Color.LIGHT_GRAY);
			g.drawString("User", 850, 190);

			g.setFont(font1);
			g.setColor(Color.ORANGE);
			int y = 320;
			for (User user : game.getUserManager().getUsers()) {
				g.drawImage(frenzelKopf, 640, y - 55, null);
				g.drawString(user.toString(), 710, y);
				y += 70;
			}
		}
	}

	public State getLastState() {
		return lastState;
	}

}
