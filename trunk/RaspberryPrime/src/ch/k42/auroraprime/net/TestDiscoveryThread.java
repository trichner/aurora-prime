package ch.k42.auroraprime.net;
import java.io.*;
import java.net.*;
import java.util.*;

import ch.k42.auroraprime.minions.Log;
 
public class TestDiscoveryThread extends Thread {
	private static final int MULTICAST_PORT=4455;
	private static final int SOCKET_PORT=4445;
	private static final String MULTICAST_GROUP="225.0.0.42";
    private long FIVE_SECONDS = 5000;
    private DatagramSocket socket;
    private boolean QUIT = false;
    
    public TestDiscoveryThread() throws SocketException{
        socket = new DatagramSocket(SOCKET_PORT);
    }
 
    public void run() {
        while (!QUIT) {
        	try{
	            byte[] buf = new byte[256];
	 
	            String dString = "Hello World!";
	
	            buf = dString.getBytes();
	 
	            // send it
	            InetAddress group;
				group = InetAddress.getByName(MULTICAST_GROUP);
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, MULTICAST_PORT);
                socket.send(packet);
	 
	            // sleep for a while
                
                socket.receive(packet);
                
                System.out.println("Got answer: " + packet.getData().toString());
                
		        try {
		            sleep((long)(Math.random() * FIVE_SECONDS+2000));
		        } catch (InterruptedException e) {}
        	}catch(IOException e){
	        	Log.e(e.getMessage());
	        }
        }
        socket.close();
    }
}


