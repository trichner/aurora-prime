package ch.k42.auroraprime.testing;

import java.awt.*;
import javax.swing.*;


public class Area51 extends Canvas {
    public Area51() {
    }
 
    public void paint(Graphics g) {
        /* We would be using this method only for the sake
         * of brevity throughout the current section. Note
         * that the Graphics class has been acquired along
         * with the method that we overrode. */
    	Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				g2.setColor(new Color(x*30,y*30,50));
				g2.fillRect(x*100, y*100, 100, 100);
			}
		}
    }
 
    public static void main(String[] args) {
    	Area51 canvas = new Area51();
        JFrame frame = new JFrame();
        frame.setSize(816, 840);
        //frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(canvas);
        frame.setVisible(true);
        
        
        
    }
}