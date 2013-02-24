package ch.k42.auroraprime.sim;

import java.awt.*;
import javax.swing.*;

import ch.k42.auroraprime.testing.Area51;


public class FrameDrawer extends Canvas{
    public FrameDrawer() {
    }
    ch.k42.auroraprime.quorgs.Frame frame;
    
    public void paint(Graphics g) {
        /* We would be using this method only for the sake
         * of brevity throughout the current section. Note
         * that the Graphics class has been acquired along
         * with the method that we overrode. */
    	Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				g2.setColor(frame.getColor(y, x));
				g2.fillRect(x*100, y*100, 100, 100);
			}
		}
    }
 
    public void setFrame(ch.k42.auroraprime.quorgs.Frame frame){
    	this.frame = frame;
    	this.repaint();
    }
    
    public void simulate() {
    	Area51 canvas = new Area51();
        JFrame frame = new JFrame();
        frame.setSize(816, 840);
        //frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(canvas);
        frame.setVisible(true); 
    }
}