package ch.k42.auroraprime.main;

import java.io.IOException;
import java.util.List;

import ch.k24.auroraprime.quorg.Quorg;
import ch.k24.auroraprime.quorg.QuorgAdapter;
import ch.k24.auroraprime.quorg.QuorgListFactory;
import ch.k42.auroraprime.R;
import ch.k42.auroraprime.dto.DeviceState;
import ch.k42.auroraprime.dto.QuorgSettings;
import ch.k42.auroraprime.dto.Request;
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
/**
 * Class for the list activity that lists all 
 * the avaliable quorgs
 * 
 * @Author Philipp Bšsch
 */

public class ListActivity extends Activity{

private ListView quorgListView;

public enum Command{
	SETQUORG,
	STARTQUORG,
	GETUPDATE
}
	

/**
 * Listener for the Table Elements 
 * 
 * 
 */
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
				settings.setQuorg(quorg.getQuorgID());
				AndroidPrimeApplication ourApplication = ((AndroidPrimeApplication) getApplication());
				settings.setMatrixID(ourApplication.getQuorgFields()[ourApplication.getSelectedField()].getFieldID());
				Request request = new Request(Request.Command.SETQUORG ,settings);
				SendRequest(request);
			}
			Intent i = new Intent(ListActivity.this, HomeActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);
		}
		
	}

	
	/** Called when the activity is first created. 
	 * 
	 * 
	 * 
	 * */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        
        AndroidPrimeApplication ourApplication = ((AndroidPrimeApplication) getApplication());
        
        ourApplication.setQuorgData(new QuorgListFactory().getQuorgList());
        
        QuorgAdapter adapter = new QuorgAdapter(this, 
                R.layout.quorg_list_view_row, ourApplication.getQuorgData());
        
        quorgListView = (ListView)findViewById(R.id.quorgListView);
        quorgListView.setAdapter(adapter);
        quorgListView.setOnItemClickListener(new TableElementListener());
    }
    
    
    /**
     * sends a generic request with the client
     * 
     * 
     */
    public Object SendRequest(Object request){	
    	
    	AndroidPrimeApplication ourApplication = ((AndroidPrimeApplication) getApplication());
    	if (ourApplication.getConnectClient().isConnected()){
    		Request requestAnswer = (Request) ourApplication.getConnectClient().sendRequest(request);
    		DeviceState newState = (DeviceState) requestAnswer.getArg();
    			if (requestAnswer.wasHandled()){
    				for (int i = 0; i<4; i++){
    					//set net settings
    					ourApplication.getQuorgFields()[i].setSettings(newState.getQuorgs().get(newState.getMatrices().get(i)));
    					//set new quorg
    					ourApplication.getQuorgFields()[i].setActiveQuorg(ourApplication.getQuorgData().get(ourApplication.getQuorgFields()[i].getSettings()
    							.getQuorg().number));
    				} 
    			} else {
    				Toast.makeText(getBaseContext(),"request could not be handled", Toast.LENGTH_LONG).show();
				}
    			
    		return null;
    	} else {
    		Toast.makeText(getBaseContext(),"no device connected", Toast.LENGTH_LONG).show();
    		return null;	
    	}		   	
    }

}
