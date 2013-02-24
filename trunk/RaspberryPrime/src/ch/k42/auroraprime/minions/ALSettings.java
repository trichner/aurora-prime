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

	static private ALSettings _instance = new ALSettings();
	
	private Properties properties;
	
	private ALSettings(){ // private Constructor
		properties = new Properties();
		Path file = Paths.get(Constants.PROPERTYFILE);
		try {
			properties.load(Files.newInputStream(file));
		} catch (IOException e) {
			Log.e("Property File not found. Looked at: " + Constants.PROPERTYFILE);
		}
		
	}
	
	//static private Semaphore instanciate = new Semaphore(1);
	
	static public ALSettings getInstance(){
//		if(_instance==null){
//			try {
//				instanciate.acquire();
//				if(_instance==null){
//					_instance = new ALSettings();
//				}
//				instanciate.release();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		return _instance;
	}
	
	//---- Methods
	/**
	 * Saves all Settings to a Propertyfile, specified
	 * by 'Constants.PROPERTYFILE'
	 */
	public static void store(){
		Path file = Paths.get(Constants.PROPERTYFILE);
		try {
			getInstance().properties.store(Files.newOutputStream(file), "ALSettings File");
		} catch (IOException e) {
			Log.e("Can't store Property File. Looked at: " + Constants.PROPERTYFILE);
		}
	}
	
	public static void setProperty(String key,String value){
		getInstance().properties.setProperty(key, value);
	}
	public static String getProperty(String key){
		return getInstance().properties.getProperty(key);
	}
}
