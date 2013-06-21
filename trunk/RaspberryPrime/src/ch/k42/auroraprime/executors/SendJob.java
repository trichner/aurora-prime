package ch.k42.auroraprime.executors;

import ch.k42.auroraprime.core.IMatrix;
import ch.k42.auroraprime.core.MatrixManager;
import ch.k42.auroraprime.core.QuorgManager;
import ch.k42.auroraprime.minions.Constants;
import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.quorgs.ColorQuorg;
import ch.k42.auroraprime.quorgs.Quorg;

import java.util.concurrent.Semaphore;

public class SendJob implements Runnable{
    private static final String TAG="SendJob";
    private static final int MAX_QUEUE = 10;
    private static Semaphore execute = new Semaphore(1) ;
	private Sender sender;

    /**
     * Instanciate a new SendJob
     * The Sender must already be connected and
     * must manually be disconnected when SendJob got stopped.
     * @param sender implementation of a Sender
     */
	public SendJob(Sender sender){
        if(!sender.isConnected()){
            Log.e(TAG,"Sender not connected, trying to connect now.");
            sender.connect();    //try to connect anyway
            if(!sender.isConnected()) Log.e(TAG,"No Device Connected, unable to connect.");
        }
		this.sender = sender;
	}

	@Override
	public void run() {
        //---- already blocked by previous call?
        Log.dd(TAG,execute.availablePermits());
        if(execute.getQueueLength()>MAX_QUEUE){
            Log.e(TAG,"SendJob seems blocked, more than "+MAX_QUEUE + " threads waiting.");
            Log.w(TAG,"purging all Quorgs");
            QuorgManager.getInstance().removeAll();
            if(Constants.DEBUG){
                Log.w(TAG,"setting a ColorQuorg ");
                IMatrix matrix = MatrixManager.getInstance().getMatrices().values().iterator().next();
                int address = matrix.getAddress();
                String[] settings = {"0xFF0000"};
                QuorgManager.getInstance().putQuorg(address,new ColorQuorg(settings));
            }
        }
        //---- aquire resources
        try {
            execute.acquire();

            if(!sender.isConnected()){
                Log.w(TAG,"Sender not connected, trying to connect now.");
                sender.connect();    //try to connect anyway
                if(sender.isConnected()){
                    Log.e(TAG,"No Device Connected, unable to connect.");
                    return;
                }
            }

            //---- sending stuff    TODO

            //MatrixManager -> discover Matrices

            //---- Send the Frame from every Quorg to the mapped Matrix
            for(Quorg q : QuorgManager.getInstance().getAllQuorgs().values()){
                sender.sendFrame(q.getMatrixAddress(),q.getFrame());
            }

            execute.release();  // we no longer need the lock, next SendJob may proceed

        } catch (InterruptedException e) {
            Log.e(TAG,"Can't aquire lock. Got interrupted. Message: " + e.getMessage());
        }
        //---- are we ready to go?

	}

}
