package ch.k42.auroraprime.i2c;

import ch.k42.auroraprime.core.IMatrix;
import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.quorgs.IFrame8x8;
import com.pi4j.io.i2c.I2CDevice;
import org.omg.IOP.TAG_ALTERNATE_IIOP_ADDRESS;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 14.04.13
 * Time: 20:55
 * To change this template use File | Settings | File Templates.
 */
public class I2CMatrix implements IMatrix {
    private static final String TAG = "I2CMatrix";
    private I2CDevice device;
    private int ID;
    //private int serial;

    public I2CMatrix(int ID,I2CDevice device){
        this.ID=ID;
        this.device = device;
    }

    public boolean sendFrame(IFrame8x8 f){
        byte[] buf = new byte[24];
        int size = f.toBytes1bit(buf);  // Buffer should be long enough, if not it violates my protocol
        try {
            device.write(buf,0,size);
        } catch (IOException e) {
            Log.e(TAG,"Unable to send frame over I2C" + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * The ID of the Matrix
     * @return ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Get the I2C device represented by this
     * Matrix
     * @return device
     */
    public I2CDevice getDevice() {
        return device;
    }
}
