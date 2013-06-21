package ch.k42.auroraprime.quorgs;

import java.awt.*;
import java.util.Random;

public class NEOUtils {
	
	private static final String TAG = "GENERALUTILS";
	private static final boolean D = false;
	private static final int[][] empty8x8 = {{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};


    //==== NEO Compatibility
    public static final int NEO_OFF 		= 0;
    public static final int NEO_RED 		= 1;
    public static final int NEO_GREEN 	= 2;
    public static final int NEO_BLUE 		= 3;
    public static final int NEO_YELLOW 	= 4;
    public static final int NEO_TURK 		= 5; //Alias
    public static final int NEO_CYAN 		= 5;
    public static final int NEO_PINK 		= 6; //Alias
    public static final int NEO_MAGENTA		= 6;
    public static final int NEO_WHITE 	= 7;

	public static String randomCharString()
	{
		Random r = new Random();
		char c = (char)(r.nextInt(26) + 'a');
		return String.valueOf(c);
		
	}
	public static int[][] getEmpty8x8(){
		int[][] array = new int[8][8];
		for(int i = 0;i<8;i++){
			System.arraycopy(empty8x8[i], 0, array[i], 0, 8);
		}
		return array;
	}
	/**
	 * static method randomArray
	 * creates an array with random entries
	 * @return
	 */
	public static int[][] randomArray(int m,int n)
	{
		Random rand = new Random();
		int[][] array = new int[m][n];
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				array[i][j] = rand.nextInt(2);
			}
		}	
		return array;
	}
	
	/**
	 * static method emptyArray
	 * creates an array with all entries = zero
	 * @return
	 */
	public static int[][] emptyArray(int m, int n)
	{
		if(m==8&&n==8) return getEmpty8x8();
		
		int[][] array = new int[m][n]; // default Array, makes sure it contains zeros
    	for (int i=0;i<m;i++){
    		for (int j=0;j<n;j++){
    			array[i][j] = 0;
    		}
    	}
		return array;
	}
	/**
	 * method fillArray - fills the array with a constant value c
	 * @param m
	 * @param n
	 * @param c the colorcode
	 * @return
	 */
	public static int[][] fillArray(int m, int n, int c)
	{
		int[][] array = new int[m][n]; // default Array, makes sure it contains zeros
    	for (int i=0;i<m;i++){
    		for (int j=0;j<n;j++){
    			array[i][j] = c;
    		}
    	}
		return array;
	}

    public static int toNEOColor(String color){
        Color c = Color.decode(color);
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        int neocolor;
        if(red>127){
            if(blue>127){
                if(green>127){
                    neocolor = NEO_WHITE;
                }else{
                    neocolor = NEO_PINK;
                }
            }else{
                if(green>127){
                    neocolor = NEO_YELLOW;
                }else{
                    neocolor = NEO_RED;
                }
            }
        }else{
            if(blue>127){
                if(green>127){
                    neocolor = NEO_TURK;
                }else{
                    neocolor = NEO_BLUE;
                }
            }else{
                if(green>127){
                    neocolor = NEO_GREEN;
                }else{
                    neocolor= NEO_OFF;
                }
            }
        }
        return neocolor;
    }
}
