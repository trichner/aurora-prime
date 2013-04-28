package ch.k42.auroraprime.main;

import ch.k24.auroraprime.quorg.Quorg;
import ch.k42.auroraprime.net.ClientFactory;
import ch.k42.auroraprime.net.IClient;
import ch.k42.auroraprime.net.IDeviceDiscovery;
import android.app.Application;

/**
 * application framework that stores values
 * used in all activities of the application
 * 
 * 
 * @Author Philipp Bšsch
 */
public class AndroidPrimeApplication extends Application {
	
	
	/**
	 * return the Device Discoverer
	 * 
	 *
	 */
	public IDeviceDiscovery getDeviceDiscoverer() {
		return deviceDiscoverer;
	}
	
	
	/**
	 * set the Device Discoverer
	 * 
	 * 
	 */
	public void setDeviceDiscoverer(IDeviceDiscovery deviceDiscoverer) {
		this.deviceDiscoverer = deviceDiscoverer;
	}

	IDeviceDiscovery deviceDiscoverer;
	IClient connectClient;
	QuorgField[] quorgFields;
	
	
	/**
	 * Create a new Client to connect
	 * 
	 * 
	 */
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

