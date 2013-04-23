package ch.k42.auroraprime.main;

import ch.k24.auroraprime.quorg.Quorg;
import ch.k42.auroraprime.net.ClientFactory;
import ch.k42.auroraprime.net.IClient;
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
	IClient connectClient;
	QuorgField[] quorgFields;
	
	public void newClient() {
		connectClient = ClientFactory.getInstance();
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		connectClient = ClientFactory.getInstance();
		quorgFields = new QuorgField[4];
		for (int i=0;i<4;i++){
			quorgFields[i] = new QuorgField(i+1);
		}
	
	}

}

