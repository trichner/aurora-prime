package ch.k42.auroraprime.main;

import java.util.concurrent.TimeUnit;

import ch.k42.auroraprime.executors.Executor;
import ch.k42.auroraprime.executors.SendJob;
import ch.k42.auroraprime.executors.Sender;
import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.minions.Utils;
import ch.k42.auroraprime.sim.SimSender;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Log.d("MAIN","Started!");
		
		//SendJob sendJob = new SendJob(new SimSender());
		
		//Executor.getInstance().scheduleAtFixedRate(sendJob, 0, 1000/30, TimeUnit.MILLISECONDS);
		
		//---- Set Up Connection to Raspberry Pi
		
		
		//---- Start Services for Raspi with default effect/load old ones
		
		
		//---- Start Multicast Listener
		
		//
		 //Executor.getInstance().shutdownNow();
		 
		 simulation();
		
	}

    /*
     * Starts a new Simulation with SimSender as the Sender
     */
	private static void simulation(){
		Sender sim = new SimSender();
		
		sim.connect();
        SendJob sjob = new SendJob(sim);
        Executor.getInstance().scheduleAtFixedRate(sjob,0,1000/30,TimeUnit.MILLISECONDS);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {}
        Executor.getInstance().shutdownNow();
		sim.disconnect();
	}

}
