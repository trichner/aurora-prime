package ch.k42.ironhide.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public interface IClient {
	public class UnableToConnectException extends Exception{
		private static final long serialVersionUID = 4952945040581474032L;
		public UnableToConnectException(){}	
	};
	/**
	 * 
	 * @param request
	 * @return
	 */
	public Object sendRequest(Object request); //blocking
	/**
	 * Opens a socket to the specified ip and port
	 * @param ip servers address (IP)
	 * @param port servers port
	 * @throws java.io.IOException if unable to connect
	 */
	public void connect(InetSocketAddress ip) throws IOException;
	/**
	 * Closes the socket
	 */
	public void disconnect();
	/**
	 * Checks if the socket is ready for transmissions
	 * @return true if socket open and ready, else false
	 */
	public boolean isConnected();
}
