package ch.k42.auroraprime.executors;

import ch.k42.auroraprime.core.IMatrix;
import ch.k42.auroraprime.quorgs.IFrame8x8;

import java.util.Map;

public interface Sender {
	public boolean sendFrame(int id, IFrame8x8 f);
	public boolean connect();
	public boolean disconnect();
    public Map<Integer,IMatrix> getMatrices();
	public boolean isConnected();
}
