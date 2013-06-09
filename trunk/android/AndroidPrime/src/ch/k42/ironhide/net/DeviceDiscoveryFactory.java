package ch.k42.ironhide.net;


public class DeviceDiscoveryFactory {
	public static IDeviceDiscovery getInstance(){
		return new DeviceDiscovery();
	}
}
