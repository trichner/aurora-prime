package ch.k42.auroraprime.core;

import ch.k42.auroraprime.quorgs.Quorg;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 3/12/13
 * Time: 10:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class Quorgs {
    //==== Singleton stuff
    private static Quorgs instance = new Quorgs();

    private Quorgs(){
        quorgs = new HashMap<Integer, Quorg>();
    }

    public static Quorgs getInstance(){
        return  instance;
    }

    //==== Body
    private Map<Integer,Quorg> quorgs;
    public boolean isQuorg(int key){
        if(!quorgs.containsKey(key)) return false;

        return quorgs.get(key)!=null;
    }

    public Quorg getQuorg(int key) {
        if(isQuorg(key)){
            return quorgs.get(key);
        }
        return null;
    }

    public Quorgs putQuorg(int key,Quorg quorg){
        removeQuorg(key);
        if(!quorg.isRunning()) quorg.start();
        quorgs.put(key,quorg);
        return this;
    }

    public Quorgs removeQuorg(int key){
        if (isQuorg(key)){
          Quorg q = quorgs.get(key);
          q.terminate();
        }
        quorgs.remove(key);
        return  this;
    }

    public Quorgs removeAll(){
        for(Quorg q : quorgs.values()){
           q.terminate();
        }
        quorgs.clear();
        return this;
    }

    public Set getKeys(){
        return quorgs.keySet();
    }

}
