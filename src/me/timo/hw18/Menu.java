package me.timo.hw18;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import me.timo.hw18.Game.State;
import me.timo.hw18.utils.Utils;

public class Menu extends MouseAdapter {

	private Game game;
	private BufferedImage frenzel;

	public Menu(Game game) {
		this.game = game;
		frenzel = Utils.loadImage("frenzel.jpg");
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (Utils.mouseOver(mx, my, 850, 730, 200, 100)) {
			if (game.getGameState() == State.MENU)
				System.exit(0);
		}

		if (Utils.mouseOver(mx, my, 850, 450, 200, 100)) {
			if (game.getGameState() == State.MENU) {
				game.setGameState(State.COUNTDOWN);
			}

		}

		if (Utils.mouseOver(mx, my, 850, 590, 200, 100)) {
			if (game.getGameState() == State.MENU || game.getGameState() == State.PAUSE)
				game.getRegisterForm().openForm();
		}
		
		if (Utils.mouseOver(mx, my, 850, 450, 200, 100)) {
			if (game.getGameState() == State.PAUSE) {
				game.setGameState(game.getPause().getLastState());
			}
		}
		
		if (Utils.mouseOver(mx, my, 850, 730, 200, 100)) {
			if (game.getGameState() == State.PAUSE) {
				game.setGameState(State.USER_LIST);
			}
		}
		
		
	}

	public void mouseReleased(MouseEvent e) {

	}

	public void render(Graphics g) {
		g.drawImage(frenzel, 300, 0, null);
		Font font = new Font("Impact", Font.BOLD, 70);
		Font font1 = new Font("Impact", Font.PLAIN, 60);
		g.setColor(Color.ORANGE);
		g.setFont(font);
		g.drawString("Welcome", 810, 120);
		g.drawString("to", 930, 190);
		g.drawString("Halloween", 930, 260);
		g.drawString("2018", 810, 330);

		g.setColor(Color.LIGHT_GRAY);
		g.setFont(font1);
		g.drawRect(850, 450, 200, 100);
		g.drawString("Start", 890, 520);

		g.drawRect(850, 590, 200, 100);
		g.drawString("Config", 870, 663);

		g.drawRect(850, 730, 200, 100);
		g.drawString("Exit", 910, 805);
	}

	public void tick() {

	}

}
