package ch.k42.auroraprime.main;

import java.util.concurrent.TimeUnit;

import ch.k42.auroraprime.core.Quorgs;
import ch.k42.auroraprime.executors.Executor;
import ch.k42.auroraprime.executors.SendJob;
import ch.k42.auroraprime.executors.Sender;
import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.minions.Utils;
import ch.k42.auroraprime.quorgs.RandomQuorg;
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

        Sender sim = new SimSender();   // Choose a sender
		
		sim.connect();                  // Connect sender
        SendJob sjob = new SendJob(sim);// Create SendJob
                                        // Schedule SendJob
        Quorgs.getInstance().putQuorg(1,new RandomQuorg());
        Quorgs.getInstance().putQuorg(2,new RandomQuorg());
        Quorgs.getInstance().putQuorg(3,new RandomQuorg());
        Quorgs.getInstance().putQuorg(4,new RandomQuorg());
        Executor.getInstance().scheduleAtFixedRate(sjob,0,1000/5,TimeUnit.MILLISECONDS);

        //==== Running
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            Log.e("stahp! what are you doing??!");
        }

        //==== stopping

        Executor.getInstance().shutdownNow();
		sim.disconnect();               // Free Connection
        Quorgs.getInstance().removeAll();
	}

}
