package ch.k42.auroraprime.quorgs;

import ch.k42.auroraprime.net.DTO.QuorgSettings;

public abstract class Quorg extends Thread {
	public abstract IFrame8x8 getFrame();
	protected volatile boolean quit = true;
    protected QuorgSettings settings;

    private int MatrixID = -1;


    public void setMatrixID(int matrixID){
        this.MatrixID = matrixID;
    }

    public int getMatrixID() {
        return MatrixID;
    }

    public void terminate(){
		quit = true;
        this.interrupt();
	}
    public boolean isRunning(){
        return !quit;
    }
	
	@Override
	public abstract void run();

    public abstract void initSettings(String[] settings);
    public abstract String[] getSettings();
}
