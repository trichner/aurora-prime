package ch.k42.auroraprime.testing;

import java.util.List;

import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.net.ALDevice;
import ch.k42.auroraprime.net.DeviceDiscovery;
import ch.k42.auroraprime.net.IDeviceDiscovery;
import ch.k42.auroraprime.net.MulticastListener;

public class TestFacility{
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
