package ch.k42.auroraprime.i2c.testing;

import ch.k42.auroraprime.minions.Log;
import com.pi4j.io.i2c.I2CBus;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 14.04.13
 * Time: 19:56
 * To change this template use File | Settings | File Templates.
 */
public class I2CTest {
    private static final String TAG= "I2CTest";
    public static void main(String args[]){
        I2CBus bus=null;
        try {
            bus = I2CUtils.getDefaultBus();
            I2CUtils.discoverDevices(bus);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            Log.e(TAG, "Unable to get i2c bus: " + e.getMessage());
        }finally {
              if(bus!=null){
                  try {
                      bus.close();
                  } catch (IOException e) {}
              }
        }


    }
}
