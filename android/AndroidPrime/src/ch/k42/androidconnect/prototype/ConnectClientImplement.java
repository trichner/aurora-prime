package ch.k42.androidconnect.prototype;

import java.io.*;
import java.net.*;

import android.util.Log;

public class ConnectClientImplement implements ConnectClient {
	private Socket socket;
	private boolean connected = false;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	// Connect function, creates new socket with Object input/output streams with 
	// given ip and port, throws exception if connection fails
	public void connect(String ip, int port) throws UnableToConnectException {
		try {
			socket = new Socket();
			SocketAddress sockaddr = new InetSocketAddress(ip, port);
			socket.connect(sockaddr, 3000);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			connected = true;
		} catch (Exception e) {throw new UnableToConnectException();}
	}
	//Disconnect socket
	//
	public void disconnect() {
		try {
			socket.close();
			connected = false;
		} catch (Exception e) {}
	}
	//
	//
	public boolean isConnected() {
		return connected;
	}
	// tries to write given Object into the output stream, reads and returns 
	// the input stream
	public Object sendRequest(Object request) {
		try {
			out.reset();
			out.writeObject(request);
			out.flush();
			Object o = in.readObject();
			return o;
		} catch (Exception e) {
			Log.e("connection","Could not complete sendRequest: " + e.getMessage());
		}
		return null;
	}
}


