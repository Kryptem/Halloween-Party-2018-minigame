package me.timo.hw18;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = -6544163527870411578L;
	private JFrame frame;
	private Game game;

	public Window(String title, int width, int height, Game game) {
		frame = new JFrame(title);
		this.game = game;

		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		//playBackground();
	}

	public JFrame getFrame() {
		return frame;
	}

	public Game getGame() {
		return game;
	}
	
}
