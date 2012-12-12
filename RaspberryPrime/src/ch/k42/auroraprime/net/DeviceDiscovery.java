package ch.k42.auroraprime.net;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import ch.k42.auroraprime.minions.Log;

public class DeviceDiscovery implements IDeviceDiscovery {
	private final static int TIMEOUT = 5000;
	@Override
	public List<ALDevice> getDiscoveredDevices() {
		DiscoveryThread test;
		List<ALDevice> list = new ArrayList<>();
		try {
			test = new DiscoveryThread("myPhone", list);
			test.start();
			
			test.join(TIMEOUT);
			
			
			//---- print discovered devices
			Log.v("Found devices:");
			for(ALDevice dev : list){
				Log.v(dev);
			}
		} catch (SocketException e) {
			Log.e(e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			Log.e(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}


}
