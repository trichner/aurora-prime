package ch.k42.auroraprime.testing;

import org.junit.Test;
import ch.k42.auroraprime.minions.ALSettings;

public class ALSettingsTests {

	@Test
	public void test() {
		
		ALSettings settings = ALSettings.getInstance();
		
		settings.setProperty("version", "0.001");
		settings.setProperty("author", "Thomas");
		
		settings.store();
		
	}

}
