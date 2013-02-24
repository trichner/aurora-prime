package ch.k42.auroraprime.minions;

import java.awt.Color;
import java.util.Random;

import ch.k42.auroraprime.quorgs.Frame;

public class Utils {
	public static Frame getRandomFrame(){
		Frame f = new Frame();
		Random rand = new Random();
		for(int col = 0;col<Frame.N_COL; col++){
			for(int row = 0;row<Frame.N_ROW; row++){
				f.setColor(row, col, new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
			}
		}
		return f;
	}
}
