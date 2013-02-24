package ch.k42.auroraprime.quorgs;

public abstract class Quorg extends Thread {
	public abstract Frame getFrame();
	protected volatile boolean quit = false;
	
	public void terminate(){
		quit = true;
	}
	
	@Override
	public abstract void run();
}
