package ch.k42.auroraprime.sim;

import ch.k42.auroraprime.quorgs.Frame;

public class FrameSimulation extends Thread {

	@Override
	public void run() {
		FrameDrawer drawer = new FrameDrawer();
		drawer.simulate();
	}
	
	public void receiveFrame(Frame f){
		
	}

	

}
