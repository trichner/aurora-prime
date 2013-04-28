package ch.k24.auroraprime.quorg;

import java.util.List;

/**
 * Factory for the Quorg List Generator
 * 
 * @Author Philipp B�sch
 */
public class QuorgListFactory {
	
	IQuorgListGenerator generator = new StaticQuorgListGenerator();
	
	public List<Quorg> getQuorgList() {
		return generator.getQuorgList();
		
	}
}


