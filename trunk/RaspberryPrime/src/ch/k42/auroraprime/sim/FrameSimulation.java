package ch.k42.auroraprime.sim;

import ch.k42.auroraprime.i2c.IMatrix;
import ch.k42.auroraprime.quorgs.Frame;
import ch.k42.auroraprime.quorgs.Frame1bit;
import ch.k42.auroraprime.quorgs.IFrame8x8;

public class FrameSimulation extends Thread implements IMatrix {

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

	public void shutdown(){
		quit = true;
	}


    @Override
    public boolean sendFrame(IFrame8x8 f) {
        drawer.updateFrame(f);
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getID() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
