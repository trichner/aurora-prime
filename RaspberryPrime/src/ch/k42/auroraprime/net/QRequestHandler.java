package ch.k42.auroraprime.net;


import ch.k42.auroraprime.core.QuorgManager;
import ch.k42.auroraprime.net.DTO.Request;
import ch.k42.auroraprime.quorgs.Quorg;
import ch.k42.auroraprime.quorgs.RandomQuorg;

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
                Quorg newQuorg = new RandomQuorg();

                QuorgManager.getInstance().putQuorg(pos,newQuorg);
                break;
            case STARTQUORG:

                break;
            case GETUPDATE:

                break;
        }

        return request.wasHandled();
    }


}
