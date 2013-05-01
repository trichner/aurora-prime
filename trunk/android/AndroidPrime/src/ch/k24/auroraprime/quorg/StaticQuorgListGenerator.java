package ch.k24.auroraprime.quorg;

import java.util.ArrayList;
import java.util.List;

import ch.k42.auroraprime.R;
import ch.k42.auroraprime.dto.QuorgSettings.QUORG;

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
		
		//no Quoq defionition
		
		Quorg emptyQuorg = new Quorg(R.drawable.ic_field_000,QUORG.OFF, "Empty Quorg", false);
		list.add(emptyQuorg);
		
		//Red LED Quorg definition
		
		Quorg redLedQuorg = new Quorg(R.drawable.ic_field_001,QUORG.REDLIGHT, "Red LED Field", false);
		list.add(redLedQuorg);
		
		//Green LED Quorg definition
		
		Quorg greenLedQuorg = new Quorg(R.drawable.ic_field_002,QUORG.GREENLIGHT, "Green LED Field", false);
		list.add(greenLedQuorg);
				
		//Blue LED Quorg definition
				
		Quorg blueLedQuorg = new Quorg(R.drawable.ic_field_003,QUORG.BLUELIGHT, "Blue LED Field", false);
		list.add(blueLedQuorg);
		
		//Clock Quorg definition
		
		Quorg clockQuorg = new Quorg(R.drawable.ic_field_004,QUORG.CLOCK, "Clock", false);
		list.add(clockQuorg);
		
		
		//random Quoq defionition
		
		Quorg randomQuorg = new Quorg(R.drawable.ic_field_005,QUORG.RANDOM, "Random Dots", false);
		list.add(randomQuorg);
		
		//mail Quorg definition
		
		Quorg mailQuorg = new Quorg(R.drawable.ic_field_006,QUORG.MAIL, "E-Mail Notifier", true);
		list.add(mailQuorg);
		
		//weather LED Quorg definition
		
		Quorg weatherQuorg = new Quorg(R.drawable.ic_field_007,QUORG.WEATHER, "Weather", true);
		list.add(weatherQuorg);
				
		//matrix LED Quorg definition
				
		Quorg matrixQuorg = new Quorg(R.drawable.ic_field_008,QUORG.MATRIX, "Matrix Effect", false);
		list.add(matrixQuorg);
		
		//audiovisualizer Quorg definition
		
		Quorg visualizerQuorg = new Quorg(R.drawable.ic_field_009,QUORG.AUDIOVISUALIZER, "Audiovisualizer", false);
		list.add(visualizerQuorg);
		
		//Reddit Quorg definition
		
		Quorg redditQuorg = new Quorg(R.drawable.ic_field_010,QUORG.REDDIT, "Karma Train Notifier", true);
		list.add(redditQuorg);
		

		return list;
	}
		
}
