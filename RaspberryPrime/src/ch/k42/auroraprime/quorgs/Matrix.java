package ch.k42.auroraprime.quorgs;


import ch.k42.auroraprime.minions.Log;

import java.util.Random;

public class Matrix extends NEOEffect{
	
	private static final String TAG = "MATRIX";
	private static final boolean D = false;
	
	private class Strip{
		public Strip(double position,double speed,int length){
			this.position = position;
			this.speed = speed;
			this.length = length;
		}
		public void step(){
			if(length>0)
				position += speed;
		}
		double position;
		double speed;
		int length;
	}
	private int COLOR = NEOUtils.NEO_GREEN;

	Strip[] strips= new Strip[8];

    /**
     *
     * @param settings color (Color)
     */
	public Matrix(String[] settings){
        super(settings);
        if(settings.length>1)
            this.COLOR = NEOUtils.toNEOColor(settings[0]);
		Random rand = new Random();
		for(int i=0;i<8;i++){
			strips[i] = new Strip(0,rand.nextDouble()+0.5,rand.nextInt(5)+2);
		}
	}
	
	@Override
	public int[][] getArray() {
		return array;
	}

    public void setColor(int color){
        this.COLOR = color;
    }
	
	@Override
	public void run() {
		Random rand = new Random();
		int[][] tmparray;
		while(!EXIT){
			
			for(int i=0;i<8;i++){
				strips[i].step();
				if((strips[i].position-strips[i].length)>8){
					strips[i] = new Strip(0,rand.nextDouble()+0.7,rand.nextInt(5)+2);
				}
			}
			
			tmparray = NEOUtils.getEmpty8x8();
			for(int i=0;i<8;i++){
				for(int j=0;(strips[i].position)>j;j++){
					if(((strips[i].position-strips[i].length)<j)&&(j<8)){
						tmparray[j][i] = COLOR;
					}
				}
			}
			array = tmparray;
			if(D) Log.d(TAG, "next run...");
			try {
				sleep(100);
			} catch (InterruptedException e) {}
		}
		
	}

}
