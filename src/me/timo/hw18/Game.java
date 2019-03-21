package me.timo.hw18;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import me.timo.hw18.events.ChooseTwoEvent;
import me.timo.hw18.events.Event;
import me.timo.hw18.events.EventManager;
import me.timo.hw18.events.TruthOrDareEvent;
import me.timo.hw18.user.UserManager;
import me.timo.hw18.utils.Countdown;
import me.timo.hw18.utils.MusicPlayer;
import me.timo.hw18.utils.Options;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 7511068067460889230L;
	public static final int WIDTH = 1920, HEIGHT = WIDTH / 12 * 9;
	public static Game INSTANCE;

	private final Window window;
	private Thread thread;
	private boolean running;

	private MusicPlayer musicPlayer;

	private Options options;
	private NewForm registerForm;
	private Menu menu;
	private Pause pause;
	private Countdown countdown;

	private UserManager userManager;
	private EventManager eventManager;
	private Event event;

	public State gameState = State.MENU;

	public static void main(String[] args) {
		new Game();
	}

	public Game() {
		INSTANCE = this;
		window = new Window("Halloween 2018", WIDTH, HEIGHT, this);
		this.start();

	}

	public synchronized void start() {
		thread = new Thread(this);
		menu = new Menu(this);
		userManager = new UserManager(this);
		options = new Options();
		countdown = new Countdown(this);
		registerForm = new NewForm(this);
		musicPlayer = new MusicPlayer(this);
		pause = new Pause(this);

		options.loadTruthsAndDares();

		// Initiate Events
		eventManager = new EventManager(this);
		//eventManager.getEvents().add(new ChooseTwoEvent());
		eventManager.getEvents().add(new TruthOrDareEvent());

		//register mouse listeners
		for (Event e : eventManager.getEvents()) {
			addMouseListener(e);
		}
		
		event = eventManager.getEvents().get(0);
		((TruthOrDareEvent)event).getRandomTruthAndDare();
		event.setUser(userManager.getUsers().get(0));
		
		addMouseListener(menu);
		addKeyListener(pause);

		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				tick();
				delta--;
			}

			if (running)
				render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// Inside Event
		if (gameState == State.EVENT_IN_PROGRESS) {

			event.render(g);

		} else if (gameState == State.COUNTDOWN) {
			countdown.render(g);

		} else if (gameState == State.MENU) {
			menu.render(g);

//			if (!musicPlayer.isPlaying())
//				musicPlayer.playMusic();
		} else if (gameState == State.TRUTH || gameState == State.DARE) {
			event.render(g);
		} else if (gameState == State.PAUSE || gameState == State.USER_LIST) {
			pause.render(g);
		}

		g.dispose();
		bs.show();
	}

	private void tick() {
		if (gameState == State.EVENT_IN_PROGRESS) {
			event.tick();

		} else if (gameState == State.COUNTDOWN) {
			countdown.tick();

		} else if (gameState == State.MENU) {
			menu.tick();
		} else if (gameState == State.TRUTH || gameState == State.DARE) {
			event.tick();
		} else if (gameState == State.PAUSE || gameState == State.USER_LIST) {
			pause.tick();
		}
	}

	public Options getOptions() {
		return options;
	}
	
	public Pause getPause() {
		return pause;
	}

	public NewForm getRegisterForm() {
		return registerForm;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setGameState(State gameState) {
		this.gameState = gameState;
	}

	public State getGameState() {
		return gameState;
	}

	public MusicPlayer getMusicPlayer() {
		return musicPlayer;
	}

	public Window getWindow() {
		return window;
	}

	public Countdown getCountdown() {
		return countdown;
	}

	public EventManager getEventManager() {
		return eventManager;
	}

	public Event getCurrentEvent() {
		return event;
	}

	public void setCurrentEvent(Event event) {
		this.event = event;
	}

	public enum State {
		MENU, GAME, COUNTDOWN, EVENT_IN_PROGRESS, TRUTH, DARE, PAUSE, USER_LIST;
	}
}
