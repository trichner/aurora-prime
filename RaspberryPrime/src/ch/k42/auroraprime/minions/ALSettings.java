package ch.k42.auroraprime.minions;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Properties;
import java.util.concurrent.Semaphore;

/**
 * Singleton containing all Information about the device, Settings/Properties
 * @author Thomas
 *
 */
public class ALSettings {
	
	
	
	static private ALSettings _instance = null; // Singleton
	
	static private Properties properties;
	
	private ALSettings(){ // private Constructor
		properties = new Properties();
		Path file = Paths.get(Constants.PROPERTYFILE);
		try {
			properties.load(Files.newInputStream(file));
		} catch (IOException e) {
			Log.e("Property File not found. Looked at: " + Constants.PROPERTYFILE);
		}
		
	}
	
	static private Semaphore instanciate = new Semaphore(1);
	
	static public ALSettings getInstance(){
		if(_instance==null){
			try {
				instanciate.acquire();
				if(_instance==null){
					_instance = new ALSettings();
				}
				instanciate.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return _instance;
	}
	
	public static void store(){
		Path file = Paths.get(Constants.PROPERTYFILE);
		try {
			properties.store(Files.newOutputStream(file), "ALSettings File"); //TODO
		} catch (IOException e) {
			Log.e("Can't store Property File. Looked at: " + Constants.PROPERTYFILE);
		}
		properties = new Properties();
	}
	
}
