package ch.k42.auroraprime.quorgs;

import ch.k42.auroraprime.minions.Log;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 27.05.13
 * Time: 17:22
 * To change this template use File | Settings | File Templates.
 */
public class MailQuorg extends Quorg{
    public static final String name="MailQuorg";
    private static final String TAG = "MailQuorg";
    private static final String url = "https://mail.google.com/mail/feed/atom";
    private  URL feed;
    private String user,password;
    private int newmails=0;
    private IFrame8x8 frame=new Frame1bit();
    public MailQuorg(String user,String password){
        Log.d(TAG,"New MailQuorg");
        this.user = user;
        this.password = password;

        try {
            feed = new URL(url);
        } catch (MalformedURLException e) {
            Log.e(TAG,"URL invalid: " + url + " Errormessage: " + e.getMessage());
        }
    }

    @Override
    public IFrame8x8 getFrame() {
        return frame;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void run() {
        Pattern pattern = Pattern.compile("<fullcount>([0-9]+)</fullcount>");
        quit = false;   //make sure quit is false
        while (!quit){

            //check mails
            try {
                Log.d(TAG,"Checking mails...");
                HttpURLConnection connection = (HttpURLConnection) feed.openConnection();
                connection.setRequestProperty("Authorization","Basic " + Base64.encode((user + ":" + password).getBytes()));
                connection.connect();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                String contents = "";
                while ((line = reader.readLine()) != null) {
                    contents += line;
                }
                //System.out.println(contents);

                Matcher m = pattern.matcher(contents);
                if(m.find()){
                    newmails = Integer.parseInt(m.group(1));
                    Log.d(TAG,"I have found "+newmails+ " new messages in your inbox!");
                }else{
                    Log.e(TAG,"Can't find entry in XML.");
                }


                frame = QuorgUtils.getDigitFrame1bit(newmails);
            } catch (IOException e) {
                Log.e(TAG,"Unable to open connection. Error message: "+e.getMessage());
            }

            try {
                sleep(10000);
            } catch (InterruptedException e) {}
        }
    }

    @Override
    public void initSettings(String[] settings) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String[] getSettings() {
        return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
    }
}
