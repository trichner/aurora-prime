package ch.k42.auroraprime.multicast;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import ch.k42.auroraprime.minions.ALSettings;
import ch.k42.auroraprime.minions.Log;

public class DeviceDiscovery implements IDeviceDiscovery {
    private static final int MULTICAST_PORT=4455;
    private static final int SOCKET_PORT=4445;
    private static final int RCV_TIMEOUT=300;
    private static final String MULTICAST_GROUP="225.0.0.42";
    private static final String TAG = "DEVICE DISCOVERY";
    private final static int TIMEOUT = 5000;

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

                        Log.v(TAG,"Got ACK       : " + str);
                        Log.v(TAG,"Packet length : " + packet.getLength());
                        Log.v(TAG,"Message from server: "+str);
                        String strs[] = str.split(";");
                        InetSocketAddress address = new InetSocketAddress(packet.getAddress(),Integer.parseInt(strs[2]));
                        ALDevice device = new ALDevice(address, str);

                        list.add(device);
                    }catch(SocketTimeoutException e){
                        Log.d(TAG,"Socket Timeout: waiting for next package");
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
	@Override
	public List<ALDevice> getDiscoveredDevices() {
		DiscoveryThread test;
		List<ALDevice> list = new ArrayList<ALDevice>();
		try {
			test = new DiscoveryThread(ALSettings.getProperty("deviceString"), list);
			test.start();
			
			test.join(TIMEOUT);
			
			
			//---- print discovered devices
			Log.v(TAG,"Found "+list.size()+" devices:");
			for(ALDevice dev : list){
				Log.d(TAG,dev);
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
