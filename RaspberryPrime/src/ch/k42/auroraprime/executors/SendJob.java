package ch.k42.auroraprime.executors;

import java.util.concurrent.Callable;

import ch.k42.auroraprime.minions.Log;

public class SendJob implements Runnable{

	private Sender sender;

	public SendJob(Sender sender){
		this.sender = sender;
		boolean ret;
		ret=sender.connect(); // how to disconnect?
		if(!ret){
			Log.e("Unnable to connect to Device.");
		}
	}

	@Override
	public void run() {

		
	}

}
