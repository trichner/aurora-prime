package ch.k42.ironhide.net;

import java.io.*;
import java.net.*;


public class AthmosClient implements IClient {
	private static final String TAG = "Client";
	private Socket socket;
	private boolean connected = false;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public void connect(InetSocketAddress ip) throws IOException {
			socket = new Socket();
			socket.connect(ip, 3000);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			connected = true;
	}

	public void disconnect() {
		try {
			connected = false;
			socket.close();
		} catch (Exception e) {
			
		}
	}

	public boolean isConnected() {
		return connected;
	}

	public Object sendRequest(Object request) {
		try {
			out.reset();
			out.writeObject(request);
			out.flush(); 				// Important! else TCP waits for further data to send a package
			Object o = in.readObject(); // await a response from the server
			return o;
		} catch (Exception e) {
		}
		return null;
	}
}

