package ch.k42.auroraprime.executors;

import ch.k42.auroraprime.sim.SimSender;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 4/30/13
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class SenderFactory {
    public static Sender getInstance(){
        return new SimSender();
    }
}
