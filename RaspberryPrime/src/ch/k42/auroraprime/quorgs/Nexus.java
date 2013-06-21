package ch.k42.auroraprime.quorgs;


import java.util.Random;

public class Nexus extends NEOEffect{
	
	private class Strip{
		public Strip(double position,double speed,int length,int color){
			this.position = position;
			this.speed = speed;
			this.length = length;
			this.color = color;
		}
		

		public Strip(){
			Random rand = new Random();
			if(rand.nextBoolean()){
				this.position = 0;
				this.speed = rand.nextDouble()+0.7;
			}else{
				this.position = 8+this.length; //TODO maybe only 8...
				this.speed = -(rand.nextDouble()+0.7);
			}
			this.length = rand.nextInt(5)+2;
			this.color = rand.nextInt(8);
		}
		public void step(){
			if(length>0)
				position += speed;
		}
		int color;
		double position;
		double speed;
		int length;
	}
	
	Strip[] vStrips= new Strip[8];
	Strip[] hStrips= new Strip[8];
	
	public Nexus(String[] settings){
		super(settings);
		for(int i=0;i<8;i++){
			vStrips[i] = new Strip(); //Strip(0,rand.nextDouble()+0.5,rand.nextInt(5)+2,rand.nextInt(8));
			hStrips[i] = new Strip(); //(0,rand.nextDouble()+0.5,rand.nextInt(5)+2,rand.nextInt(8));
		}
	}
	
	@Override
	public int[][] getArray() {
		array = NEOUtils.emptyArray(8, 8);
		for(int i=0;i<8;i++){
			for(int j=0;(vStrips[i].position)>j;j++){
				if(((vStrips[i].position-vStrips[i].length)<j)&&(j<8)){
					array[j][i] = vStrips[i].color;
				}
			}
			for(int j=0;(hStrips[i].position)>j;j++){
				if(((hStrips[i].position-hStrips[i].length)<j)&&(j<8)){
					array[i][j] = hStrips[i].color;
				}
			}
		}
		return array;
	}
	
	@Override
	public void run() {
		Random rand = new Random();
		while(!EXIT){
			
			for(int i=0;i<8;i++){
				vStrips[i].step();
				hStrips[i].step();
				if(((vStrips[i].position-vStrips[i].length)>8) || ((vStrips[i].position+vStrips[i].length)<0)){
					vStrips[i] = new Strip();
				}
				if(((hStrips[i].position-hStrips[i].length)>8) || ((hStrips[i].position+hStrips[i].length)<0)){
					hStrips[i] = new Strip();
				}
			}
			
			try {
				sleep(100);
			} catch (InterruptedException e) {}
		}
		
	}

}
