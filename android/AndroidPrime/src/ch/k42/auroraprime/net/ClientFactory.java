package ch.k42.auroraprime.net;

public class ClientFactory {
	public static IClient getInstance(){
		return new AthmosClient();
	}
}
