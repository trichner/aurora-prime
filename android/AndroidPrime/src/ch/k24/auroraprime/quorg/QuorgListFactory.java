package ch.k24.auroraprime.quorg;

import java.util.List;

/**
 * Factory for the Quorg List Generator
 * 
 * @Author Philipp Bšsch
 */
public class QuorgListFactory {
	
	IQuorgListGenerator generator = new StaticQuorgListGenerator();
	
	public List<Quorg> getQuorgList() {
		return generator.getQuorgList();
		
	}
}


