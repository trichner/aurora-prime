package testing;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import ch.k42.auroraprime.net.ALDevice;
import ch.k42.auroraprime.net.IDeviceDiscovery;

public class DeviceDiscoveryTestdummy implements IDeviceDiscovery {

	private List<ALDevice> list;
	
	public List<ALDevice> getDiscoveredDevices() {
		// TODO Auto-generated method stub

		list = new ArrayList<ALDevice>();
		
		InetAddress inetAddress;
		try {
		
		inetAddress = InetAddress.getByName("1.2.3.4");
		ALDevice alDevice = new ALDevice(inetAddress, "Horsts Lampe");
		list.add(alDevice);
		
		InetAddress inetAddress2 = InetAddress.getByName("1.2.3.5");
		ALDevice alDevice2 = new ALDevice(inetAddress2, "Manfreds Lampe");
		list.add(alDevice2);
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
