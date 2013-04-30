package ch.k42.auroraprime.core;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 4/30/13
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class MatrixManager {
    private MatrixManager instance = new MatrixManager();

    private class MatrixDiscoveryThread extends Thread{
        @Override
        public void run(){

        }
    }


    // Singleton
    private MatrixManager(){

    }

    public List<Integer> discoverMatrices(){

        return null;
    }
}
