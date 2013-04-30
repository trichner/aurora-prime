package ch.k42.auroraprime.main;

import ch.k24.auroraprime.quorg.Quorg;
import ch.k42.auroraprime.R;
import ch.k42.auroraprime.dto.QuorgSettings;
import ch.k42.auroraprime.dto.QuorgSettings.QUORG;

/**
 * A class used to store the information/state
 * of one of the matrix fields
 * 
 * @Author Philipp Bšsch
 */
public class QuorgField {
	
	private int fieldNumber;
	private int fieldID;
	private Quorg activeQuorg;
	private boolean isRunning;
	private QuorgSettings settings;
	
	public QuorgField(int fieldNumber){
		super();
		this.fieldNumber = fieldNumber;
		this.fieldID = -1;
		Quorg noQuorg = new Quorg(R.drawable.ic_field_000,QUORG.OFF, "Empty Quorg", false);
		this.activeQuorg = noQuorg;
		this.isRunning = false;
		this.settings = null;	
	}
	public QuorgField(int fieldNumber, Quorg activeQuorg, boolean isRunning){
		super();
		this.fieldNumber = fieldNumber;
		this.fieldID = -1;
		this.activeQuorg = activeQuorg;
		this.isRunning = isRunning;
		this.settings = null;
	}
	public QuorgField(int fieldNumber, Quorg activeQuorg, boolean isRunning, QuorgSettings settings){
		super();
		this.fieldNumber = fieldNumber;
		this.fieldID = -1;
		this.activeQuorg = activeQuorg;
		this.isRunning = isRunning;
		this.settings = settings;
	}
	
	public void emptyField() {
		//TODO
	}
	
	public int getFieldID() {
		return fieldID;
	}
	public void setFieldID(int fieldID) {
		this.fieldID = fieldID;
	}
	
	public int getFieldNumber() {
		return fieldNumber;
	}
	public void setFieldNumber(int fieldNumber) {
		this.fieldNumber = fieldNumber;
	}
	public Quorg getActiveQuorg() {
		return activeQuorg;
	}
	public void setActiveQuorg(Quorg activeQuorg) {
		this.activeQuorg = activeQuorg;
	}
	public boolean isRunning() {
		return isRunning;
	}
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	public QuorgSettings getSettings() {
		return settings;
	}
	public void setSettings(QuorgSettings settings) {
		this.settings = settings;
	}

}
