package ch.k24.auroraprime.quorg;

/**
 * 
 */
/**
 * @author Bert
 *
 */



public class Quorg {

		private int image;
		private String name;
		private boolean hasSettings;
		
		// constructors
		
		public Quorg(int image, String name, boolean hasSettings) {
			
			super();
			this.image = image;
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
		public boolean isHasSettings() {
			return hasSettings;
		}
		public void setHasSettings(boolean hasSettings) {
			this.hasSettings = hasSettings;
		}

		
		
}
