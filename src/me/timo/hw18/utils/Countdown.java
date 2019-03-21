package me.timo.hw18.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import me.timo.hw18.Game;
import me.timo.hw18.Game.State;

public class Countdown {

	private Game game;

	private int green;
	private int blue;
	private int factorB;
	private int factorG;

	// Time
	private double countdownTime = 360;

	public Countdown(Game game) {
		this.game = game;
		green = 254;
		blue = 200;

		factorB = -1;
		factorG = -1;
	}

	public void tick() {

		if (countdownTime <= 0) {
			game.setGameState(State.EVENT_IN_PROGRESS);
			setCountdownTime(game.getOptions().getCountdownTime());

		}

		if (blue <= 20 || blue > 200)
			factorB *= -1;

		if (green <= 120 || green > 254)
			factorG *= -1;

		blue += factorB;
		green += factorG;
		countdownTime--;

	}

	public void render(Graphics g) {
		// Background
		g.setColor(new Color(255, green, blue));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		// Time
		g.setColor(Color.BLACK);
		g.setFont(new Font("Impact", Font.BOLD, 100));
		g.drawString(calculateTime((int) countdownTime), 800, 550);
	}

	private String calculateTime(int ticksLeft) {
		int m = ticksLeft / 3600;
		int s = (ticksLeft % 3600) / 60;
		int ms = (ticksLeft % 3600) % 60;

		return m + ":" + s + ":" + ms;
	}
	
	public void setCountdownTime(double countdownTime) {
		this.countdownTime = countdownTime * 3600;
	}

	public double getCountdownTime() {
		return countdownTime;
	}

}
