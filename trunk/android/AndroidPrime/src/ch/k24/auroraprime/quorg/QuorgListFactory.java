package ch.k24.auroraprime.quorg;

import java.util.List;

public class QuorgListFactory {
	
	IQuorgListGenerator generator = new StaticQuorgListGenerator();
	
	public List<Quorg> getQuorgList() {
		return generator.getQuorgList();
		
	}
}


