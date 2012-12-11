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
					try {
						this.sleep(500); //don't spam errors
					} catch (InterruptedException e1) {	}
					continue;
				}
		
			    //TODO: SEND CLIENT A MSG BACK AND OPEN SOCKET
			    String received = new String(packet.getData());
			    InetAddress sender = packet.getAddress();
			    int port = packet.getPort();
			    System.out.println("Received Multicast: " + received.substring(0, 10<received.length() ? 20 : (received.length()-1)) + " from " + sender +":"+port);
			    
			    //Answer the call
			    try {
			    	
					DatagramSocket dsock = new DatagramSocket(port);
					String str = "Received Multicast from " + sender.toString() + " It works.";
					buf = str.getBytes();
					DatagramPacket pkt = new DatagramPacket(buf, buf.length);
					dsock.connect(sender, port);
					dsock.send(pkt);
					
					dsock.close();
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
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
	
	//----------------------------------- Class Body
	
	MulticastClientThread listener;
	
	public boolean startListener(){
		try {
			listener = new MulticastClientThread(); //create new Listener Thread
			listener.start();						//start the thread
		} catch (IOException e) {					//threadstart was not successful
			Log.e(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean stopListener(){
		listener.shutdown(); // shutdown the listener
		try {
			listener.join(TIMEOUT_MILLIS); // wait for the thread
		} catch (InterruptedException e) {
			Log.e(e.getMessage());
			e.printStackTrace();
			listener.stop(); //kill the thread
			return false;
		}
		return true;
	}
}