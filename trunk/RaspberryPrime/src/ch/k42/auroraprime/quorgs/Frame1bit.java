package ch.k42.auroraprime.quorgs;

import ch.k42.auroraprime.minions.Log;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 14.04.13
 * Time: 21:29
 * To change this template use File | Settings | File Templates.
 */
public class Frame1bit implements IFrame8x8{
    private static final int SIZE = 8;
    private long red=0;
    private long green=0;
    private long blue=0;

    public Frame1bit(){};

    public Frame1bit(long r,long g,long b){
        this.red = r;
        this.blue  = b;
        this.green = g;
    }
    @Override
    public boolean setColor(int row, int col, Color color) {
        int colors = color.getRGB();

        if((colors & 0xFF0000)!=0)
            red = Bitfields.setBit(col,row,red);
        else
            red = Bitfields.delBit(col,row,red);
        if((colors & 0x00FF00)!=0)
            green = Bitfields.setBit(col,row,green);
        else
            green = Bitfields.delBit(col, row, green);

        if((colors & 0x0000FF)!=0)
            blue = Bitfields.setBit(col,row,blue);
        else
            blue = Bitfields.delBit(col, row, blue);
        //Log.d("", red + color.toString()+ "  " + String.format("%h",colors));

        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean setColor(Color[][] colors) {
        for(int x=0;x<SIZE;x++)
            for(int y=0;y<SIZE;y++)
                setColor(y,x,colors[x][y]);

        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Color getColor(int row, int col) {
        return new Color(Bitfields.isSet(col,row,red) ? 255 : 0,Bitfields.isSet(col,row,green) ? 255 : 0,Bitfields.isSet(col,row,blue) ? 255 : 0);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int toBytes8bit(byte[] buf) {
        return 0;  //TODO To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int toBytes1bit(byte[] buf) {
        for(int i=0;i<SIZE;i++){
            buf[       i] = (byte) (red   & 0xFF);
            buf[  SIZE+i] = (byte) (green & 0xFF);
            buf[2*SIZE+i] = (byte) (blue  & 0xFF);
            red   >>>= 8;
            green >>>= 8;
            blue  >>>= 8;
        }
        return 3*SIZE;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
