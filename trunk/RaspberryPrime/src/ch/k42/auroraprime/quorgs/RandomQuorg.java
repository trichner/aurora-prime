package ch.k42.auroraprime.quorgs;

import ch.k42.auroraprime.minions.Log;

/**
 * Just generates RandomFrames
 * @author Thomas
 *
 */
public class RandomQuorg extends Quorg {
	private IFrame8x8 frame = QuorgUtils.getRandomFrame1bit();

    public RandomQuorg(String[] settings){
        super(settings);
    }


	@Override
	public IFrame8x8 getFrame() {
		IFrame8x8 ret = frame;
		this.interrupt();
		return ret;
	}

    @Override
    public String getQuorgName() {
        return "RandomQuorg";
    }

    @Override
	public void run() {
        quit=false;
        Log.d("RandomQuorg","started");
		while(!quit){
            //Log.d("RandomQuorg","generating random quorg...");
			frame = QuorgUtils.getRandomFrame1bit();
			
			try {
				this.sleep(10000);
			} catch (InterruptedException e) {}
		}
        Log.d("RandomQuorg","stopped");
	}
}
