package ch.k42.auroraprime.quorgs;

import ch.k42.auroraprime.minions.Utils;

/**
 * Just generates RandomFrames
 * @author Thomas
 *
 */
public class RandomQuorg extends Quorg {
	private Frame frame = Utils.getRandomFrame();

	@Override
	public Frame getFrame() {
		Frame ret = frame;
		this.interrupt();
		return ret;
	}

	@Override
	public void run() {
		while(!quit){
			frame = Utils.getRandomFrame();
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {}
		}
	}
}
