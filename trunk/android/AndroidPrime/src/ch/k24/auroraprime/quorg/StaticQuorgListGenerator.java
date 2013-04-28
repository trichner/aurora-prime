package ch.k24.auroraprime.quorg;

import java.util.ArrayList;
import java.util.List;

import ch.k42.auroraprime.R;

/**
 * Implementation of a Quorg List Generator
 * that returns a static programmed list
 * of avaliable quorgs
 * 
 * @Author Philipp Bšsch
 */
public class StaticQuorgListGenerator implements IQuorgListGenerator {

	private List<Quorg> list;

	public List<Quorg> getQuorgList() {
		// TODO Auto-generated method stub
		list = new ArrayList<Quorg>();
		
		//Red LED Quorg definition
		
		Quorg redLedQuorg = new Quorg(R.drawable.redlight,001, "Red LED Field", false);
		list.add(redLedQuorg);
		
		//Green LED Quorg definition
		
		Quorg greenLedQuorg = new Quorg(R.drawable.greenlight,002, "Green LED Field", false);
		list.add(greenLedQuorg);
				
		//Blue LED Quorg definition
				
		Quorg blueLedQuorg = new Quorg(R.drawable.bluelight,003, "Blue LED Field", false);
		list.add(blueLedQuorg);
		
		//Clock Quorg definition
		
		Quorg clockQuorg = new Quorg(R.drawable.clock,004, "Clock", true);
		list.add(clockQuorg);

		return list;
	}
		
}
