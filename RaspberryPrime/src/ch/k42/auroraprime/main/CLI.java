package ch.k42.auroraprime.main;

import ch.k42.auroraprime.core.MatrixManager;
import ch.k42.auroraprime.core.QuorgManager;
import ch.k42.auroraprime.executors.Executor;
import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.quorgs.ColorQuorg;
import ch.k42.auroraprime.quorgs.MailQuorg;
import ch.k42.auroraprime.quorgs.Quorg;
import ch.k42.auroraprime.quorgs.RandomQuorg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 5/23/13
 * Time: 2:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class CLI {
    private static final String TAG = "CLI";

//    private static enum CMD{
//        SETQ("set"),
//        SHOW("show"),
//        RMQG("remove"),
//        LIST("list"),
//        HELP("help"),
//        QUIT("quit");
//        public final String cmd;
//        public final String desc;
//        CMD(String cmd){
//            this.cmd = cmd;
//            desc = "";
//        }
//        CMD(String cmd,String description){
//            this.cmd = cmd;
//            this.desc = description;
//        }
//    }

    List<Class> quorgs = new ArrayList<>();

    public CLI(){
        quorgs.add(RandomQuorg.class);
        quorgs.add(MailQuorg.class);
    }
    private BufferedReader in;
    public void run(){

        Runtime.getRuntime().addShutdownHook( new Thread() {
            @Override
            public void run() {
                //this.notifyAll();
            }
        } );

        QuorgManager.getInstance().putQuorg(1,new RandomQuorg());
        QuorgManager.getInstance().putQuorg(2,new RandomQuorg());
        QuorgManager.getInstance().putQuorg(3,new RandomQuorg());
        QuorgManager.getInstance().putQuorg(4,new RandomQuorg());
        MatrixManager.getInstance().start();

        //==== Running

        in = new BufferedReader(new InputStreamReader(System.in));
        String line;

        do{
            try {
                line = in.readLine();
            } catch (IOException e) {
                Log.e(TAG,"unable to read from console input: " + e.getMessage());
                break;
            }
            switch (line){
                case "put":
                    setq();
                    break;

            }


        }while(!line.equals("exit"));

        //==== stopping
        MatrixManager.getInstance().stop();     // disconnect matrices
        Executor.getInstance().shutdownNow();   // just in case
        QuorgManager.getInstance().removeAll(); // stop all quorgs
    }

    private void setq(){
        l("Choose Quorg:");
        list();
        try {
            int i = Integer.parseInt(in.readLine());
            Quorg q;
            Class c = quorgs.get(i);
            q = new RandomQuorg();
            switch (i){
                case 0: //random quorg
                    q = (Quorg) c.newInstance();
                    break;
                case 1: //mail quorg
                    Constructor cons = c.getConstructor(String.class,String.class);
                    l("Username:");
                    String user = in.readLine();
                    l("Password:");
                    String password = in.readLine();
                    q = (Quorg) cons.newInstance(user,password);

                    break;
                case 2: //COLOR quorg
                    q = (Quorg) c.newInstance();
                    q.initSettings("blue");
                    break;
            }

            QuorgManager.getInstance().putQuorg(ID,q);

        } catch (Exception e) {
            Log.e(TAG,"Illegal input");
        }

    }
    private void show(){}
    private void rmqg(){}
    private void help(){}

    private void list(){
        for(int i=0;i<quorgs.size();i++){
            p(i + " : ");
            l(quorgs.get(i).getName());
        }
    }

    private static void p(String s){
        System.out.print(s);
    }
    private static void l(String s){
        System.out.println(s);
    }
}
