package ch.k42.auroraprime.quorgs;

import java.io.Serializable;

public abstract class Quorg extends Thread implements Serializable{

	protected volatile boolean quit = false;
    protected String[] settings;
    private int matrix = -1;

    public void setMatrixAddress(int matrix){
        this.matrix = matrix;
    }

    public int getMatrixAddress() {
        return matrix;
    }

    public void terminate(){
		quit = true;
        this.interrupt();
	}
    public boolean isRunning(){
        return this.getState()!=State.TERMINATED && this.getState()!=State.NEW;
    }

    /**
     * This method can be used to use some basic user input. You have to specify your own
     * String format.
     * @param settings Settings in Stringformat
     */
    public Quorg(String[] settings){
        this.settings = settings;
    }

    public abstract String getQuorgName();

	@Override
    /**
     * This can be used to bring live to your effect. When a Quorg
     * gets set, this Thread will be started an run will be executed.
     * Usually, this method should look like this:
     * <PRE>
     *     private Frame frame;
     *     public void run(){
     *         quit=false;
     *         while(!quit){
     *              Frame newframe = new Frame();
     *             // Calculate a new frame
     *              frame = newframe;
     *             try{
     *                 sleep(someTime);
     *             }catch(InterruptedException e){}
     *         }
     *     }
     *
     *     public IFrame8x8 getFrame(){
     *          this.interrupt();
     *          return frame;
     *     }
     * </PRE>
     *
     */
	public abstract void run();

    /**
     * This function gets called whenever a Frame gets sent to the address specified for this quorg. This
     * call should be very fast in order to hold time constraints. Don't use this to generate any Frames here,
     * Just return a precalculated Frame. To calculate this, use the run() Method.
     * @return Frame displayed on the Matrix
     */
    public abstract IFrame8x8 getFrame();


    /**
     * Returns your settings as a String, this should represent the current
     * configuration of this Quorg. If not used, you should at least return
     * the string which was set by initSettings()
     * @return Settings in Stringformat
     */
    public String[] getSettings(){
        return this.settings;
    }
}
