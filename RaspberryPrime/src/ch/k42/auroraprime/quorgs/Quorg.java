package ch.k42.auroraprime.quorgs;

public abstract class Quorg extends Thread {
	public abstract IFrame8x8 getFrame();
	protected volatile boolean quit = true;
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
}
