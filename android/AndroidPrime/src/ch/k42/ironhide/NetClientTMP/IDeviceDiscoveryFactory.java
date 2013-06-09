package ch.k42.ironhide.NetClientTMP;

public class IDeviceDiscoveryFactory {
	public static IDeviceDiscovery getInstance(){
		return new DeviceDiscovery();
	}
}
