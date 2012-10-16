package ch.k42.androidconnect.prototype;


public class ConnectClientTester {

	public static void main(String[] args) {
		try {
			ConnectClientImplement client = new ConnectClientImplement();
			client.connect("127.0.0.1", 10002);
			System.out.print((String)client.sendRequest("Its dangerous to go alone. Take this message!"));
		} catch (Exception e) {System.out.print("YOU FAIL");}
	}
}

