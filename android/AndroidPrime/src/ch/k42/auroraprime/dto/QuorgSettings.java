package ch.k42.auroraprime.net.DTO;

import java.io.Serializable;

public class QuorgSettings implements Serializable {
    public enum QUORG{
        OFF(0,"Off"),
        REDLIGHT(1,"Redlight"),
        GREENLIGHT(2,"Bluefield"),
        BLUELIGHT(3,"Greenfield"),
        CLOCK(4,"Clock"),
        RANDOM(5,"Random"),
        MAIL(6,"Mail"),
        WEATHER(7,"Weather"),
        MATRIX(8,"Matrix"),
        AUDIOVISUALIZER(9,"Audiovisualizer"),
        REDDIT(10,"Reddit");
        public final int number;
        public final String name;
        QUORG(int number,String name){
            this.number =number;
            this.name = "FIXME"; //FIXME
        }
    }

    private int matrixID;
    private QUORG quorgID;

	private String[] settings;
	public int getScreen() {
		return matrixID;
	}
	public void setMatrixID(int matrixID) {
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

