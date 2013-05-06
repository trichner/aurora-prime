package ch.k42.auroraprime.core;

import ch.k42.auroraprime.executors.Executor;
import ch.k42.auroraprime.executors.SendJob;
import ch.k42.auroraprime.executors.Sender;
import ch.k42.auroraprime.executors.SenderFactory;
import ch.k42.auroraprime.minions.ALSettings;
import ch.k42.auroraprime.minions.Log;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 4/30/13
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class MatrixManager {
    private static MatrixManager instance = new MatrixManager();
    public static MatrixManager getInstance(){
        return instance;
    }
    private static final String TAG = "MatrixManager";

    // Singleton
    private boolean isRunning=false;
    private Sender sender;
    private SendJob sjob;

    private MatrixManager(){
        sender = SenderFactory.getInstance();
    }

    public boolean start(){
        boolean ret = sender.connect();
        if(!ret) return false;
        sjob = new SendJob(sender);
        int fps = 5;
        try {
            int tmp = Integer.parseInt(ALSettings.getProperty("FPS"));
            if(tmp<100 && tmp>0)
                fps=tmp;
            else
                Log.w(TAG,"Framerate in properties out of bounds.");
        }catch (Exception e){
            Log.e(TAG,"Can't read Settings File for FPS: " + e.getMessage());
        }
        Executor.getInstance().scheduleAtFixedRate(sjob, 0, 1000 / fps, TimeUnit.MILLISECONDS);
        isRunning=true;
        return true;
    }

    public boolean stop(){
        isRunning=false;
        sender.disconnect();
        Executor.getInstance().remove(sjob);
        return true;
    }

    public Sender getSender() {
        return sender;
    }

    public boolean isRunning(){
        return isRunning;
    }
}
