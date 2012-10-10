package ch.k42.auroraprime.net;

import java.io.IOException;
import java.net.*;
import ch.k42.auroraprime.minions.Log;

public class MulticastListener{
	
	private static final String MULTICAST_GROUP="225.0.0.42";
	private static final int MULTICAST_PORT=4455;
	private static final long TIMEOUT_MILLIS = 5000;
	
	private class MulticastClientThread extends Thread{ //TODO
		private MulticastSocket socket;
		private InetAddress group;
		private boolean QUIT = false;
		
		public MulticastClientThread() throws IOException{
			socket = new MulticastSocket(MULTICAST_PORT);
			group = InetAddress.getByName(MULTICAST_GROUP);
			socket.joinGroup(group);
		}
		
		public void shutdown(){
			this.QUIT = true;
		}
		
		public void run(){
			DatagramPacket packet;
			while(!QUIT){
			    byte[] buf = new byte[256];
			    packet = new DatagramPacket(buf, buf.length);
			    try {
					socket.receive(packet);
				} catch (IOException e) {
					Log.e(e.getMessage());
					e.printStackTrace();
				}
		
			    //TODO: SEND CLIENT A MSG BACK AND OPEN SOCKET
			    String received = new String(packet.getData());
			    InetAddress sender = packet.getAddress();
			    int port = packet.getPort();
			    System.out.println("Received Multicast: " + received.substring(0, 10<received.length() ? 10 : (received.length()-1)) + " from " + sender +":"+port);
			    
			}
		
			try {
				socket.leaveGroup(group);
			} catch (IOException e) {
				Log.e(e.getMessage());
				e.printStackTrace();
			}
			socket.close();
		}
	}
	
	MulticastClientThread listener;
	
	public void startListener(){
		try {
			listener = new MulticastClientThread();
			listener.start();
		} catch (IOException e) {
			Log.e(e.getMessage());
			e.printStackTrace();
		}
	}
	public void stopListener(){
		listener.shutdown();
		try {
			listener.join(TIMEOUT_MILLIS);
		} catch (InterruptedException e) {
			Log.e(e.getMessage());
			e.printStackTrace();
		}
	}
}
