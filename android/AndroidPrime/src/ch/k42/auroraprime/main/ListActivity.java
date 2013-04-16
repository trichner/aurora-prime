package ch.k42.auroraprime.main;

import ch.k42.auroraprime.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableRow;


public class ListActivity extends Activity{

private TableRow tableRow1;
private TableRow tableRow2;
private TableRow tableRow3;
private TableRow tableRow4;
	

	private class TableElementListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(ListActivity.this, HomeActivity.class);
			startActivity(i);
		}
		
	}

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
       
        tableRow1 = (TableRow) findViewById(R.id.tableRow1);
        tableRow2 = (TableRow) findViewById(R.id.tableRow2);
        tableRow3 = (TableRow) findViewById(R.id.tableRow3);
        tableRow4 = (TableRow) findViewById(R.id.tableRow4);
        
        TableElementListener tableElementListener = new TableElementListener();
        
        tableRow1.setOnClickListener(tableElementListener);
        tableRow2.setOnClickListener(tableElementListener);
        tableRow3.setOnClickListener(tableElementListener);
        tableRow4.setOnClickListener(tableElementListener);
        
        //TODO everything
    }
    	//TODO even more
}
