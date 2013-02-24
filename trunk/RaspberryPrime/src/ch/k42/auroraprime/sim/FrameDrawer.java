package ch.k42.auroraprime.sim;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;

import ch.k42.auroraprime.quorgs.Frame;


public class FrameDrawer extends Canvas{
	private static final long serialVersionUID = 3931465364089899146L;
	
    private Frame frame = new Frame(Color.cyan);
    private JFrame jframe;
    
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
 
    public void updateFrame(Frame frame){
    	this.frame = frame;
    	this.repaint();
    }
    
    public void simulate() {
    	//FrameDrawer canvas = new FrameDrawer();
    	jframe = new JFrame();
    	jframe.setSize(816, 840);
        //frame.setResizable(false);
    	jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	jframe.getContentPane().add(this);
    	jframe.setVisible(true); 
    }
    
    public void stop(){
    	jframe.setVisible(false); 
    	jframe.dispose();
    }
}