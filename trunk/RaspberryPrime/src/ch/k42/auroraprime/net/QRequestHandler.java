package ch.k42.auroraprime.net;


import ch.k42.auroraprime.minions.Log;
import ch.k42.auroraprime.net.DTO.Request;

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

                break;
            case STARTQUORG:

                break;
            case GETUPDATE:

                break;
        }

        return request.wasHandled();
    }
}
