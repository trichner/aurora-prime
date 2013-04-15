package ch.k42.auroraprime.quorgs;

public abstract class Quorg extends Thread {
	public abstract IFrame8x8 getFrame();
	protected volatile boolean quit = true;
	
	public void terminate(){
		quit = true;
	}
    public boolean isRunning(){
        return !quit;
    }
	
	@Override
	public abstract void run();
}
