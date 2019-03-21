package me.timo.hw18.events;

public class Dare {

	private int points, mPoints, fPoints;
	private boolean mwDare;
	private String dareText;

	public Dare(String dareText) {
		this.dareText = dareText;
		mwDare = false;
	}
	
	public boolean isMwDare() {
		return mwDare;
	}
	
	public void setMwDare(boolean mwDare) {
		this.mwDare = mwDare;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPoints() {
		return points;
	}

	public void setmPoints(int mPoints) {
		this.mPoints = mPoints;
	}

	public int getmPoints() {
		return mPoints;
	}

	public void setfPoints(int fPoints) {
		this.fPoints = fPoints;
	}

	public int getfPoints() {
		return fPoints;
	}

	public String getDareText() {
		return dareText;
	}

	public void setDareText(String dareText) {
		this.dareText = dareText;
	}

}
