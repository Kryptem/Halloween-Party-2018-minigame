package me.timo.hw18.utils;

import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import me.timo.hw18.Game;

public class Utils {

	public static boolean mouseOver(int mx, int my,int x, int y, int width, int height) {
		if (mx > x && mx < x+width) {
			if (my > y && my < y + height) {
				return true;
			}
		}
		return false;
	}
	
	public static BufferedImage loadImage(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(Utils.class.getClassLoader().getResourceAsStream("res/" + path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public static Polygon createTopTriangle() {
		Polygon p = new Polygon();
		
		p.addPoint(0, 0);
		p.addPoint(0, Game.HEIGHT - 385);
		p.addPoint(Game.WIDTH, 0);

		
		return p;
	}

	public static Polygon createBottomTriangle() {
		Polygon p = new Polygon();
		p.addPoint(Game.WIDTH, 0);
		p.addPoint(Game.WIDTH, Game.HEIGHT);
		p.addPoint(0, Game.HEIGHT - 385);
		return p;
	}
}
