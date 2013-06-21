package ch.k42.auroraprime.core;

import ch.k42.auroraprime.quorgs.Quorg;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 3/12/13
 * Time: 10:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class QuorgManager {
    //==== Singleton stuff
    private static QuorgManager instance = new QuorgManager();

    public static QuorgManager getInstance(){
        return  instance;
    }

    //==== Body
    private ConcurrentHashMap<Integer,Quorg> quorgs;


    private QuorgManager(){
        quorgs = new ConcurrentHashMap<>();
    }

    public boolean isQuorg(int key){
        return quorgs.containsKey(key);
    }

    public Quorg getQuorg(int key) {
        if(isQuorg(key)){
            return quorgs.get(key);
        }
        return null;
    }

    public boolean putQuorg(int address,Quorg quorg){
        //if(quorgs.containsKey(matrixID)) return false; // identifier not unique?
        removeQuorg(address);
        quorg.setMatrixAddress(address); // set a unique identifier
        if(!quorg.isRunning()) quorg.start();
        quorgs.put(address, quorg);
        return true;
    }

    public boolean removeQuorg(int address){
        if (!isQuorg(address)){
            return false;
        }
        Quorg q = quorgs.get(address);
        q.terminate();
        quorgs.remove(address);
        return  true;
    }

    public QuorgManager removeAll(){
        for(Quorg q : quorgs.values()){
           q.terminate();
        }
        quorgs.clear();
        return this;
    }

    public Set getKeys(){
        return quorgs.keySet();
    }

    public Map<Integer, Quorg> getAllQuorgs(){
        //List<Quorg> quorgList = new ArrayList<Quorg>(quorgs.values());
        return quorgs;
    }
}
