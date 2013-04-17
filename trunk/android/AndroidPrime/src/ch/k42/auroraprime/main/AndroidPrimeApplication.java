package ch.k42.auroraprime.main;

import ch.k42.auroraprime.net.IDeviceDiscovery;
import android.app.Application;



public class AndroidPrimeApplication extends Application {
	
	public IDeviceDiscovery getDeviceDiscoverer() {
		return deviceDiscoverer;
	}

	public void setDeviceDiscoverer(IDeviceDiscovery deviceDiscoverer) {
		this.deviceDiscoverer = deviceDiscoverer;
	}

	IDeviceDiscovery deviceDiscoverer;
	
	@Override
	public void onCreate() {
		super.onCreate();
	}

}
