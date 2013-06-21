package ch.k42.auroraprime.multicast;

import java.net.InetSocketAddress;

public class ALDevice {
	private InetSocketAddress address;
	private String name;
	private String deviceID;
	private String version;
    private String[] quorgs;
	
	public ALDevice(InetSocketAddress address, String name, String deviceID, String version,String[] quorgs){
		this.address = address;
		this.name = name;
		this.deviceID = deviceID;
		this.version = version;
        this.quorgs = quorgs;
	}
	
	public ALDevice(InetSocketAddress address, String deviceID,String[] quorgs){
		this(address,"Ambient-Light " + deviceID,deviceID,"0000",quorgs);
	}

    @Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.address.getHostString());
		sb.append(" : ");
		sb.append(this.name);
		sb.append(" vers.: ");
		sb.append(this.version);
        sb.append("  no. quorgs: ");
        sb.append(quorgs.length);
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

    public String[] getQuorgs() {
        return quorgs;
    }
}
