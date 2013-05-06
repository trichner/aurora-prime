package ch.k42.auroraprime.net;


import ch.k42.auroraprime.core.IMatrix;
import ch.k42.auroraprime.core.MatrixManager;
import ch.k42.auroraprime.core.QuorgManager;
import ch.k42.auroraprime.net.DTO.DeviceState;
import ch.k42.auroraprime.net.DTO.QuorgSettings;
import ch.k42.auroraprime.net.DTO.Request;
import ch.k42.auroraprime.quorgs.ColorQuorg;
import ch.k42.auroraprime.quorgs.Quorg;
import ch.k42.auroraprime.quorgs.RandomQuorg;

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

                int pos=0;
                Object arg = request.getArg();

                if(!(arg instanceof QuorgSettings)) //wrong argument?
                    break;

                QuorgSettings nqset = (QuorgSettings) arg;
                Quorg newQuorg=null;

                switch (nqset.getQuorg()) {
                    case OFF:
                        break;
                    case REDLIGHT:
                        newQuorg = new ColorQuorg();
                        break;
                    case GREENLIGHT:
                        newQuorg = new ColorQuorg();
                        break;
                    case BLUELIGHT:
                        newQuorg = new ColorQuorg();
                        break;
                    case CLOCK:
                        break;
                    case RANDOM:
                        newQuorg = new RandomQuorg();
                        break;
                    case MAIL:
                        break;
                    case WEATHER:
                        break;
                    case MATRIX:
                        break;
                    case AUDIOVISUALIZER:
                        break;
                    case REDDIT:
                        break;
                }

                if(newQuorg==null) break; // no matching quorg found?

                newQuorg.initSettings(nqset.getSettings());
                QuorgManager.getInstance().putQuorg(nqset.getScreen(),newQuorg);
                request.wasHandled();
                break;
            case GETUPDATE:

                SortedMap<Integer,Quorg> quorgs =QuorgManager.getInstance().getAllQuorgs();
                List<Integer> matrices = new ArrayList<Integer>();
                Collection<IMatrix> tlist = MatrixManager.getInstance().getSender().getMatrices().values();
                for(IMatrix m : tlist){
                    matrices.add(m.getID());
                }
                SortedMap<Integer,QuorgSettings> smap = new TreeMap<Integer, QuorgSettings>();
                for(Quorg q : quorgs.values()){
                    QuorgSettings qset = new QuorgSettings();
                    qset.setSettings(q.getSettings());
                    qset.setMatrixID(q.getMatrixID());
                    qset.setQuorg(QuorgSettings.QUORG.RANDOM); //FIXME
                    smap.put(q.getMatrixID(),qset);
                }

                DeviceState state = new DeviceState(smap,matrices);

                //request.setArg(state);

                request.wasHandled();
                break;
        }


        return request;
    }


}
