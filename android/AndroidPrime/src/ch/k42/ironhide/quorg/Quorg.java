package ch.k42.ironhide.quorg;

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
		private Class quorg;
		private String name;
        private String[] settings;
		
		// constructors
		
		public Quorg(int image, String name,Class quorg ,String[] settings) {
            this.settings = settings;
            this.quorg = quorg;
			this.image = image;
			this.name = name;
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
			return settings.length!=0;
		}
		public Class getQuorg() {
			return quorg;
		}
		public void setQuorg(Class quorg) {
			this.quorg = quorg;
		}

    public String[] getSettings() {
        return settings;
    }

    public void setSettings(String[] settings) {
        this.settings = settings;
    }
}
