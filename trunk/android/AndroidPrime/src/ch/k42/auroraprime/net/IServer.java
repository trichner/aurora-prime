package ch.k42.auroraprime.net;

import java.util.List;
import java.net.*;

/**
 * Listens on a Port for incoming connection, accepts them and
 * opens a socket. If the server looses the connection to a client,
 * it closes the socket.
 * 
 * @author Thomas Richner
 *
 */
public interface IServer {
		/**
		 * Starts listening for incoming connections on
		 * a specified port
		 * @param port to listen on
		 * @param rhf a Factory producing handlers that answer client requests
		 */
        public void start(int port,RequestHandlerFactory rhf);
        /**
         * Stops the servers listening 
         */
        public void stop();
        /**
         * Checks if the server is listening to incoming connections
         * @return true if listening else false
         */
        public boolean isRunning();
        /**
         * Returns a list of all active and open connections to this server
         * @return list of open and active sockets
         */
        public List<Socket> getConnections();
        /**
         * The port the server is listening on
         * @return port of this server
         */
        public int getPort();
}