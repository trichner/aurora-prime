package ch.k42.auroraprime.i2c;

import ch.k42.auroraprime.core.IMatrix;
import ch.k42.auroraprime.executors.Sender;
import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.quorgs.IFrame8x8;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 14.04.13
 * Time: 18:58
 * To change this template use File | Settings | File Templates.
 */
public class I2CSender implements Sender {
    private static final String TAG = "I2CSender";
    private ConcurrentHashMap<Integer,IMatrix> matrices;
    private boolean isConnected;
    private I2CBus bus;

    @Override
    public boolean sendFrame(int address, IFrame8x8 f) {
        if(!matrices.containsKey(address)) return  false;
        IMatrix matrix = matrices.get(address);
        matrix.sendFrame(f);
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean connect() {
        Map<Integer,I2CDevice> devices;

        matrices = new ConcurrentHashMap<>();
        try {
            bus = I2CUtils.getDefaultBus();

            devices = I2CUtils.discoverDevices(bus); //TODO dicover from time to time

            for(Integer i : devices.keySet()){
                matrices.put(i,new I2CMatrix(i,devices.get(i)));
            }
        } catch (IOException e) {
            Log.e(TAG,"Unable to discover i2c devices: "+e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return false;
        }
        if(matrices.size()>0) isConnected = true;
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean disconnect() {
        isConnected = false;
        try {
            bus.close();
        } catch (IOException e) {
            Log.e(TAG,"Failed to close i2c bus: "+e.getMessage());
        }
        return true;  //TODO close i2c bus? -> better not?
    }

    @Override
    public Map<Integer,IMatrix> getMatrices() {
        return matrices;
    }

    @Override
    public boolean isConnected() {
        return isConnected;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
