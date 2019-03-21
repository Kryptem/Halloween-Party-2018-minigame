package me.timo.hw18.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;

import me.timo.hw18.events.Dare;

public class Options {

	private boolean playMusic;
	private double countdownTime;

	private List<JCheckBox> checkBoxes;

	private boolean onlyTruth, onlyDare;
	private List<String> truths;
	private List<Dare> dares;

	public Options() {
		checkBoxes = new ArrayList<>();
		truths = new ArrayList<>();
		dares = new ArrayList<>();
	}

	public void loadTruthsAndDares() {
		InputStream stream = this.getClass().getClassLoader().getResourceAsStream("res/truths.txt");

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(stream, "ISO-8859-1"));
			String line;

			while ((line = br.readLine()) != null) {
				truths.add(line);
			}
			br.close();

			stream = this.getClass().getClassLoader().getResourceAsStream("res/dares.txt");
			br = new BufferedReader(new InputStreamReader(stream, "ISO-8859-1"));

			while ((line = br.readLine()) != null) {
				String[] splitted = line.split("\\*");
				Dare dare = new Dare(splitted[0]);
				//System.out.println(splitted.length);
				int mPoints = 0;
				int fPoints = 0;
				
				if (splitted[1].contains("/")) {
					mPoints = Integer.parseInt(splitted[1].split("/")[0].split(":")[1]);
					fPoints = Integer.parseInt(splitted[1].split("/")[1].split(":")[1]);
					
					dare.setmPoints(mPoints);
					dare.setfPoints(fPoints);
					dare.setPoints(0);
					dare.setMwDare(true);
					dares.add(dare);
					continue;
				}
				
				dare.setPoints(Integer.parseInt(splitted[1]));

				dares.add(dare);
			}

			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * File file = new File("res\\truths.txt");
		 * 
		 * if (file.exists()) {
		 * 
		 * try { BufferedReader br = new BufferedReader(new FileReader(file)); String
		 * line;
		 * 
		 * while ((line = br.readLine()) != null) { truths.add(line); } br.close();
		 * 
		 * file = new File("res\\dares.txt"); br = new BufferedReader(new
		 * FileReader(file));
		 * 
		 * while ((line = br.readLine()) != null) { dares.add(line); }
		 * 
		 * br.close();
		 * 
		 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException
		 * e) { e.printStackTrace(); } }
		 */

	}

	public boolean isOnlyDare() {
		return onlyDare;
	}

	public void setOnlyDare(boolean onlyDare) {
		this.onlyDare = onlyDare;
	}

	public boolean isOnlyTruth() {
		return onlyTruth;
	}

	public void setOnlyTruth(boolean onlyTruth) {
		this.onlyTruth = onlyTruth;
	}

	public boolean playsMusic() {
		return playMusic;
	}

	public void setPlayMusic(boolean playMusic) {
		this.playMusic = playMusic;
	}

	public double getCountdownTime() {
		return countdownTime;
	}

	public void setCountdownTime(double countdownTime) {
		this.countdownTime = countdownTime;
	}

	public List<JCheckBox> getCheckBoxes() {
		return checkBoxes;
	}

	public List<String> getTruths() {
		return truths;
	}

	public List<Dare> getDares() {
		return dares;
	}

}
