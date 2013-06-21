package ch.k42.auroraprime.main;

import ch.k42.auroraprime.core.MatrixManager;
import ch.k42.auroraprime.core.NetManager;
import ch.k42.auroraprime.core.QuorgManager;
import ch.k42.auroraprime.executors.Executor;
import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.net.QRequestHandler;
import ch.k42.auroraprime.net.RequestHandler;
import ch.k42.auroraprime.net.RequestHandlerFactory;
import ch.k42.auroraprime.quorgs.*;
import ch.k42.auroraprime.sim.SimSender;

public class Main {



	/**
     *
     * Notes:
     * - the Transmission Layer can be choosen by editing the 'SenderFactory'
     *
	 * @param args
	 */
	public static void main(String[] args) {
		Log.vv("MAIN","Started!");

        if(args.length==0){
            i2c_raspi();
        }else {
            simulator();
        }

		
	}

    private static void i2c_raspi(){
        // Set some default Quorgs
        String[] init = {"RP-MkII  by Thomas Richner","0xFF0000","0x000000","1"};
        QuorgManager.getInstance().putQuorg(1,new TextEffect(init));
        QuorgManager.getInstance().putQuorg(2,new BinaryClock(new String[0]));
        QuorgManager.getInstance().putQuorg(3,new Matrix(new String[0]));
        QuorgManager.getInstance().putQuorg(4,new Nexus(new String[0]));

        MatrixManager.getInstance().start();

        NetManager.getInstance().start(new RequestHandlerFactory() {
            @Override
            public RequestHandler getInstance() {
                return new QRequestHandler();  //To change body of implemented methods use File | Settings | File Templates.
            }
        });//() ->  new StringHandler()); //TODO use other handler

        //==== Running forever
        try {
            Thread.sleep(1500000000);
        } catch (InterruptedException e) {
            Log.e("MAIN","stahp! what are you doing??! Not supposed to happen!");
            System.exit(1);
        }

        //==== stopping
        NetManager.getInstance().stop();
        MatrixManager.getInstance().stop();     // disconnect matrices
        Executor.getInstance().shutdownNow();   // just in case
        QuorgManager.getInstance().removeAll(); // stop all quorgs
    }

    private static void simulator(){
        // Set some default Quorgs
        String[] init = {"RP-MkII Simulator by Thomas Richner","0xFF0000","0x000000","1"};
        QuorgManager.getInstance().putQuorg(1,new TextEffect(init));
        QuorgManager.getInstance().putQuorg(2,new BinaryClock(new String[0]));
        QuorgManager.getInstance().putQuorg(3,new Matrix(new String[0]));
        QuorgManager.getInstance().putQuorg(4,new Nexus(new String[0]));

        MatrixManager.getInstance().setSender(new SimSender());
        MatrixManager.getInstance().start();

        NetManager.getInstance().start(new RequestHandlerFactory() {
            @Override
            public RequestHandler getInstance() {
                return new QRequestHandler();  //To change body of implemented methods use File | Settings | File Templates.
            }
        });//() ->  new StringHandler()); //TODO use other handler

        //==== Running forever
        try {
            Thread.sleep(1500000000);
        } catch (InterruptedException e) {
            Log.e("MAIN","stahp! what are you doing??! Not supposed to happen!");
            System.exit(1);
        }

        //==== stopping
        NetManager.getInstance().stop();
        MatrixManager.getInstance().stop();     // disconnect matrices
        Executor.getInstance().shutdownNow();   // just in case
        QuorgManager.getInstance().removeAll(); // stop all quorgs
    }

	private static void addHook(Thread hook){
        Runtime.getRuntime().addShutdownHook(hook);
	}

}
