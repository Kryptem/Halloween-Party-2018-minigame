package me.timo.hw18.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.timo.hw18.Game;

public class EventManager {

	private Game game;
	private Random r;

	private List<Event> events;

	public EventManager(Game game) {
		this.game = game;
		r = new Random();
		events = new ArrayList<>();
		
	}

	public Event getNextEvent() {
		Event event = events.get(r.nextInt(events.size()));
		event.setUser(game.getUserManager().getNextUser());

		if (event instanceof ChooseTwoEvent) {
			((ChooseTwoEvent) event).getRandomBoxes();
			
		} else if (event instanceof TruthOrDareEvent) {
			((TruthOrDareEvent)event).getRandomTruthAndDare();
		}

		return event;
	}

	public List<Event> getEvents() {
		return events;
	}

}
