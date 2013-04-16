package ch.k42.auroraprime.net;

import java.io.Serializable;

public class QuorgSettings implements Serializable {
	private int screen;
	private int quorgID;
	private String[] settings;
	public int getScreen() {
		return screen;
	}
	public void setScreen(int screen) {
		this.screen = screen;
	}
	public int getQuorgID() {
		return quorgID;
	}
	public void setQuorgID(int quorgID) {
		this.quorgID = quorgID;
	}
	public String[] getSettings() {
		return settings;
	}
	public void setSettings(String[] settings) {
		this.settings = settings;
	}
	
	
}

