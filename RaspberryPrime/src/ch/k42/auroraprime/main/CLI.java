package ch.k42.auroraprime.main;

import ch.k42.auroraprime.minions.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public CLI(){

    }

    public void run(){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;

        do{
            try {
                line = in.readLine();
            } catch (IOException e) {
                Log.e(TAG,"unable to read from console input: " + e.getMessage());
                break;
            }

            switch (line){
                case "help":

                    break;
            }

        }while(!line.equals("exit"));


    }

    private void setq(){}
    private void show(){}
    private void rmqg(){}
    private void help(){}
    private void list(){}
}
