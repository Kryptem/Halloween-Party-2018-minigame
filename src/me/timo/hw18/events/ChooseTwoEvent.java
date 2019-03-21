package me.timo.hw18.events;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JCheckBox;

import me.timo.hw18.Game;
import me.timo.hw18.Game.State;
import me.timo.hw18.user.User;
import me.timo.hw18.utils.Utils;

public class ChooseTwoEvent extends Event {

	private List<JCheckBox> checkBoxes;

	private JCheckBox box1, box2;

	private int blue;
	private int blueFactor;

	public ChooseTwoEvent() {
		blue = 165;
		blueFactor = 1;
		checkBoxes = game.getOptions().getCheckBoxes();

	}

	public void getRandomBoxes() {
		box1 = checkBoxes.get(r.nextInt(checkBoxes.size()));
		box2 = checkBoxes.get(r.nextInt(checkBoxes.size()));

		while (box1 == box2)
			box1 = checkBoxes.get(r.nextInt(checkBoxes.size()));
	}

	public void render(Graphics g) {
		g.setColor(new Color(249, 87, 87));
		g.fillRect(0, 0, Game.WIDTH / 2, Game.HEIGHT);

		g.setColor(new Color(244, 128, 66));
		g.fillRect(Game.WIDTH / 2, 0, Game.WIDTH / 2, Game.HEIGHT);

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(755, 0, 410, 200);

		g.setFont(new Font("Arial", Font.PLAIN, 100));
		g.setColor(Color.WHITE);
		g.drawString(user.getName(), 780, 105);
		g.setFont(new Font("Arial", Font.PLAIN, 80));
		g.drawString("(" + user.getPoints() + "P)", 885, 180);

		g.setColor(new Color(blue, blue, 15));
		g.setFont(new Font("Arial", Font.BOLD, 100));

		g.drawString(box1.getText(), 40, 560);
		g.drawString(box2.getText(), 1050, 560);

	}

	public void tick() {

		if (blue < 165 || blue > 220)
			blueFactor *= -1;

		blue += blueFactor;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (game.getCurrentEvent() instanceof ChooseTwoEvent) { 
			// left
			if (Utils.mouseOver(mx, my, 0, 0, Game.WIDTH / 2, Game.HEIGHT)) {
				if (game.getGameState() == State.EVENT_IN_PROGRESS) {
					user.setPoints(user.getPoints() + Integer.parseInt(
							box1.getText().substring(box1.getText().length() - 3, box1.getText().length() - 2)));
					game.setGameState(State.COUNTDOWN);
					nextEvent();
				}
			}

			// right
			if (Utils.mouseOver(mx, my, Game.WIDTH / 2, 0, Game.WIDTH / 2, Game.HEIGHT)) {
				if (game.getGameState() == State.EVENT_IN_PROGRESS)
					user.setPoints(user.getPoints() + Integer.parseInt(
							box2.getText().substring(box2.getText().length() - 3, box2.getText().length() - 2)));
				game.setGameState(State.COUNTDOWN);
				nextEvent();
			}
		}
	}

	public List<JCheckBox> getCheckBoxes() {
		return checkBoxes;
	}

	public User getUser() {
		return user;
	}

}
