package ch.k42.auroraprime.minions;

import java.awt.Color;
import java.util.Random;

import ch.k42.auroraprime.quorgs.Frame;
import ch.k42.auroraprime.quorgs.Frame1bit;
import ch.k42.auroraprime.quorgs.IFrame8x8;

public class Utils {
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
    public static IFrame8x8 getRandomFrame1bit(){
        Random rand = new Random();
        return new Frame1bit(rand.nextLong(),rand.nextLong(),rand.nextLong());
    }
}
