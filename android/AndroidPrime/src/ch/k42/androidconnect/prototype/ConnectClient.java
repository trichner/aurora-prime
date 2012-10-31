package ch.k42.androidconnect.prototype;

public interface ConnectClient {
	
	public class UnableToConnectException extends Exception{
		private static final long serialVersionUID = 4952945040581474032L;
		public UnableToConnectException(){}	
	};
	
	public Object sendRequest(Object request);
	
	public boolean connect(String ip, int port) throws UnableToConnectException;
	
	public void disconnect();
	
	public boolean isConnected();
}
