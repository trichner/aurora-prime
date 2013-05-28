package ch.k42.auroraprime.main;

import ch.k42.auroraprime.core.MatrixManager;
import ch.k42.auroraprime.core.QuorgManager;
import ch.k42.auroraprime.executors.Executor;
import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.quorgs.MailQuorg;
import ch.k42.auroraprime.quorgs.RandomQuorg;

public class Main {



	/**
     *
     * Notes:
     * - the Transmission Layer can be choosen by editing the 'SenderFactory'
     *
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

	private static void simulation(){
        Runtime.getRuntime().addShutdownHook( new Thread() {
            @Override
            public void run() {
                //this.notifyAll();
            }
        } );

        QuorgManager.getInstance().putQuorg(1,new RandomQuorg());
        QuorgManager.getInstance().putQuorg(2,new RandomQuorg());
        QuorgManager.getInstance().putQuorg(3,new RandomQuorg());
        QuorgManager.getInstance().putQuorg(4,new RandomQuorg());
        MatrixManager.getInstance().start();

        //==== Running
        try {
            Thread.sleep(1500000000);
        } catch (InterruptedException e) {
            Log.e("MAIN","stahp! what are you doing??!");
        }

        //==== stopping
        MatrixManager.getInstance().stop();     // disconnect matrices
        Executor.getInstance().shutdownNow();   // just in case
        QuorgManager.getInstance().removeAll(); // stop all quorgs
	}

}
