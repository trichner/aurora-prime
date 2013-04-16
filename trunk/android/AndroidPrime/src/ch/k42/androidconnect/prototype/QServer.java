package ch.k42.androidconnect.prototype;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class QServer{
    private int port;
    private boolean active = false;
    private ServerSocket serverSocket;
    private Listener listener;
    private List<Socket> socketList = new ArrayList<Socket>();

    public QServer() {
    }
    
    public List<Socket> getConnections() {
            return socketList;
    }

    public boolean isRunning() {
            return active;
    }

    public void start(int port) {
            this.port = port;
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
            private ObjectOutputStream out;
            private ObjectInputStream in;
            
            public ClientHandler(Socket socket) {
                    this.socket = socket;
                    try {
                            out = new ObjectOutputStream(socket.getOutputStream());
                            in = new ObjectInputStream(socket.getInputStream());
                    } catch (Exception e) {}
            }
            public void run() {
                    while (active) {
                            try {
                                    Object o = in.readObject();
                                    out.reset();
                                    out.writeObject("Huiiiii! Greetings from: " + socket.getLocalAddress());
                                    out.flush(); // Important!
                            } catch (IOException e) {
                                    System.err.println("IOException: "+e.getMessage());
                                    break;
                            } catch (ClassNotFoundException e) {
                                    System.err.println("ClassNotFound: "+e.getMessage());
                                    break;
                            } catch (Exception e) {
                                    System.err.println("Error Transmitting: "+e.getMessage());
                                    break;
                            }
                    }
                    try {
                            this.socket.close();
                            socketList.remove(socket);
                    } catch (IOException e) {
                            System.err.println("Error closing socket.");
                    }
            }
    }

    public int getPort() {
            return port;
    }
}