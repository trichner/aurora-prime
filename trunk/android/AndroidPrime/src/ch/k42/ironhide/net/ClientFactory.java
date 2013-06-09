package ch.k42.ironhide.net;

public class ClientFactory {
	public static IClient getInstance(){
		return new AthmosClient();
	}
}
