package ch.k42.auroraprime.net;

import testing.DeviceDiscoveryTestdummy;

public class DeviceDiscoveryFactory {
	public static IDeviceDiscovery getInstance(){
		return new DeviceDiscovery();
	}
}
