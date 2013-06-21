package ch.k42.auroraprime.testing;

import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.multicast.ALDevice;
import ch.k42.auroraprime.multicast.DeviceDiscovery;
import ch.k42.auroraprime.multicast.IDeviceDiscovery;
import ch.k42.auroraprime.multicast.MulticastListener;
import ch.k42.auroraprime.quorgs.Quorg;
import ch.k42.auroraprime.quorgs.StaticQuorgList;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class MulticastTesting{
	/**
	 * 
	 * 
	 * 
	 * @param args
	 */
	// .../bin: java -cp . ch.k42.auroraprime.testing.TestFacility 
	
	public static void main(String[] args) {
		if(args.length==0){
			System.out.println("no args, using client mode");
            client();
			return;
		}
		if(args[0].equals("client")){
			
			client();
				
		}else if(args[0].equals("server")){
			
			server();
			
		}else{
            System.out.println("Illegal Argument");
        }
	}
	
	public static void client(){
		IDeviceDiscovery discovery = new DeviceDiscovery();
		List<ALDevice> list = discovery.getDiscoveredDevices();
        try {
            Class c = Class.forName(StaticQuorgList.quorg_package + list.get(0).getQuorgs()[0]);
            Constructor constructor = c.getConstructor(String[].class);
            Quorg q = (Quorg) constructor.newInstance((Object) new String[0]);
            q.start();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Log.v("mtesting","List of discovered devices:");
		for(ALDevice dev : list){
			Log.v("",dev);
		}
	}
	
	public static void server(){
		MulticastListener listener = new MulticastListener();
		listener.startListener();
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		listener.stopListener();
	}

}
