package me.timo.hw18.events;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.util.List;

import me.timo.hw18.Game;
import me.timo.hw18.Game.State;
import me.timo.hw18.utils.Utils;

public class TruthOrDareEvent extends Event {

	private List<String> truths;
	private List<Dare> dares;

	private String truth;
	private Dare dare;
	private String[] truthSplit, dareSplit;
	private Polygon topTriangle, bottomTriangle;

	private int factor;
	private int red;

	public TruthOrDareEvent() {
		truths = game.getOptions().getTruths();
		dares = game.getOptions().getDares();

		topTriangle = Utils.createTopTriangle();
		bottomTriangle = Utils.createBottomTriangle();

		factor = 1;
		red = 60;
	}

	public void getRandomTruthAndDare() {
		truth = truths.get(r.nextInt(truths.size()));
		dare = dares.get(r.nextInt(dares.size()));

		truthSplit = truth.split("-");
		dareSplit = dare.getDareText().split("-");

	}

	public void render(Graphics g) {

		g.setColor(new Color(100, 200, 66));
		g.fillPolygon(topTriangle);

		g.setColor(new Color(244, 55, 65));
		g.fillPolygon(bottomTriangle);

		g.setColor(new Color(10, red, 100));
		g.setFont(new Font("Arial", Font.PLAIN, 120));
		g.drawString("OR", 830, 550);

		g.setFont(new Font("Arial", Font.PLAIN, 200));
		g.drawString("Truth", 430, 300);
		g.drawString("Dare", 1060, 800);

		g.setFont(new Font("Arial", Font.PLAIN, 80));
		g.drawString("(3P)", 600, 370);

		g.setColor(Color.WHITE);
		g.fillRect(755, 0, 410, 150);
		g.setColor(new Color(100, 200, 66));
		g.fillRect(765, 0, 390, 140);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 70));
		g.drawString(user.getName(), 780, 60);
		g.drawString("(" + user.getPoints() + "P)", 885, 120);

		g.setFont(new Font("TimesRoman", Font.PLAIN, 110));
		if (game.getGameState() == State.TRUTH) {
			g.setColor(new Color(240, red, 66));
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

			g.setColor(Color.ORANGE);

			int y = 300;
			for (String s : truthSplit) {
				g.drawString(s, 600, y);
				y += 130;
			}

		}
		g.setFont(new Font("TimesRoman", Font.PLAIN, 110));
		if (game.getGameState() == State.DARE) {
			g.setColor(new Color(90, red, 66));
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

			g.setColor(Color.ORANGE);
			int y = 300;
			for (String s : dareSplit) {
				g.drawString(s, 440, y);
				y += 130;
			}

			g.setColor(Color.LIGHT_GRAY);
			g.drawString("(" + getRespectivePoints() + "P)", 810, 100);
		}

	}

	public void tick() {

		if (red < 60 || red > 120)
			factor *= -1;
		red += factor;

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (game.getCurrentEvent() instanceof TruthOrDareEvent) {

			// Truth or Dare displayed
			if (Utils.mouseOver(mx, my, 0, 0, Game.WIDTH, Game.HEIGHT)) {

				if (game.getGameState() == State.TRUTH) {
					user.setPoints(user.getPoints() + 3);
					game.setGameState(State.COUNTDOWN);
					nextEvent();
				}

				if (game.getGameState() == State.DARE) {
					user.setPoints(user.getPoints() + getRespectivePoints());
					game.setGameState(State.COUNTDOWN);
					nextEvent();
				}

			}
			// Top left for truth
			if (Utils.mouseOver(mx, my, 0, 0, Game.WIDTH / 2, Game.HEIGHT / 2)) {
				if (game.getGameState() == State.EVENT_IN_PROGRESS) {

					game.setGameState(State.TRUTH);

				}
			}
			// bottom right for dare
			if (Utils.mouseOver(mx, my, Game.WIDTH / 2 + 1, Game.HEIGHT / 2 + 1, Game.WIDTH / 2, Game.HEIGHT / 2)) {
				if (game.getGameState() == State.EVENT_IN_PROGRESS) {

					game.setGameState(State.DARE);

				}
			}
		}

	}
	
	private int getRespectivePoints() {
		int points = dare.getPoints();

		if (dare.isMwDare()) {
			if (user.isMan()) {
				points = dare.getmPoints();
			} else {
				points = dare.getfPoints();
			}
		}
		return points;
	}

	public List<Dare> getDares() {
		return dares;
	}

	public List<String> getTruths() {
		return truths;
	}

	public Polygon getTopTriangle() {
		return topTriangle;
	}

	public Polygon getBottomTriangle() {
		return bottomTriangle;
	}

}
