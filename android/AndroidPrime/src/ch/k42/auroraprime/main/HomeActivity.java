package ch.k42.auroraprime.main;

import ch.k42.auroraprime.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;


public class HomeActivity extends Activity implements OnClickListener {
	
	private static final String TAG = "HomeActivity";
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	
	//is the app connected to a device?
	private boolean connected = false;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //die Views Suchen
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

public void onClick(View v) {
	// get id of button pressed, send intent and start ListActivity
	Intent i=null;
	switch (v.getId())
	{
	case R.id.button1: i = new Intent(this, ListActivity.class);
					   i.putExtra("sourceButton", "button1"); break;
	case R.id.button2: i = new Intent(this, ListActivity.class);
	   				   i.putExtra("sourceButton", "button2"); break;
	case R.id.button3: i = new Intent(this, ListActivity.class);
					   i.putExtra("sourceButton", "button3"); break;
	case R.id.button4: i = new Intent(this, ListActivity.class);
					   i.putExtra("sourceButton", "button4"); break;
	}
	Log.d(TAG, "onClicked");
	startActivity(i);
	
}

}