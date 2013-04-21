package ch.k42.auroraprime.i2c;
import ch.k42.auroraprime.minions.Log;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import sun.util.resources.LocaleNames_be;

import java.io.IOException;
import java.util.Arrays;

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
        boolean bus0 = args[0].equals("0");
        int adr = Integer.parseInt(args[1]);

        I2CBus bus=null;
        try {
            Log.d(TAG,"Get bus nr. "+(bus0 ? I2CBus.BUS_0 : I2CBus.BUS_1));
            bus = I2CFactory.getInstance(bus0 ? I2CBus.BUS_0 : I2CBus.BUS_1);
            Log.d(TAG,String.format("Get device on address: %xh",adr));
            I2CDevice dev = bus.getDevice(adr);
            Log.d(TAG,"Reading some bits...");
            while (true){

//                //send some bytes
//                byte[] buf = {0x01,0x02,0x03,0x04,0x05};
//                //
//                try {
//                    dev.write(buf,0,buf.length);
//                    Log.d(TAG,"Sent "+buf.length+"bytes");
//                } catch (IOException e) {
//                    Log.e("Unable to send frame over I2C" + e.getMessage());
//                }

                byte[] rbuf = new byte[20];
                try {
                    int ret = dev.read();
                    //int ret = dev.read(rbuf,0,20);
                    System.out.println(Arrays.toString(rbuf));

                    Log.d(TAG,String.format("Read message: %4h",ret));

                }catch (Exception e){
                    Log.e("i2ctest"," Exception ");
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}

            }
//            dev.write((byte) 0x42);
//            Log.d(TAG,String.format("Read message: %4h",ret));
            //I2CUtils.discoverDevices(bus);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            Log.e(TAG, "IOException: " + e.getMessage());
        }finally {
              if(bus!=null){
                  try {
                      bus.close();
                  } catch (IOException e) {}
              }
        }


    }
}
