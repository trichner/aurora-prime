package ch.k42.ironhide.NetClientTMP;


import ch.k42.auroraprime.core.MatrixManager;
import ch.k42.auroraprime.core.QuorgManager;
import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.net.DTO.Request;
import ch.k42.auroraprime.quorgs.Quorg;
import ch.k42.ironhide.net.Request;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 4/19/13
 * Time: 8:02 PM
 */
public class QRequestHandler implements RequestHandler{
    private static final String TAG="RequestHandler";
    @Override
    public Object getResponse(Object o) {
        Request request;
        if (o instanceof Request)
            request = (Request) o;
        else
            return o;



        switch (request.getCmd()) {

            case SETQUORG:
                try{
                    Log.d(TAG,"SETQUORG");
                    Object args[] = request.getArgs();
                    if(args.length<3) break;

                    Integer position = (Integer) args[0];
                    Log.d(TAG,"parsing position: "+position);
                    Class c = (Class) args[1];
                    Log.d(TAG,"Class: "+c.getName());
                    String[] settings = (String[]) args[2];
                    Log.d(TAG,"Settings: " + Arrays.toString(settings));
                    Constructor cons = c.getConstructor(String[].class);
                    Log.d(TAG,"Found Constructor");
                    Quorg q = (Quorg) cons.newInstance((Object) settings);
                    Log.d(TAG,"Created new Instance");
                    QuorgManager.getInstance().putQuorg(position,q);
                    Log.d(TAG,"successfully set new Quorg");
                    request.setHandled(true);
                    Log.d(TAG,"SETQUORG handled");
                }catch(Exception e){
                    Log.w(TAG, "Invalid SETQUORG command: " + e.getMessage());
                }
                break;
            case GETUPDATE:
                try{
                    Serializable args[] = new Serializable[2];
                    Map qmap= new HashMap<Integer,Class>();
                    List mlist = new ArrayList<Integer>();
                    for(Map.Entry entry : QuorgManager.getInstance().getAllQuorgs().entrySet()){
                        qmap.put(entry.getKey(), entry.getValue().getClass());
                    }
                    for(Integer m : MatrixManager.getInstance().getMatrices().keySet()){
                        mlist.add(m);
                    }
                    args[0] = (Serializable) qmap; //legal, ArrayList implements Serializable
                    args[1] = (Serializable) mlist;

                    request.setArgs(args);
                    request.setHandled(true);
                    Log.d(TAG,"GETUPDATE handled");
                }catch(Exception e){
                    Log.w(TAG, "Invalid SETQUORG command");
                }
                break;
        }


        return request;
    }


}
