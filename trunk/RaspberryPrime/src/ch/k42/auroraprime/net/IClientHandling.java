package ch.k42.auroraprime.net;

import java.net.Socket;

public interface IClientHandling {
	/**
	 * Adds a new Socket to be handled.
	 * Meaning: receiving requests and process them
	 * @param sock a Socket, ready to send/receive
	 * @return true if successful, else false
	 */
	public boolean addClient(Socket sock);
	
	/**
	 * Removes an socket by his IP address
	 * @param ip address of the socket
	 * @return true if successful else false
	 */
	public boolean removeClient(String ip);
	
	/**
	 * 
	 */
	public boolean broadcastObject(Object o);
}
