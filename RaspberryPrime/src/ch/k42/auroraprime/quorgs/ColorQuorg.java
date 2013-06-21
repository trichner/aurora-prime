package ch.k42.auroraprime.quorgs;

import ch.k42.auroraprime.minions.Log;

import java.awt.*;

/**
 * Just generates RandomFrames
 * @author Thomas
 *
 */
public class ColorQuorg extends Quorg {
	private IFrame8x8 frame;

    /**
     *
     * @param settings color (Color)
     */
    public ColorQuorg(String[] settings){
        super(settings);
        if(settings.length>0)
            frame = QuorgUtils.getColorFrame(Color.decode(settings[0]));
    }

	@Override
	public IFrame8x8 getFrame() {
		IFrame8x8 ret = frame;
		this.interrupt();
		return ret;
	}

    @Override
    public String getQuorgName() {
        return "ColorQuorg";
    }

    @Override
	public void run() {
        quit=false;
        Log.vv("ColorQuorg","started");
		while(!quit){
			try {
				sleep(1000000);
			} catch (InterruptedException e) {}
		}
        Log.vv("ColorQuorg","stopped");
	}

}
