package ch.k42.auroraprime.quorgs;

import ch.k42.auroraprime.minions.Log;

import java.awt.*;

/**
 * This class represents a Frame containing all information about the color of
 * a 8x8 Matrix
 * 
 * @author Thomas Richner
 *
 */
public interface IFrame8x8{
    public static final int SIZE = 8;
	/**
	 * Set a specific Dot in the Matrix to a new Color
	 * @param row from 0 to 8
	 * @param col from 0 to 8
	 * @param color the Color for the new dot
	 * @return true if successful, else false
	 */
	public boolean setColor(int row,int col,Color color);
    /**
     * Set a specific Dot in the Matrix to a new Color
     * @param colors an 8x8 array of Colors
     * @return true if successful, else false
     */
    public boolean setColor(Color[][] colors);
    /**
     * Get the color of a specific dot in the Matrix
     * @param row from 0 to 8
     * @param col from 0 to 8
     * @return color on position row/col
     */
	public Color getColor(int row, int col);

    /**
     * Converts the Frame to a serialized version
     * for low-level IO, 8bit color depth
     * @param buf a buffer to fill
     * @return number of bytes written
     */
    public int toBytes8bit(byte[] buf);
    /**
     * Converts the Frame to a serialized version
     * for low-level IO, only 1bit color depth
     * @param buf a buffer to fill
     * @return number of bytes written
     */
    public int toBytes1bit(byte[] buf);
}
