package ch.k42.auroraprime.core;

import ch.k42.auroraprime.minions.ALSettings;
import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.multicast.MulticastListener;
import ch.k42.auroraprime.net.IServer;
import ch.k42.auroraprime.net.IServerFactory;
import ch.k42.auroraprime.net.RequestHandlerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 28.05.13
 * Time: 21:42
 * To change this template use File | Settings | File Templates.
 */
public class NetManager {
    private static NetManager instance = new NetManager();
    public static NetManager getInstance(){
        return instance;
    }
    private static final String TAG = "MatrixManager";

    // Singleton
    private static final int DEFAULTPORT = 1337;
    private boolean isRunning=false;
    private MulticastListener listener;
    private IServer server;

    private NetManager(){
        listener = new MulticastListener();
        server = IServerFactory.getInstance();
    }

    public boolean start(RequestHandlerFactory requestHandlerFactory){

        int port = DEFAULTPORT;
        try {
            port = Integer.parseInt(ALSettings.getProperty("serverPort"));
        } catch (NumberFormatException e) {
            Log.w(TAG,"Couldn't parse serverPort in properties file. Using port " + port + " instead.");
        }

        server.start(port,requestHandlerFactory);
        listener.startListener();
        isRunning=true;
        return true;
    }

    public boolean stop(){
        isRunning=false;
        listener.stopListener();
        server.stop();
        return true;
    }

    public boolean isRunning(){
        return isRunning;
    }
}
