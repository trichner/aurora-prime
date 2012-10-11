package ch.k42.auroraprime.testing;

import java.net.SocketException;

import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.net.MulticastListener;
import ch.k42.auroraprime.net.TestDiscoveryThread;

public class TestFacility{
	/**
	 * 
	 * 
	 * 
	 * @param args
	 */
	// .../bin: java -cp . ch.k42.auroraprime.testing.TestFacility 
	
	public static void main(String[] args) {
		if(args.length==0){
			System.out.println("no args");
			return;
		}
		if(args[0].equals("client")){
			TestDiscoveryThread test;
			try {
				test = new TestDiscoveryThread();
				test.start();
				test.join();
			} catch (SocketException e) {
				Log.e(e.getMessage());
				e.printStackTrace();
			} catch (InterruptedException e) {
				Log.e(e.getMessage());
				e.printStackTrace();
			}
		}else if(args[0].equals("server")){
			MulticastListener listener = new MulticastListener();
			listener.startListener();
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			listener.stopListener();
		}
	}

}
