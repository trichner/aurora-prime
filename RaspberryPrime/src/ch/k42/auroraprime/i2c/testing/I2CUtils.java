package ch.k42.auroraprime.i2c.testing;

import ch.k42.auroraprime.minions.Log;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 14.04.13
 * Time: 19:05
 * To change this template use File | Settings | File Templates.
 */
public class I2CUtils{

    private static final String TAG = "I2CUtils";
    private static final byte maxAdress = 0x0F;

    public static List<I2CDevice> discoverDevices(I2CBus bus){
        Log.d(TAG,"start i2c discovery");
        byte address = 0x00;
        List<I2CDevice> devices = new ArrayList<I2CDevice>();
        I2CDevice tdev;
        while (address<maxAdress){
            try {
                Log.d(TAG,"discover on adress "+ address);
                tdev = bus.getDevice(address);
                int dummy = tdev.read();
                // success?
                Log.d(TAG,"discovered on adress "+ address);
                devices.add(tdev);

            } catch (IOException e) {
                Log.d(TAG,"failed to read from "+ address);
                //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            address++;   // next adress
        }
        Log.d(TAG,"discovered "+ devices.size() + " devices");
        return devices;
    }

    public static I2CBus getDefaultBus() throws IOException {
            return I2CFactory.getInstance(I2CBus.BUS_1);
    }


}
