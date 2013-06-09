package ch.k42.auroraprime.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.List;
import java.util.Vector;

//Thomas Richner & co
public class AthmosServer implements IServer{
    private int port;
    private boolean active = false;
    private ServerSocket serverSocket;
    private Listener listener;
    private RequestHandlerFactory handlerFactory;
    private List<Socket> socketList = new Vector<Socket>();

    public AthmosServer() {
    }
    
    public List<Socket> getConnections() {
            return socketList;
    }

    public boolean isRunning() {
            return active;
    }

    public void start(int port,RequestHandlerFactory factory) {
            this.port = port;
            this.handlerFactory = factory;
            try {
                    System.out.print("listening on port "+port+"\n");
                    serverSocket = new ServerSocket(port);
                    active = true;
                    listener = new Listener();
                    listener.start();
            } catch (Exception e) {}
    }

    public void stop() {
            try {
                if (active){
                	active = false;
                	serverSocket.close();
                }        
            } catch (IOException e) {
            	System.err.println("Unable to close socket: "+e.getMessage());
            }
    }
    
    private class Listener extends Thread {
            private Socket socket;
            public void run() {
                    while(active) {
                            try {
                                    socket = serverSocket.accept();
                                    socketList.add(socket);
                                    ClientHandler clientHandler = new ClientHandler(socket);
                                    clientHandler.start();
                            } catch (IOException e) {}
                    }
            }
    }
    
    private class ClientHandler extends Thread {
            private Socket socket;
            private RequestHandler handler;
            private ObjectOutputStream out;
            private ObjectInputStream in;
            
            public ClientHandler(Socket socket) {
                    this.socket = socket;
                    this.handler = handlerFactory.getInstance();
                    try {
                            out = new ObjectOutputStream(socket.getOutputStream());
                            in = new ObjectInputStream(socket.getInputStream());
                    } catch (Exception e) {
                    	//TODO cleaner exception handling
                    }
            }
            public void run() {
                    while (active) {
                            try {
                                Object o = in.readObject();			// get Request
                                Object r = handler.getResponse(o); 	// Handle Request
                                
                                out.reset();
                                out.writeObject(r);					// answer Request, send response
                                out.flush(); 						// Important! Or tcp will buffer
                            } catch (IOException e) {
                            	if(socket.isConnected()){
                            		active=false;
                            		System.out.println("Client "+socket.getRemoteSocketAddress() + " disconnected");
                            	}
                            } catch (ClassNotFoundException e) {
								System.err.println(e.getMessage());
							} catch (Exception e){ // cast exception or similar (Runtime)
								System.err.println(e.getMessage());
							}
                    }
                    socketList.remove(socket);
                    try {
                        this.socket.close();
                    } catch (IOException e) {
                            System.err.println("Error closing socket.");
                    }
            }
    }

    public int getPort() {
            return port;
    }
}