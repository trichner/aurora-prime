package ch.k42.auroraprime.net;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 28.05.13
 * Time: 21:47
 * To change this template use File | Settings | File Templates.
 */
public class IServerFactory {
    public static IServer getInstance(){
        return new AthmosServer();
    }
}
