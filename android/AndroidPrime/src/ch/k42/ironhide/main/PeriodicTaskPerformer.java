package ch.k42.ironhide.main;
import android.os.Handler;
import android.util.Log;
/**
 * A class used to perform periodical updates,
 * specified inside a runnable object. An update interval
 * may be specified (otherwise, the class will perform the 
 * update every 2 seconds).
 * 
 * @Author Carlos Sim�es, changed by Philipp B�sch
 */

//Names changed to PeriodicTaskPerformer, interval changed to 20 seconds

public class PeriodicTaskPerformer {

        private Handler mHandler = new Handler();

        private Runnable mStatusChecker;
        private int UPDATE_INTERVAL = 20000;
        
        private static final String TAG = "PeriodicTaskPerformer";

        /**
         * Creates an UIUpdater object, that can be used to
         * perform UIUpdates on a specified time interval.
         * 
         * @param uiUpdater A runnable containing the update routine.
         */
        public PeriodicTaskPerformer(final Runnable ptPerformer){
            mStatusChecker = new Runnable() {
             
                public void run() {
                    // Run the passed runnable
                	ptPerformer.run();
                	Log.d(TAG, "Device Update Performed");
                    // Re-run it after the update interval
                    mHandler.postDelayed(this, UPDATE_INTERVAL);
                }
            };
        }

        /**
         * The same as the default constructor, but specifying the
         * intended update interval.
         * 
         * @param uiUpdater A runnable containing the update routine.
         * @param interval  The interval over which the routine
         *                  should run (milliseconds).
         */
        public PeriodicTaskPerformer(Runnable ptPerformer, int interval){
            this(ptPerformer);
        }

        /**
         * Starts the periodical update routine (mStatusChecker 
         * adds the callback to the handler).
         */
        public void startUpdates(){
            mStatusChecker.run();
            Log.d(TAG, "Device Updates Started");
        }

        /**
         * Stops the periodical update routine from running,
         * by removing the callback.
         */
        public void stopUpdates(){
            mHandler.removeCallbacks(mStatusChecker);
            Log.d(TAG, "Device Updates Stopped");
        }
}