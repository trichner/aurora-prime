package ch.k42.auroraprime.sim;

import ch.k42.auroraprime.executors.Sender;
import ch.k42.auroraprime.quorgs.Frame;

public class SimSender implements Sender {

	FrameSimulation simulator;
	public SimSender(){
		simulator = new FrameSimulation();
	}
	@Override
	public boolean sendFrame(Frame f) {
		simulator.receiveFrame(f);
		return true;
	}
	@Override
	public boolean connect() {
		simulator.start();
		return true;
	}
	@Override
	public boolean close() {
		simulator.shutdown();
		//simulator.join();
		return true;
	}

}
