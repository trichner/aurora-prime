package ch.k42.auroraprime.core;

import ch.k42.auroraprime.quorgs.IFrame8x8;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 15.04.13
 * Time: 01:55
 * To change this template use File | Settings | File Templates.
 */
public interface IMatrix {
    public boolean sendFrame(IFrame8x8 f);
    /**
     * The ID of the Matrix
     * @return ID
     */
    public int getID();
    /**
     *
     */
}
