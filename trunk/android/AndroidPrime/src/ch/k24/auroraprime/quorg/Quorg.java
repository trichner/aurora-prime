package ch.k24.auroraprime.quorg;

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
		private int quorgID;
		private String name;
		private boolean hasSettings;
		
		// constructors
		
		public Quorg(int image,int quorgID, String name, boolean hasSettings) {
			
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
		public int getQuorgID() {
			return quorgID;
		}
		public void setQuorgID(int quorgID) {
			this.quorgID = quorgID;
		}

		
		
}
