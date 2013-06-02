package ch.k42.auroraprime.main;

import java.util.List;

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

	private IDeviceDiscovery deviceDiscoverer;
	private IClient connectClient;
	private QuorgField[] quorgFields;
	private int selectedField;
	private List<Quorg> quorgData;
	
	
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
		selectedField=-1;
		for (int i=0;i<4;i++){
			quorgFields[i] = new QuorgField(i+1);
		}
	
	}

	
	public IClient getConnectClient() {
		return connectClient;
	}


	public void setConnectClient(IClient connectClient) {
		this.connectClient = connectClient;
	}


	public QuorgField[] getQuorgFields() {
		return quorgFields;
	}


	public void setQuorgFields(QuorgField[] quorgFields) {
		this.quorgFields = quorgFields;
	}


	public int getSelectedField() {
		return selectedField;
	}


	public void setSelectedField(int selectedField) {
		this.selectedField = selectedField;
	}


	public List<Quorg> getQuorgData() {
		return quorgData;
	}


	public void setQuorgData(List<Quorg> quorgData) {
		this.quorgData = quorgData;
	}
	
	

}

