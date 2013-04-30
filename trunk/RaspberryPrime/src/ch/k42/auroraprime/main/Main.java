package ch.k42.auroraprime.main;

import java.util.concurrent.TimeUnit;

import ch.k42.auroraprime.core.QuorgManager;
import ch.k42.auroraprime.executors.Executor;
import ch.k42.auroraprime.executors.SendJob;
import ch.k42.auroraprime.executors.Sender;
import ch.k42.auroraprime.i2c.I2CSender;
import ch.k42.auroraprime.minions.Log;
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
        QuorgManager.getInstance().putQuorg(1,new RandomQuorg());
        QuorgManager.getInstance().putQuorg(2,new RandomQuorg());
        QuorgManager.getInstance().putQuorg(3,new RandomQuorg());
        QuorgManager.getInstance().putQuorg(4,new RandomQuorg());
        Executor.getInstance().scheduleAtFixedRate(sjob,0,1000/5,TimeUnit.MILLISECONDS);

        //==== Running
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            Log.e("MAIN","stahp! what are you doing??!");
        }

        //==== stopping

        Executor.getInstance().shutdownNow();
		sim.disconnect();               // Free Connection
        QuorgManager.getInstance().removeAll();
	}

    private static void i2c(){

        Sender i2c = new I2CSender();   // Choose a sender

        i2c.connect();  // Connect sender
        SendJob sjob = new SendJob(i2c);// Create SendJob
        // Schedule SendJob
        QuorgManager.getInstance().putQuorg(1,new RandomQuorg());
        QuorgManager.getInstance().putQuorg(2,new RandomQuorg());
        QuorgManager.getInstance().putQuorg(3,new RandomQuorg());
        QuorgManager.getInstance().putQuorg(4,new RandomQuorg());
        Executor.getInstance().scheduleAtFixedRate(sjob,0,1000/5,TimeUnit.MILLISECONDS);

        //==== Running
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            Log.e("MAIN","stahp! what are you doing??!");
        }

        //==== stopping

        Executor.getInstance().shutdownNow();
        i2c.disconnect();               // Free Connection
        QuorgManager.getInstance().removeAll();
    }

}
