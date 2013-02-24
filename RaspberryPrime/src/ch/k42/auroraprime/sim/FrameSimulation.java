package ch.k42.auroraprime.sim;

import ch.k42.auroraprime.minions.Utils;
import ch.k42.auroraprime.quorgs.Frame;

public class FrameSimulation extends Thread {

	FrameDrawer drawer;
	private volatile boolean quit = false;
	
	public FrameSimulation(){
		drawer = new FrameDrawer();
	}
	
	@Override
	public void run() {
		drawer.simulate();
		
		while(!quit){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {}
		}
		
		drawer.stop();
	}
	
	public void receiveFrame(Frame f){
		drawer.updateFrame(f);
	}
	
	public void shutdown(){
		quit = true;
	}

	

}
