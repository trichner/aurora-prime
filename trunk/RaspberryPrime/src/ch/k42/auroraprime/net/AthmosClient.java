package ch.k42.auroraprime.net;

import ch.k42.auroraprime.minions.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;


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
            Log.e(TAG,"Unable to send Request. Cause: " + e.getCause() +" // "+ e.getMessage());
		}
		return null;
	}
}

