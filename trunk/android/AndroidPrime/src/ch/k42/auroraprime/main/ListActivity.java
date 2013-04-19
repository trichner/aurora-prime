package ch.k42.auroraprime.main;

import java.io.IOException;
import java.util.List;

import ch.k24.auroraprime.quorg.Quorg;
import ch.k24.auroraprime.quorg.QuorgAdapter;
import ch.k24.auroraprime.quorg.QuorgListFactory;
import ch.k42.auroraprime.R;
import ch.k42.auroraprime.net.QuorgSettings;
import ch.k42.auroraprime.net.Request;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.Toast;


public class ListActivity extends Activity{

//private TableRow tableRow1;
//private TableRow tableRow2;
//private TableRow tableRow3;
//private TableRow tableRow4;
//	
private ListView quorgListView;

public enum Command{
	SETQUORG,
	STARTQUORG,
	GETUPDATE
}
	
	private class TableElementListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Quorg quorg = (Quorg) parent.getItemAtPosition(position);
//			Toast.makeText(getBaseContext(),quorg.getName(), Toast.LENGTH_LONG).show();
			
			if (quorg.hasSettings()){
				Toast.makeText(getBaseContext(),"settings are not yet implemented bro", Toast.LENGTH_LONG).show();
			} else {
				QuorgSettings settings = new QuorgSettings();
				settings.setQuorgID(quorg.getQuorgID());
				AndroidPrimeApplication ourApplication = ((AndroidPrimeApplication) getApplication());
//				settings.setScreen(ourApplication.activeScreen);
				
				Request request = new Request(ch.k42.auroraprime.net.Request.Command.SETQUORG ,settings);
				SendRequest(request);
			}
			Intent i = new Intent(ListActivity.this, HomeActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);
		}
		
	}

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        
        List<Quorg> quorgData = new QuorgListFactory().getQuorgList();
        
        QuorgAdapter adapter = new QuorgAdapter(this, 
                R.layout.quorg_list_view_row, quorgData);
        
        quorgListView = (ListView)findViewById(R.id.quorgListView);
        quorgListView.setAdapter(adapter);
        quorgListView.setOnItemClickListener(new TableElementListener());
    }
    
    public Object SendRequest(Object request){	
    	
    	AndroidPrimeApplication ourApplication = ((AndroidPrimeApplication) getApplication());
    	if (ourApplication.connectClient.isConnected()){
    		ourApplication.connectClient.sendRequest(request);
    		return null;
    	} else {
    		Toast.makeText(getBaseContext(),"no device connected", Toast.LENGTH_LONG).show();
    		return null;	
    	}		   	
    }

}
