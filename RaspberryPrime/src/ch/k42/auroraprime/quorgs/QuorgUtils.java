package ch.k42.auroraprime.quorgs;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class QuorgUtils {

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
     * Fills a Frame with a given Digit, based on 1bit color depth
     * @param n a digit to parse
     * @return a pre-filled IFrame8x8
     */
    public static IFrame8x8 getDigitFrame1bit(int n){
        n %=10;
        long r=0;
        for (int i=0;i<n;i++){
            r = Bitfields.setBit(4,i,r); //TODO out of bounds if >8 !!!!
        }

        return new Frame1bit(r,0,0);
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
