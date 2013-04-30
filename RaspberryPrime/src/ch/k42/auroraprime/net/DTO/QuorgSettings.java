package ch.k42.auroraprime.net.DTO;

import java.io.Serializable;

public class QuorgSettings implements Serializable {
	private int matrixID;

	private QUORG quorgID;

    public enum QUORG{
        RANDOM(1),
        OFF(2),
        GREENFIELD(3),
        BLUEFIELD(4);
        public final int number;
        QUORG(int number){
            this.number =number;
        }
    }

	private String[] settings;
	public int getScreen() {
		return matrixID;
	}
	public void setScreen(int matrixID) {
		this.matrixID = matrixID;
	}
	public QUORG getQuorg() {
		return quorgID;
	}
	public void setQuorg(QUORG quorg) {
		this.quorgID = quorgID;
	}
	public String[] getSettings() {
		return settings;
	}
	public void setSettings(String[] settings) {
		this.settings = settings;
	}
	
	
}

