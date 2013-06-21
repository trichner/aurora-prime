package ch.k42.auroraprime.net;


import ch.k42.auroraprime.minions.ALSettings;
import ch.k42.auroraprime.multicast.MulticastListener;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 4/19/13
 * Time: 1:52 PM
 *
 */
public class MulticastTestServer {
    public static void main(String args[]){

        MulticastListener listener = new MulticastListener();
        // Start Server
        IServer server = new AthmosServer();
        int port = Integer.parseInt(ALSettings.getProperty("serverPort"));
        server.start(port,new RequestHandlerFactory() {
            @Override
            public RequestHandler getInstance() {
                return new StringHandler();
            }
        });

        listener.startListener();

        //trap thread
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listener.stopListener();

    }
}

