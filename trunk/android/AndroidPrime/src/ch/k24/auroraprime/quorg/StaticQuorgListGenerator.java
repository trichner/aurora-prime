package ch.k24.auroraprime.quorg;

import java.util.ArrayList;
import java.util.List;

import ch.k42.auroraprime.R;

public class StaticQuorgListGenerator implements IQuorgListGenerator {

	private List<Quorg> list;

	public List getQuorgList() {
		// TODO Auto-generated method stub
		list = new ArrayList<Quorg>();
		
		//Red LED Quorg definition
		
		Quorg redLedQuorg = new Quorg(R.drawable.redlight, "Red LED Field", false);
		list.add(redLedQuorg);
		
		//Green LED Quorg definition
		
		Quorg greenLedQuorg = new Quorg(R.drawable.greenlight, "Green LED Field", false);
		list.add(greenLedQuorg);
				
		//Blue LED Quorg definition
				
		Quorg blueLedQuorg = new Quorg(R.drawable.bluelight, "Blue LED Field", false);
		list.add(blueLedQuorg);
		
		//Clock Quorg definition
		
		Quorg clockQuorg = new Quorg(R.drawable.clock, "Clock", false);
		list.add(clockQuorg);

		return list;
	}
		
}
