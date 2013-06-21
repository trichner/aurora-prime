package ch.k42.auroraprime.multicast;

import ch.k42.auroraprime.minions.ALSettings;
import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.quorgs.StaticQuorgList;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class MulticastListener{
    private static final String TAG = "MulticastListener";
	private final String MULTICAST_GROUP;
	private final int MULTICAST_PORT;
	private final long TIMEOUT_MILLIS = 5000;
	
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
					Log.e(TAG,e.getMessage());
					e.printStackTrace();
					try {
						this.sleep(500); //don't spam errors
					} catch (InterruptedException e1) {	}
					continue;
				}
		
			    //---- Handle received Multicast
			    //TODO: SEND CLIENT A MSG BACK AND OPEN SOCKET
			    
			    String received = new String(packet.getData(),0,packet.getLength());

			    InetAddress sender = packet.getAddress();
			    int port = packet.getPort();
			    Log.v(TAG, String.format("Received Multicast: %10s from %s:%s",received,sender,port));

			    
			    //---- Answer the call
			    try {
			    	
					//---- build package
                    StringBuilder sb = new StringBuilder();
                    sb.append("ALDevice;Port;"); // 0, 1
                    sb.append(ALSettings.getProperty("serverPort")); // 2
				    sb.append(";Version;"); // 3
                    sb.append(ALSettings.getProperty("version")); // 4
                    sb.append(";SupportedQuorgs;"); // 5
                    sb.append(Arrays.toString(StaticQuorgList.supported_quorgs)); //6


					String str = sb.toString();
					buf = str.getBytes();
					
					//---- send back package
					DatagramSocket dsock = new DatagramSocket();
					DatagramPacket pkt = new DatagramPacket(buf, buf.length);

					dsock.connect(sender, port);
					dsock.send(pkt);
					
					dsock.close();
				} catch (SocketException e) {
					Log.e(TAG,"Socket Exception: " + e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					Log.e(TAG,"IO Exception: " + e.getMessage());
					e.printStackTrace();
				}
			    
			}
		
			//---- stop listener, do clean exit
			try {
				socket.leaveGroup(group);
			} catch (IOException e) {
				Log.e(TAG,e.getMessage());
				e.printStackTrace();
			}
			socket.close();
		}
	}
	
	//==== Class Body
	
	MulticastClientThread listener;
	
	public MulticastListener(){
		MULTICAST_GROUP = ALSettings.getProperty("multicastIP");
		MULTICAST_PORT = Integer.parseInt(ALSettings.getProperty("multicastPort"));
	}
	
	public boolean startListener(){
		try {
			listener = new MulticastClientThread(); //create new Listener Thread
			listener.start();						//start the thread
		} catch (IOException e) {					//threadstart was not successful
			Log.e(TAG,e.getMessage());
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
			Log.e(TAG,e.getMessage());
			e.printStackTrace();
			listener.stop(); //simple, we eh, kill the thread
			return false;
		}
		return true;
	}
	
	
}
