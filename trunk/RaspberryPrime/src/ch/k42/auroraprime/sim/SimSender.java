package ch.k42.auroraprime.sim;

import ch.k42.auroraprime.executors.Sender;
import ch.k42.auroraprime.core.IMatrix;
import ch.k42.auroraprime.quorgs.IFrame8x8;

import java.util.HashMap;
import java.util.Map;

public class SimSender implements Sender {
	private boolean isConnected = false;
	private Map<Integer,FrameSimulation> matrices;

	public SimSender(){
        matrices = new HashMap<Integer, FrameSimulation>();
        matrices.put(1,new FrameSimulation(1));
        matrices.put(2,new FrameSimulation(2));
        matrices.put(3,new FrameSimulation(3));
        matrices.put(4,new FrameSimulation(4));
		//simulator = new FrameSimulation();
	}

    @Override
    public boolean sendFrame(int id, IFrame8x8 f) {
        FrameSimulation simulator = matrices.get(id);

        simulator.sendFrame(f); //TODO adapt for 1bit
        return true;
    }

    @Override
	public boolean connect() {
        if(isConnected) return true;
        for(FrameSimulation m : matrices.values())
		    m.start();

        isConnected = true;
		return true;
	}

    @Override
	public boolean disconnect() {
		isConnected = false;
        for(FrameSimulation m : matrices.values())
            m.shutdown();
		return true;
	}

    @Override
    public Map<Integer, IMatrix> getMatrices() {
        Map ret = new HashMap<Integer, IMatrix>();
        ret.putAll(matrices);
        return  ret;
    }

    @Override
	public boolean isConnected() {
		return isConnected;
	}

}
