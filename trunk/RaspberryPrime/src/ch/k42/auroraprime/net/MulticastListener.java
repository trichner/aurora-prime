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
			
			//---- start listening
			while(!QUIT){
			    byte[] buf = new byte[256];
			    packet = new DatagramPacket(buf, buf.length);
			    
			    //---- receive a package
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
		
			    //---- Handle received Multicast
			    //TODO: SEND CLIENT A MSG BACK AND OPEN SOCKET
			    String received = new String(packet.getData());
			    InetAddress sender = packet.getAddress();
			    int port = packet.getPort();
			    System.out.println("Received Multicast: " + received.substring(0, 10<received.length() ? 20 : (received.length()-1)) + " from " + sender +":"+port);
			    
			    
			    
			    //---- Answer the call
			    try {
			    	
					//---- build package
					String str = "testingDevice42";
					
					buf = str.getBytes();
					
					//---- send back package
					DatagramSocket dsock = new DatagramSocket(port);
					DatagramPacket pkt = new DatagramPacket(buf, buf.length);
					dsock.connect(sender, port);
					dsock.send(pkt);
					
					dsock.close();
				} catch (SocketException e) {
					Log.e("Socket Exception: " + e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					Log.e("IO Exception: " + e.getMessage());
					e.printStackTrace();
				}
			    
			}
		
			//---- stop listener, do clean exit
			try {
				socket.leaveGroup(group);
			} catch (IOException e) {
				Log.e(e.getMessage());
				e.printStackTrace();
			}
			socket.close();
		}
	}
	
	//==== Class Body
	
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
			listener.stop(); //simple, we eh, kill the thread
			return false;
		}
		return true;
	}
	
	
}
