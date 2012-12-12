package ch.k42.auroraprime.net;
import java.io.*;
import java.net.*;
import java.util.*;

import ch.k42.auroraprime.minions.Log;
 
public class DiscoveryThread extends Thread {
	private static final int MULTICAST_PORT=4455;
	private static final int SOCKET_PORT=4445;
	private static final int RCV_TIMEOUT=100;
	private static final String MULTICAST_GROUP="225.0.0.42";
	
    private DatagramSocket socket;
    
    private List<ALDevice> list;
    private String sender;
    
    public DiscoveryThread(String sender,List<ALDevice> list) throws SocketException{
        this.sender = sender;
        this.list = list;
    	socket = new DatagramSocket(SOCKET_PORT);
    }
 
    public void run() {
    	try{
            byte[] buf = new byte[256];
 
            String dString = sender;

            buf = dString.getBytes();
 
            // send it
            InetAddress group;
			group = InetAddress.getByName(MULTICAST_GROUP);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, group, MULTICAST_PORT);
            socket.send(packet);
            
            //--- wait for max 10 devices
            byte[] rbuf;
            socket.setSoTimeout(RCV_TIMEOUT);
            for(int i=10; i>0; i--){
            	try{
            		rbuf = new byte[256];
    			    packet = new DatagramPacket(rbuf, rbuf.length);
    			    
	            	socket.receive(packet);
	            	String str = new String(rbuf,0,packet.getLength());
	            	Log.v("Got Device Discovery answer: " + str);
	            	ALDevice device = new ALDevice(packet.getAddress(), str);
	            	list.add(device);
            	}catch(SocketTimeoutException e){
            		Log.d("Socket Timeout: waiting for next package");
            	}
            }
            
    	}catch(IOException e){
        	Log.e(e.getMessage());
        }
        socket.close();
    }
}


