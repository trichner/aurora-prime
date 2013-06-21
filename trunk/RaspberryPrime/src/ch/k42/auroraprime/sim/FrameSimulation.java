package ch.k42.auroraprime.sim;

import ch.k42.auroraprime.core.IMatrix;
import ch.k42.auroraprime.quorgs.IFrame8x8;

public class FrameSimulation extends Thread implements IMatrix {

	FrameDrawer drawer;
	private volatile boolean quit = false;
	private int address;
	public FrameSimulation(int address){
		drawer = new FrameDrawer();
        this.address = address;
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

	public void shutdown(){
		quit = true;
	}


    @Override
    public boolean sendFrame(IFrame8x8 f) {
        drawer.updateFrame(f);
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getAddress() {
        return address;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
