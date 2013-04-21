package ch.k42.auroraprime.net;

import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.multicast.ALDevice;
import ch.k42.auroraprime.multicast.IDeviceDiscovery;
import ch.k42.auroraprime.multicast.IDeviceDiscoveryFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 4/19/13
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class MulticastTestClient {

    public static void main(String args[]){
        IDeviceDiscovery marcopolo = IDeviceDiscoveryFactory.getInstance();
        List<ALDevice> discoveredDevices = marcopolo.getDiscoveredDevices();

        for(int i=0;i<10;i++){
            if(discoveredDevices.size()>0){
                IClient client = new AthmosClient();
                try {
                    client.connect(discoveredDevices.get(0).getAddress());
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    Log.e("MulticastTestClient","Unable to open socket on " + discoveredDevices.get(0).getAddress());
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
            discoveredDevices = marcopolo.getDiscoveredDevices();
        }

    }
}
