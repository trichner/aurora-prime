package ch.k42.ironhide.quorg;

import ch.k42.auroraprime.dto.QuorgSettings.QUORG;

/**
 * A Class that holds all information
 * about a quorg the application needs
 * 
 * 
 * @author Bert
 *
 */

public class Quorg {

		private int image;
		private QUORG quorgID;
		private String name;
		private boolean hasSettings;
		
		// constructors
		
		public Quorg(int image,QUORG quorgID, String name, boolean hasSettings) {
			
			super();
			this.image = image;
			this.quorgID = quorgID;
			this.name = name;
			this.hasSettings = hasSettings;
			
		}
		
		public Quorg() {
			super();
		}
		
		//getters and setters 
		public int getImage() {
			return image;
		}
		public void setImage(int image) {
			this.image = image;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public boolean hasSettings() {
			return hasSettings;
		}
		public void setHasSettings(boolean hasSettings) {
			this.hasSettings = hasSettings;
		}
		public QUORG getQuorgID() {
			return quorgID;
		}
		public void setQuorgID(QUORG quorgID) {
			this.quorgID = quorgID;
		}

		
		
}
