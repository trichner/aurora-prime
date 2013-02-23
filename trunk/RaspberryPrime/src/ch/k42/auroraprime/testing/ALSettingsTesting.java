package ch.k42.auroraprime.testing;

import ch.k42.auroraprime.minions.ALSettings;

public class ALSettingsTesting {
	public static void main(String[] args){
		ALSettings settings = ALSettings.getInstance();
		
		//settings.setProperty("version", "0.003");
		//settings.setProperty("author", "Thomas");
		
		System.out.println(settings.getProperty("author"));
		System.out.println(settings.getProperty("version"));
		
		settings.store();
	}
}
