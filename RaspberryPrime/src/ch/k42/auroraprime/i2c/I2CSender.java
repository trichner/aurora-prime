package ch.k42.auroraprime.i2c;

import ch.k42.auroraprime.executors.Sender;
import ch.k42.auroraprime.i2c.testing.I2CUtils;
import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.quorgs.IFrame8x8;
import com.pi4j.io.i2c.I2CDevice;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 14.04.13
 * Time: 18:58
 * To change this template use File | Settings | File Templates.
 */
public class I2CSender implements Sender {
    private static final String TAG = "I2CSender";
    private Map<Integer,IMatrix> matrices;
    private boolean isConnected;

    @Override
    public boolean sendFrame(int id, IFrame8x8 f) {
        if(!matrices.containsKey(id)) return  false;
        IMatrix matrix = matrices.get(id);
        matrix.sendFrame(f);
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean connect() {
        List<I2CDevice> devices;
        matrices = new HashMap<Integer, IMatrix>();
        try {
            devices = I2CUtils.discoverDevices(I2CUtils.getDefaultBus());
            for(int i=0;i<devices.size();i++){
                matrices.put(i,new I2CMatrix(i,devices.get(i))); // TODO make ID less random
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
