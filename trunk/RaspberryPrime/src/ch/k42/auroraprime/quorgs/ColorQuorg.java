package ch.k42.auroraprime.quorgs;

import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.minions.Utils;

import java.awt.*;

/**
 * Just generates RandomFrames
 * @author Thomas
 *
 */
public class ColorQuorg extends Quorg {
	private IFrame8x8 frame;

    public ColorQuorg(){
        frame = Utils.getColorFrame(Color.black);
    }

	@Override
	public IFrame8x8 getFrame() {
		IFrame8x8 ret = frame;
		this.interrupt();
		return ret;
	}

	@Override
	public void run() {
        quit=false;
        Log.d("ColorQuorg","started");
		while(!quit){
			try {
				sleep(1000000);
			} catch (InterruptedException e) {}
		}
        Log.d("ColorQuorg","stopped");
	}

    private String[] settings;
    @Override
    public void initSettings(String[] settings) {
        this.settings = settings;
        if(settings.length>0){
            if(settings[0].equals("red")){
                frame = Utils.getColorFrame(Color.red);
            }else if(settings[0].equals("green")){
                frame = Utils.getColorFrame(Color.green);
            }else if(settings[0].equals("red")){
                frame = Utils.getColorFrame(Color.blue);
            }
        }
    }

    @Override
    public String[] getSettings() {
        return settings;
    }
}
