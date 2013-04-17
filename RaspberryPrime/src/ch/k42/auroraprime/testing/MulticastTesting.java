package ch.k42.auroraprime.testing;

import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.multicast.ALDevice;
import ch.k42.auroraprime.multicast.DeviceDiscovery;
import ch.k42.auroraprime.multicast.IDeviceDiscovery;
import ch.k42.auroraprime.multicast.MulticastListener;

import java.util.List;

public class MulticastTesting{
	/**
	 * 
	 * 
	 * 
	 * @param args
	 */
	// .../bin: java -cp . ch.k42.auroraprime.testing.TestFacility 
	
	public static void main(String[] args) {
		if(args.length==0){
			System.out.println("no args");
			return;
		}
		if(args[0].equals("client")){
			
			client();
				
		}else if(args[0].equals("server")){
			
			server();
			
		}
	}
	
	public static void client(){
		IDeviceDiscovery discovery = new DeviceDiscovery();
		List<ALDevice> list = discovery.getDiscoveredDevices();
        Log.v("List of discovered devices:");
		for(ALDevice dev : list){
			Log.v(dev);
		}
	}
	
	public static void server(){
		MulticastListener listener = new MulticastListener();
		listener.startListener();
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		listener.stopListener();
	}

}
