package ch.k42.auroraprime.net;

import java.net.InetAddress;

public class ALDevice {
	private InetAddress address;
	private String name;
	private String deviceID;
	private String version;
	
	public ALDevice(InetAddress address, String name, String deviceID, String version){
		this.address = address;
		this.name = name;
		this.deviceID = deviceID;
		this.version = version;
	}
	
	public ALDevice(InetAddress address, String deviceID){
		this(address,"Ambient-Light " + deviceID,deviceID,"0000");
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.address.getCanonicalHostName());
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

	public InetAddress getAddress() {
		return address;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public String getVersion() {
		return version;
	}
	
}
