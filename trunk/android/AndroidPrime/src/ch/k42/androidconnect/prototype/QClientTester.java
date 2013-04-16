package ch.k42.androidconnect.prototype;


public class QClientTester {

	private static void panicHelp(){
		System.out.println("Usage: java ... command ");
		System.out.println("Commands:");
		System.out.println("server <port>");
		System.out.println("client <ip> <dst-port>");
		System.exit(0);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length<1){
			System.out.println("command not found/too few arguments");
			panicHelp();
		}
		
		if(args[0].equals("server")){
			if(args.length<2){
				System.out.println("Too few arguments");
				panicHelp();
			}
			// Start Server
			 QServer server = new QServer();
			 server.start(Integer.parseInt(args[1]));
			 
			
		}else if(args[0].equals("client")){
			if(args.length<3){
				System.out.println("Too few arguments");
				panicHelp();
			}
			//open connection, send one request
			try {
				QClientImpl client = new QClientImpl();
				client.connect(args[1], Integer.parseInt(args[2]));
				System.out.print((String) client.sendRequest("lollll"));
			} catch (Exception e) {
				System.out.print("Q: Something failed");
			}
		}else{
			System.out.println("unknown command: " + args[0]);
		}
		
	}
}
