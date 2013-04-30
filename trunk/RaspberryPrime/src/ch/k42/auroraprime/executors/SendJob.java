package ch.k42.auroraprime.executors;

import ch.k42.auroraprime.core.QuorgManager;
import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.quorgs.Frame;
import ch.k42.auroraprime.quorgs.IFrame8x8;

public class SendJob implements Runnable{
    private static final String TAG="SendJob";
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
            if(sender.isConnected()) Log.e(TAG,"No Device Connected, unable to connect.");
        }
		this.sender = sender;
	}

	@Override
	public void run() {
        //---- are we ready to go?
        if(!sender.isConnected()){
            Log.e(TAG,"Sender not connected, trying to connect now.");
            sender.connect();    //try to connect anyway
            if(sender.isConnected()){
                Log.e(TAG,"No Device Connected, unable to connect.");
                return;
            }
        }

        //---- sending stuff    TODO

        //MatrixManager -> discover Matrices




        Frame frame;// = // effect get frame;
        //for() //for all quorgs, get a new frame
        //sender.sendFrame(frame);
        IFrame8x8 f1 = QuorgManager.getInstance().getQuorg(1).getFrame();
        IFrame8x8 f2 = QuorgManager.getInstance().getQuorg(2).getFrame();
        IFrame8x8 f3 = QuorgManager.getInstance().getQuorg(3).getFrame();
        IFrame8x8 f4 = QuorgManager.getInstance().getQuorg(4).getFrame();
        Log.d("SendJob",f1.getColor(0,0));



        sender.sendFrame(1, f1);
        sender.sendFrame(2, f2);
        sender.sendFrame(3, f3);
        sender.sendFrame(4, f4);
	}

}
