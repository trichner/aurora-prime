package ch.k42.auroraprime.quorgs;

import ch.k42.auroraprime.minions.Log;

import java.util.Calendar;
import java.util.Date;

//doesn't work^^
public class BinaryClock extends NEOEffect{
	private int color = NEOUtils.NEO_WHITE;
	private static final String TAG = "Binary Clock";
	private static final boolean D = false;

    /**
     *
     * @param settings color (Color)
     */
	public BinaryClock(String[] settings){
		super(settings);
        if(settings.length>1){
            try {
                this.color = NEOUtils.toNEOColor(settings[0]);
            }catch (Exception e){
                Log.e(TAG,e.getMessage());
            }
        }
		Log.vv(TAG, "New Binary Clock!");
		array = NEOUtils.getEmpty8x8();
	}
	
	public void setColor(int color){
		this.color = color;
	}

	
	@Override
	public int[][] getArray() {
		this.interrupt();       
		return array;
	}

	@Override
	public void run() {

		while(!EXIT){
			array = NEOUtils.getEmpty8x8();
			Date date = Calendar.getInstance().getTime();
			int hours = date.getHours();
			int minutes = date.getMinutes();
			int seconds = date.getSeconds();
			if(D) Log.d(TAG,"Hours: " + hours +"  Minutes: "+minutes);
			
			if(((hours/10)&0x01)==0x01){
				array[7][1] = color;
			}
			if(((hours/10)&0x02)==0x02){
				array[6][1] = color;
			}
			hours = hours%10;
			if((hours&0x01)==0x01){
				array[7][2] = color;
			}
			if((hours&0x02)==0x02){
				array[6][2] = color;
			}
			if((hours&0x04)==0x04){
				array[5][2] = color;
			}
			if((hours&0x08)==0x08){
				array[4][2] = color;
			}
			
			// Display 1 cipher of minutes
			if(((minutes/10)&0x01)==0x01){
				array[7][5] = color;
			}
			if(((minutes/10)&0x02)==0x02){
				array[6][5] = color;
			}
			if(((minutes/10)&0x04)==0x04){
				array[5][5] = color;
			}
			
			// Display 2 cipher of minutes
			minutes %= 10;
			if(((minutes)&0x01)==0x01){
				array[7][6] = color;
			}
			if(((minutes)&0x02)==0x02){
				array[6][6] = color;
			}
			if(((minutes)&0x04)==0x04){
				array[5][6] = color;
			}
			if(((minutes)&0x08)==0x08){
				array[4][6] = color;
			}
			
			if(seconds%2==0){
				array[7][7] = NEOUtils.NEO_GREEN;
			}
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {	}
			
			
		}
		
	}

	
}
