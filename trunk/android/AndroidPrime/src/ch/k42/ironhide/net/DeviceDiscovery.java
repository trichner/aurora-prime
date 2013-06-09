package ch.k42.ironhide.net;

import android.util.Log;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class DeviceDiscovery implements IDeviceDiscovery {

    private static final int RCV_TIMEOUT=300;
    private static final String TAG = "DEVICE DISCOVERY";
    private final static int TIMEOUT = 5000;

    private static String MULTICAST_GROUP="225.0.0.42";
    private static int MULTICAST_PORT=4455;

    private class DiscoveryThread extends Thread {

        private DatagramSocket socket;

        private List<ALDevice> list;
        private String sender;

        public DiscoveryThread(String sender,List<ALDevice> list) throws SocketException{
            this.sender = sender;
            this.list = list;
            socket = new DatagramSocket(); //SOCKET_PORT); // choose random port?
        }

        public void run() {
            try{
                String dString = sender;

                byte[] buf = dString.getBytes();

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
                        String str = new String(packet.getData(),0,packet.getLength());

                        Log.v(TAG, "Got ACK       : " + str);
                        Log.v(TAG,"Packet length : " + packet.getLength());
                        Log.v(TAG,"Message from server: "+str);
                        String strs[] = str.split(";");
                        InetSocketAddress address = new InetSocketAddress(packet.getAddress(),Integer.parseInt(strs[2]));
                        String version = strs[4];
                        String[] quorgs = strs[6].replace("[", "").replace("]", "").split(", "); //parse supported quorgs

                        ALDevice device = new ALDevice(address, strs[0],"noID",version,quorgs);
                        list.add(device);
                        Log.d(TAG,"added device: " +device);
                    }catch(SocketTimeoutException e){
                        //Log.d(TAG,"Socket Timeout: waiting for next package");
                    }catch (Exception e){
                        Log.w(TAG,"Exception: " + e.getMessage());
                    }
                }

            }catch(IOException e){
                Log.e(TAG,e.getMessage());
            }
            socket.close();
        }
    }



    //==== Actual class

    public DeviceDiscovery(){}

    public DeviceDiscovery(String ip,int port){
        MULTICAST_GROUP = ip;
        MULTICAST_PORT = port;
    }

	@Override
	public List<ALDevice> getDiscoveredDevices() {
		DiscoveryThread test;
		List<ALDevice> list = new ArrayList<ALDevice>();
		try {
            String devString = "Anonymous Android Client";
			test = new DiscoveryThread(devString, list);
			test.start();
			
			test.join(TIMEOUT);
			
			
			//---- print discovered devices
			Log.v(TAG,"Found "+list.size()+" devices:");
			for(ALDevice dev : list){
				Log.d(TAG,dev.toString());
			}
		} catch (SocketException e) {
			Log.e(TAG,e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			Log.e(TAG,e.getMessage());
			e.printStackTrace();
		}
		return list;
	}


}
