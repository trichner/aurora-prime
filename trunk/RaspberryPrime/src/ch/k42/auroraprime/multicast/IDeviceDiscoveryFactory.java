package ch.k42.auroraprime.multicast;

public class IDeviceDiscoveryFactory {
	public static IDeviceDiscovery getInstance(){
		return new DeviceDiscovery();
	}
}
