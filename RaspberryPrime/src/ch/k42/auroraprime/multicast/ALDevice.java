package ch.k42.auroraprime.multicast;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class ALDevice {
	private InetSocketAddress address;
	private String name;
	private String deviceID;
	private String version;
	
	public ALDevice(InetSocketAddress address, String name, String deviceID, String version){
		this.address = address;
		this.name = name;
		this.deviceID = deviceID;
		this.version = version;
	}
	
	public ALDevice(InetSocketAddress address, String deviceID){
		this(address,"Ambient-Light " + deviceID,deviceID,"0000");
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.address.getHostString());
		sb.append(" : ");
		sb.append(this.name);
		sb.append(" vers.: ");
		sb.append(this.version);
		return sb.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InetSocketAddress getAddress() {
		return address;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public String getVersion() {
		return version;
	}
	
}
