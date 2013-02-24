package ch.k42.auroraprime.main;

import ch.k42.auroraprime.sim.FrameSimulation;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Started!");
		FrameSimulation sim = new FrameSimulation();
		sim.start();
		System.out.println("blub");
		try {
			sim.join();
		} catch (InterruptedException e) {}
		
		//---- Set Up Connection to Raspberry Pi
		
		
		//---- Start Services for Raspi with default effect/load old ones
		
		
		//---- Start Multicast Listener
		
		//
		
		
	}

}
