package ch.k42.auroraprime.minions;

import ch.k42.auroraprime.quorgs.Frame1bit;
import ch.k42.auroraprime.quorgs.IFrame8x8;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Utils {
    /**
     * Fills a Frame with Random colors, based on 8bit color depth
     * @return a pre-filled IFrame8x8
     */
	public static IFrame8x8 getRandomFrame(){
		IFrame8x8 f = new Frame1bit();
		Random rand = new Random();
		for(int col = 0;col<IFrame8x8.SIZE; col++){
			for(int row = 0;row<IFrame8x8.SIZE; row++){
				f.setColor(row, col, new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
			}
		}
		return f;
	}

    /**
     * Fills a Frame with Random colors, based on 1bit color depth
     * @return a pre-filled IFrame8x8
     */
    public static IFrame8x8 getRandomFrame1bit(){
        Random rand = new Random();
        return new Frame1bit(rand.nextLong(),rand.nextLong(),rand.nextLong());
    }

    /**
     * Fills a Frame with one color
     * @param c color to fill with
     * @return a pre-filled IFrame8x8
     */
    public static IFrame8x8 getColorFrame(Color c){
        IFrame8x8 frame8x8 = new Frame1bit();
        Color[][] colors = new Color[8][8];
        for (int i=0;i<IFrame8x8.SIZE;i++){
            Arrays.fill(colors[i],c);
        }
        return frame8x8;
    }
}
