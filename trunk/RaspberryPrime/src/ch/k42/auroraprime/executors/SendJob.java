package ch.k42.auroraprime.executors;

import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.quorgs.Frame;

public class SendJob implements Runnable{

	private Sender sender;

    /**
     * Instanciate a new SendJob
     * The Sender must already be connected and
     * must manually be disconnected when SendJob got stopped.
     * @param sender implementation of a Sender
     */
	public SendJob(Sender sender){
        if(sender.isConnected()){
            Log.e("Sender not connected, trying to connect now.");
            sender.connect();    //try to connect anyway
            if(sender.isConnected()) Log.e("No Device Connected, unable to connect.");
        }
		this.sender = sender;
	}

	@Override
	public void run() {
        //---- are we ready to go?
        if(sender.isConnected()){
            Log.e("Sender not connected, trying to connect now.");
            sender.connect();    //try to connect anyway
            if(sender.isConnected()){
                Log.e("No Device Connected, unable to connect.");
                return;
            }
        }

        //---- sending stuff    TODO
        Frame frame;// = // effect get frame;
        //for() //for all quorgs, get a new frame
        //sender.sendFrame(frame);
		
	}

}
