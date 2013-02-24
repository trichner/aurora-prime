package ch.k42.auroraprime.quorgs;

import java.awt.Color;

import ch.k42.auroraprime.minions.Log;
/**
 * This class represents a Frame containing all information about the color of
 * a Matrix
 * 
 * @author Thomas Richner
 *
 */
public class Frame {
	
		//ready for 8 bit PWM
	
	// Size of the Matrix
	public static final int N_ROW = 8;
	public static final int N_COL = 32;
	
	// Bitmask for the % operator for the given size of the matrix, works only if size=2^n
	private static final int N_ROW_BMASK = 0x07;
	private static final int N_COL_BMASK = 0x1F;
	
	private Color matrix[][] = new Color[N_ROW][N_COL];

	
	private void initMatrix(Color color){
		for(int i=0; i<N_ROW;i++){
			for(int j=0; j<N_COL;j++){
				matrix[i][j] = color;
			}
		}
	}
	/**
	 * Creates a new Frame all in black
	 */
	public Frame(){
		initMatrix(new Color(0,0,0));
	}
	/**
	 * Creates a new Frame all in one color
	 * @param color the initializing color
	 */
	public Frame(Color color){
		if(color==null)
			initMatrix(new Color(0,0,0));
		else
			initMatrix(color);
	}
	/**
	 * Creates a new Frame all in the same Color.
	 * If r,g or b are out of range, it will be masked
	 * out like this <pre>r = r & 0xff</pre>
	 * @param r Red color value
	 * @param g Green color value
	 * @param b Blue color value
	 */
	public Frame(int r,int g,int b){
		//this makes sure r,g and b are between 0-255
		r = r & 0xFF;
		g = g & 0xFF;
		b = b & 0xFF;
		
		initMatrix(new Color(r,g,b));
	}
	/**
	 * Set a specific Dot in the Matrix to a new Color
	 * @param row from 0 to Frame.N_ROW
	 * @param col from 0 to Frame.N_COL
	 * @param color the Color for the new dot
	 * @return true if successful, else false
	 */
	public boolean setColor(int row,int col,Color color){
		if(((row & (~N_ROW_BMASK))!=0 )||( (col & (~N_COL_BMASK))!=0 || color==null)){
			Log.e("Frame setColor(...) Could not set color on position " + row + "/" + col + " with color " + color);
			return false;
		}
			
		matrix[row][col] = color;
		return true;
	}
	
	public Color getColor(int row, int col){
		
		if(((row & (~N_ROW_BMASK))!=0) || ((col & (~N_COL_BMASK))!=0)){ // out of range?
			Log.e("Frame getColor(row,col) is out of Range! row/col: " + row + "/" + col); 
			return null;
		}
			
		return matrix[row][col];
	}
}
