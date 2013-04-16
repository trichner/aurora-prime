package ch.k42.auroraprime.net;

public class StringHandler extends RequestHandler {

	@Override
	public Object getResponse(Object request) {
		String s = (String) request;
		System.out.println(s);
		String r = "Welcome to my realm!";
		return r;
	}

}
