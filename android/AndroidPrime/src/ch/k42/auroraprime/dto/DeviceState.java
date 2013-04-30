package ch.k42.auroraprime.dto;

import java.util.List;
import java.util.SortedMap;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 4/30/13
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class DeviceState {
    private SortedMap<Integer,QuorgSettings> quorgs;
    private List<Integer> matrices;

    public DeviceState(SortedMap<Integer,QuorgSettings> quorgs, List<Integer> matrices){
        this.quorgs = quorgs;
        this.matrices = matrices;
    }

    public SortedMap<Integer, QuorgSettings> getQuorgs() {
        return quorgs;
    }

    public List<Integer> getMatrices() {
        return matrices;
    }
}
