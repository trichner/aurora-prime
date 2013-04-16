package ch.k42.androidconnect.prototype;

import java.io.*;
import java.net.*;

import android.util.Log;

public class QClientImpl implements QClient {
	private static final String TAG = "Client";
	private Socket socket;
	private boolean connected = false;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public void connect(String ip, int port) throws UnableToConnectException {
		try {
			socket = new Socket();
			SocketAddress sockaddr = new InetSocketAddress(ip, port);
			socket.connect(sockaddr, 3000);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			Log.d(TAG, "connected to " + socket.getRemoteSocketAddress());
			connected = true;
		} catch (Exception e) {throw new UnableToConnectException();}
	}

	public void disconnect() {
		try {
			connected = false;
			Log.d(TAG, "disconnected from " + socket.getRemoteSocketAddress());
			socket.close();
		} catch (Exception e) {
			
		}
	}

	public boolean isConnected() {
		return connected;
	}

	public Object sendRequest(Object request) {
		Log.d(TAG,"Sending request");
		try {
			out.reset();
			out.writeObject(request);
			out.flush(); 				// Important! else TCP waits for further data to send a package
			Object o = in.readObject(); // await a response from the server
			return o;
		} catch (Exception e) {
			Log.e(TAG,"Could not complete sendRequest: " + e.getMessage());
		}
		return null;
	}
}

