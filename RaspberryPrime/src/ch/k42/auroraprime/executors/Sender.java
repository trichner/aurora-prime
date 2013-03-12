package ch.k42.auroraprime.executors;

import ch.k42.auroraprime.quorgs.Frame;

public interface Sender {
	public boolean sendFrame(Frame f);
	public boolean connect();
	public boolean disconnect();
	public boolean isConnected();
}
