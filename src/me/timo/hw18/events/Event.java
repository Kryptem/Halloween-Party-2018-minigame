package me.timo.hw18.events;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import me.timo.hw18.Game;
import me.timo.hw18.user.User;

public abstract class Event extends MouseAdapter {

	protected Game game = Game.INSTANCE;
	protected Random r = new Random();
	protected User user;

	public Event() {

	}

	public abstract void render(Graphics g);

	public abstract void tick();

	public abstract void mousePressed(MouseEvent e);
	
	public void nextEvent() {
		game.setCurrentEvent(game.getEventManager().getNextEvent());
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

}
