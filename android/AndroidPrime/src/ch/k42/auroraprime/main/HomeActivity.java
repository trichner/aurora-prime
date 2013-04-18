package ch.k42.auroraprime.main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import ch.k42.auroraprime.R;
import ch.k42.auroraprime.net.*;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.Toast;
//import android.widget.RelativeLayout;
import android.content.Intent;
import android.graphics.Rect;


public class HomeActivity extends Activity {
	
	int titleHeight = 0;
	int statusHeight = 0;
	int contentViewTop = 0;
	int displayHeight = 0;
	int layoutHeight = 0;
	int layoutWidth = 0;
	
	int buttonSize = 0;
	int buttonOuterHorizontalPadding = 0;
	int buttonInnerHorizontalPadding = 0;
	int buttonOuterVerticalPadding = 0;
	int buttonInnerVerticalPadding = 0;
	
	int smallButtonSize = 0;
	int smallButtonOuterHorizontalPadding = 0;
	int smallButtonOuterVerticalPadding = 0;
	int smallButtonInnerHorizontalPadding = 0;
	int smallButtonInnerVerticalPadding = 0;
	
	Intent i;
	
	IDeviceDiscovery deviceDiscoverer;
	PeriodicTaskPerformer deviceListUpdater;
	
	List<ALDevice> deviceList = new ArrayList<ALDevice>();
	ALDevice selectedDevice;
	
	//listener for the four fields
	private class bigButtonListener implements OnClickListener {

		public void onClick(View v) {
			// get id of button pressed, send intent and start ListActivity
			//Intent i=null;
			switch (v.getId())
			{
			case R.id.button1: 	switchButtonVisibility(1);
							   	//i = new Intent(HomeActivity.this, ListActivity.class);
							   	//i.putExtra("sourceButton", "button1"); 
								break;
			case R.id.button2: 	switchButtonVisibility(2);
								//i = new Intent(HomeActivity.this, ListActivity.class);
			   				   	//i.putExtra("sourceButton", "button2"); 
								break;
			case R.id.button3: 	switchButtonVisibility(3);
								//i = new Intent(HomeActivity.this, ListActivity.class);
							   	//i.putExtra("sourceButton", "button3"); 
								break;
			case R.id.button4:	switchButtonVisibility(4);
								//i = new Intent(HomeActivity.this, ListActivity.class);
							   	//i.putExtra("sourceButton", "button4"); 
								break;
			}
			Log.d(TAG, "onClicked");
			//startActivity(i);
		}
		
	}
	
	//listener for the change module button
	private class changeModuleListener implements OnClickListener{

		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId())
			{
			case R.id.B1_3: 	i = new Intent(HomeActivity.this, ListActivity.class);
							   	//i.putExtra("sourceButton", "button1"); 
								break;
			case R.id.B2_3: 	i = new Intent(HomeActivity.this, ListActivity.class);
			   				   	//i.putExtra("sourceButton", "button2"); 
								break;
			case R.id.B3_3: 	i = new Intent(HomeActivity.this, ListActivity.class);
							   	//i.putExtra("sourceButton", "button3"); 
								break;
			case R.id.B4_3:		i = new Intent(HomeActivity.this, ListActivity.class);
							   	//i.putExtra("sourceButton", "button4"); 
								break;
			}
			Log.d(TAG, "changeModuleClicked");
			startActivity(i);
		}
		
	}
	
	//listener for the elements on the deviceList drop-down menu
	//will try to connect to the selected device in list
	private class deviceListListener implements OnItemSelectedListener{

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			
			ALDevice targetDevice = ((ALDevice) parent.getItemAtPosition(pos));
			
			//adress for net tester
			InetSocketAddress inetAddress;
			inetAddress = new InetSocketAddress("127.0.0.1", 1337);
			ALDevice netTesterDevice = new ALDevice(inetAddress, "NetTester");
			
			//toast which gives the selected device for testing
			Toast.makeText(parent.getContext(), 
					"Selected " + targetDevice.getName(),
					Toast.LENGTH_SHORT).show();
			
			AndroidPrimeApplication ourApplication = ((AndroidPrimeApplication) getApplication());
			
			if (ourApplication.connectClient.isConnected()) {
				ourApplication.connectClient.disconnect();
				ourApplication.newClient();
			};
				try {
					ourApplication.connectClient.connect(netTesterDevice.getAddress());
					String s = (String) ourApplication.connectClient.sendRequest("lollll");
					Log.d(TAG, "request sent, response: " + s);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.d(TAG, "client Connection Failed!");
				}
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private static final String TAG = "HomeActivity";
	
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	
	Button B1_1;
	Button B1_2;
	Button B1_3;
	Button B1_4;
	Button B2_1;
	Button B2_2;
	Button B2_3;
	Button B2_4;
	Button B3_1;
	Button B3_2;
	Button B3_3;
	Button B3_4;
	Button B4_1;
	Button B4_2;
	Button B4_3;
	Button B4_4;
	
	Spinner deviceListSpinner;

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Random modded Stack overflow code to get the space available for the active layout in view
        FrameLayout root = (FrameLayout) findViewById(R.id.linlayout);    
        root.post(new Runnable() {
        	
            public void run() {
                Rect rect = new Rect();
                Window win = getWindow();
                win.getDecorView().getWindowVisibleDisplayFrame(rect);
                statusHeight = rect.top;
                contentViewTop = win.findViewById(Window.ID_ANDROID_CONTENT).getTop();
                titleHeight = contentViewTop - statusHeight;
                Log.d(TAG, "titleHeight = " + titleHeight + " statusHeight = " + statusHeight + " contentViewTop = " + contentViewTop);
                layoutWidth = getResources().getDisplayMetrics().widthPixels;
                displayHeight = getResources().getDisplayMetrics().heightPixels;
                layoutHeight = displayHeight - titleHeight - statusHeight;
                Log.d(TAG, "layoutHeight = " + layoutHeight + " layoutWidth = " + layoutWidth);
                
                // Calculate Button sizes and positions
                buttonSize = (int)(33*layoutWidth/100);
                buttonOuterHorizontalPadding = (int)(10*layoutWidth/100);
            	buttonInnerHorizontalPadding = layoutWidth - 2*buttonSize - 2*buttonOuterHorizontalPadding;
            	buttonInnerVerticalPadding = buttonInnerHorizontalPadding;
            	buttonOuterVerticalPadding = (int) ((layoutHeight - 2*buttonSize - buttonInnerVerticalPadding)/1.5);

            	Log.d(TAG, "buttonSize = " + buttonSize + " buttonOuterHorizontalPadding = " + buttonOuterHorizontalPadding + " buttonInnerHorizontalPadding = " + buttonInnerHorizontalPadding
            			+ " buttonOuterVerticalPadding = " + buttonOuterVerticalPadding + " buttonInnerVerticalPadding = " + buttonInnerVerticalPadding);
            	
            	// Calculate SmallButton sizes and positions
            	smallButtonSize = (int) buttonSize/2;
            	smallButtonOuterHorizontalPadding = (int) buttonOuterHorizontalPadding-(smallButtonSize/2);
            	smallButtonOuterVerticalPadding = (int) buttonOuterVerticalPadding-(smallButtonSize/2);
            	smallButtonInnerHorizontalPadding = smallButtonInnerVerticalPadding = smallButtonSize;
            	
            	Log.d(TAG, "smallbuttonSize = " + smallButtonSize);
            	
                // set custom view dimensions
                button1.setHeight(buttonSize);
                button2.setHeight(buttonSize);
                button3.setHeight(buttonSize);
                button4.setHeight(buttonSize);
                button1.setWidth(buttonSize);
                button2.setWidth(buttonSize);
                button3.setWidth(buttonSize);
                button4.setWidth(buttonSize);
              
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,      
                        LayoutParams.WRAP_CONTENT
                );
                params1.setMargins(buttonOuterHorizontalPadding, buttonOuterVerticalPadding, 0, 0);
                button1.setLayoutParams(params1);
                
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,      
                        LayoutParams.WRAP_CONTENT
                );
                params2.setMargins(buttonInnerHorizontalPadding, buttonOuterVerticalPadding, 0, 0);
                button2.setLayoutParams(params2);
                
                LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,      
                        LayoutParams.WRAP_CONTENT
                );
                params3.setMargins(buttonOuterHorizontalPadding, buttonInnerVerticalPadding, 0, 0);
                button3.setLayoutParams(params3);
                
                LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,      
                        LayoutParams.WRAP_CONTENT
                );
                params4.setMargins(buttonInnerHorizontalPadding, buttonInnerVerticalPadding, 0, 0);
                button4.setLayoutParams(params4);

                //small buttons
                B1_1.setHeight(smallButtonSize);
                B1_2.setHeight(smallButtonSize);
                B1_3.setHeight(smallButtonSize);
                B1_4.setHeight(smallButtonSize);
                B2_1.setHeight(smallButtonSize);
                B2_2.setHeight(smallButtonSize);
                B2_3.setHeight(smallButtonSize);
                B2_4.setHeight(smallButtonSize);
                B1_1.setWidth(smallButtonSize);
                B1_2.setWidth(smallButtonSize);
                B1_3.setWidth(smallButtonSize);
                B1_4.setWidth(smallButtonSize);
                B2_1.setWidth(smallButtonSize);
                B2_2.setWidth(smallButtonSize);
                B2_3.setWidth(smallButtonSize);
                B2_4.setWidth(smallButtonSize);
                B3_1.setHeight(smallButtonSize);
                B3_2.setHeight(smallButtonSize);
                B3_3.setHeight(smallButtonSize);
                B3_4.setHeight(smallButtonSize);
                B4_1.setHeight(smallButtonSize);
                B4_2.setHeight(smallButtonSize);
                B4_3.setHeight(smallButtonSize);
                B4_4.setHeight(smallButtonSize);
                B3_1.setWidth(smallButtonSize);
                B3_2.setWidth(smallButtonSize);
                B3_3.setWidth(smallButtonSize);
                B3_4.setWidth(smallButtonSize);
                B4_1.setWidth(smallButtonSize);
                B4_2.setWidth(smallButtonSize);
                B4_3.setWidth(smallButtonSize);
                B4_4.setWidth(smallButtonSize);
                
                LinearLayout.LayoutParams params1_1 = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,      
                        LayoutParams.WRAP_CONTENT
                );
                params1_1.setMargins(smallButtonOuterHorizontalPadding, smallButtonOuterVerticalPadding, 0, 0);
                B1_1.setLayoutParams(params1_1);
                
                LinearLayout.LayoutParams params1_2 = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,      
                        LayoutParams.WRAP_CONTENT
                );
                params1_2.setMargins(smallButtonInnerHorizontalPadding, smallButtonOuterVerticalPadding, 0, 0);
                B1_2.setLayoutParams(params1_2);
                
                LinearLayout.LayoutParams params1_3 = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,      
                        LayoutParams.WRAP_CONTENT
                );
                params1_3.setMargins(smallButtonOuterHorizontalPadding, smallButtonInnerVerticalPadding, 0, 0);
                B1_3.setLayoutParams(params1_3);
                
                LinearLayout.LayoutParams params1_4 = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,      
                        LayoutParams.WRAP_CONTENT
                );
                params1_4.setMargins(smallButtonInnerHorizontalPadding, smallButtonInnerVerticalPadding, 0, 0);
                B1_4.setLayoutParams(params1_4);
                
                LinearLayout.LayoutParams params2_1 = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,      
                        LayoutParams.WRAP_CONTENT
                );
                params2_1.setMargins(smallButtonOuterHorizontalPadding+buttonSize+buttonInnerHorizontalPadding, smallButtonOuterVerticalPadding, 0, 0);
                B2_1.setLayoutParams(params2_1);
                
                LinearLayout.LayoutParams params2_2 = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,      
                        LayoutParams.WRAP_CONTENT
                );
                params2_2.setMargins(smallButtonInnerHorizontalPadding, smallButtonOuterVerticalPadding, 0, 0);
                B2_2.setLayoutParams(params2_2);
                
                LinearLayout.LayoutParams params2_3 = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,      
                        LayoutParams.WRAP_CONTENT
                );
                params2_3.setMargins(smallButtonOuterHorizontalPadding+buttonSize+buttonInnerHorizontalPadding, smallButtonInnerVerticalPadding, 0, 0);
                B2_3.setLayoutParams(params2_3);
                
                LinearLayout.LayoutParams params2_4 = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,      
                        LayoutParams.WRAP_CONTENT
                );
                params2_4.setMargins(smallButtonInnerHorizontalPadding, smallButtonInnerVerticalPadding, 0, 0);
                B2_4.setLayoutParams(params2_4);
                
                LinearLayout.LayoutParams params3_1 = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,      
                        LayoutParams.WRAP_CONTENT
                );
                params3_1.setMargins(smallButtonOuterHorizontalPadding, smallButtonOuterVerticalPadding+buttonSize+buttonInnerVerticalPadding, 0, 0);
                B3_1.setLayoutParams(params3_1);
                
                LinearLayout.LayoutParams params3_2 = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,      
                        LayoutParams.WRAP_CONTENT
                );
                params3_2.setMargins(smallButtonInnerHorizontalPadding, smallButtonOuterVerticalPadding+buttonSize+buttonInnerVerticalPadding, 0, 0);
                B3_2.setLayoutParams(params3_2);
                
                LinearLayout.LayoutParams params3_3 = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,      
                        LayoutParams.WRAP_CONTENT
                );
                params3_3.setMargins(smallButtonOuterHorizontalPadding, smallButtonInnerVerticalPadding, 0, 0);
                B3_3.setLayoutParams(params3_3);
                
                LinearLayout.LayoutParams params3_4 = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,      
                        LayoutParams.WRAP_CONTENT
                );
                params3_4.setMargins(smallButtonInnerHorizontalPadding, smallButtonInnerVerticalPadding, 0, 0);
                B3_4.setLayoutParams(params3_4);
                
                LinearLayout.LayoutParams params4_1 = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,      
                        LayoutParams.WRAP_CONTENT
                );
                params4_1.setMargins(smallButtonOuterHorizontalPadding+buttonSize+buttonInnerHorizontalPadding, smallButtonOuterVerticalPadding+buttonSize+buttonInnerVerticalPadding, 0, 0);
                B4_1.setLayoutParams(params4_1);
                
                LinearLayout.LayoutParams params4_2 = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,      
                        LayoutParams.WRAP_CONTENT
                );
                params4_2.setMargins(smallButtonInnerHorizontalPadding, smallButtonOuterVerticalPadding+buttonSize+buttonInnerVerticalPadding, 0, 0);
                B4_2.setLayoutParams(params4_2);
                
                LinearLayout.LayoutParams params4_3 = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,      
                        LayoutParams.WRAP_CONTENT
                );
                params4_3.setMargins(smallButtonOuterHorizontalPadding+buttonSize+buttonInnerHorizontalPadding, smallButtonInnerVerticalPadding, 0, 0);
                B4_3.setLayoutParams(params4_3);
                
                LinearLayout.LayoutParams params4_4 = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,      
                        LayoutParams.WRAP_CONTENT
                );
                params4_4.setMargins(smallButtonInnerHorizontalPadding, smallButtonInnerVerticalPadding, 0, 0);
                B4_4.setLayoutParams(params4_4);
              
            }
        });
            
        //die Views Suchen
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        
        B1_1 = (Button) findViewById(R.id.B1_1);
        B1_2 = (Button) findViewById(R.id.B1_2);
        B1_3 = (Button) findViewById(R.id.B1_3);
        B1_4 = (Button) findViewById(R.id.B1_4);
        B2_1 = (Button) findViewById(R.id.B2_1);
        B2_2 = (Button) findViewById(R.id.B2_2);
        B2_3 = (Button) findViewById(R.id.B2_3);
        B2_4 = (Button) findViewById(R.id.B2_4);
        B3_1 = (Button) findViewById(R.id.B3_1);
        B3_2 = (Button) findViewById(R.id.B3_2);
        B3_3 = (Button) findViewById(R.id.B3_3);
        B3_4 = (Button) findViewById(R.id.B3_4);
        B4_1 = (Button) findViewById(R.id.B4_1);
        B4_2 = (Button) findViewById(R.id.B4_2);
        B4_3 = (Button) findViewById(R.id.B4_3);
        B4_4 = (Button) findViewById(R.id.B4_4);
        
        deviceListSpinner = (Spinner) findViewById(R.id.deviceListSpinner);
        
        B1_1.setVisibility(4);
		B1_2.setVisibility(4);
		B1_3.setVisibility(4);
		B1_4.setVisibility(4);
        B2_1.setVisibility(4);
		B2_2.setVisibility(4);
		B2_3.setVisibility(4);
		B2_4.setVisibility(4);
		B3_1.setVisibility(4);
		B3_2.setVisibility(4);
		B3_3.setVisibility(4);
		B3_4.setVisibility(4);
		B4_1.setVisibility(4);
		B4_2.setVisibility(4);
		B4_3.setVisibility(4);
		B4_4.setVisibility(4);
        
        OnClickListener bigListener = new bigButtonListener();
        deviceListListener spinnerListener = new deviceListListener();
        changeModuleListener buttonChangeModuleListener = new changeModuleListener();
        
        button1.setOnClickListener(bigListener);
        button2.setOnClickListener(bigListener);
        button3.setOnClickListener(bigListener);
        button4.setOnClickListener(bigListener);
        
        B1_3.setOnClickListener(buttonChangeModuleListener);
        B2_3.setOnClickListener(buttonChangeModuleListener);
        B3_3.setOnClickListener(buttonChangeModuleListener);
        B4_3.setOnClickListener(buttonChangeModuleListener);
        
        deviceListSpinner.setOnItemSelectedListener(spinnerListener);
        
        //Create a new DeviceDiscoverer
        deviceDiscoverer = DeviceDiscoveryFactory.getInstance();
        
//         create a new PeriodicTaskPerformer who refreshes the Device List every 20 seonds
        deviceListUpdater = new PeriodicTaskPerformer(new Runnable() {
            public void run() {
               RefreshDeviceList();
            }
         });
//         start the Updater
        deviceListUpdater.startUpdates();
//        RefreshDeviceList();
        Log.d(TAG,"onCreated");
    }
    
    public void onResume(Bundle savedInstanceState) {
    	super.onResume();
    	deviceListUpdater.startUpdates();
//    	new RefreshDeviceList().execute();
//    	RefreshDeviceList();
    	Log.d(TAG,"onResumed");
    }
    
    public void onPause(Bundle savedInstanceState) {
//    	super.onPause();
    	deviceListUpdater.stopUpdates();
    	Log.d(TAG,"onPaused");
    }
    
    // make small buttons visible if field button is clicked, make small buttons invisible if clicked again or another
    // field is clicked
    private void switchButtonVisibility (int Field){
    	switch (Field){
    		case 1: if(B1_1.getVisibility()==0) B1_1.setVisibility(4); else B1_1.setVisibility(0);
    				if(B1_2.getVisibility()==0) B1_2.setVisibility(4); else B1_2.setVisibility(0);
    				if(B1_3.getVisibility()==0) B1_3.setVisibility(4); else B1_3.setVisibility(0);
    				if(B1_4.getVisibility()==0) B1_4.setVisibility(4); else B1_4.setVisibility(0);
    				B2_1.setVisibility(4);
    				B2_2.setVisibility(4);
    				B2_3.setVisibility(4);
    				B2_4.setVisibility(4);
    				B3_1.setVisibility(4);
    				B3_2.setVisibility(4);
    				B3_3.setVisibility(4);
    				B3_4.setVisibility(4);
    				B4_1.setVisibility(4);
    				B4_2.setVisibility(4);
    				B4_3.setVisibility(4);
    				B4_4.setVisibility(4);
    				break;
    				
    		case 2: if(B2_1.getVisibility()==0) B2_1.setVisibility(4); else B2_1.setVisibility(0);
					if(B2_2.getVisibility()==0) B2_2.setVisibility(4); else B2_2.setVisibility(0);
					if(B2_3.getVisibility()==0) B2_3.setVisibility(4); else B2_3.setVisibility(0);
					if(B2_4.getVisibility()==0) B2_4.setVisibility(4); else B2_4.setVisibility(0);
					B1_1.setVisibility(4);
					B1_2.setVisibility(4);
					B1_3.setVisibility(4);
					B1_4.setVisibility(4);
					B3_1.setVisibility(4);
					B3_2.setVisibility(4);
					B3_3.setVisibility(4);
					B3_4.setVisibility(4);
					B4_1.setVisibility(4);
					B4_2.setVisibility(4);
					B4_3.setVisibility(4);
					B4_4.setVisibility(4);
					break;
					
    		case 3: if(B3_1.getVisibility()==0) B3_1.setVisibility(4); else B3_1.setVisibility(0);
					if(B3_2.getVisibility()==0) B3_2.setVisibility(4); else B3_2.setVisibility(0);
					if(B3_3.getVisibility()==0) B3_3.setVisibility(4); else B3_3.setVisibility(0);
					if(B3_4.getVisibility()==0) B3_4.setVisibility(4); else B3_4.setVisibility(0);
					B1_1.setVisibility(4);
					B1_2.setVisibility(4);
					B1_3.setVisibility(4);
					B1_4.setVisibility(4);
					B2_1.setVisibility(4);
					B2_2.setVisibility(4);
					B2_3.setVisibility(4);
					B2_4.setVisibility(4);
					B4_1.setVisibility(4);
					B4_2.setVisibility(4);
					B4_3.setVisibility(4);
					B4_4.setVisibility(4);
					break;
					
    		case 4: if(B4_1.getVisibility()==0) B4_1.setVisibility(4); else B4_1.setVisibility(0);
    				if(B4_2.getVisibility()==0) B4_2.setVisibility(4); else B4_2.setVisibility(0);
    				if(B4_3.getVisibility()==0) B4_3.setVisibility(4); else B4_3.setVisibility(0);
					if(B4_4.getVisibility()==0) B4_4.setVisibility(4); else B4_4.setVisibility(0);
					B1_1.setVisibility(4);
					B1_2.setVisibility(4);
					B1_3.setVisibility(4);
					B1_4.setVisibility(4);
					B2_1.setVisibility(4);
					B2_2.setVisibility(4);
					B2_3.setVisibility(4);
					B2_4.setVisibility(4);
					B3_1.setVisibility(4);
					B3_2.setVisibility(4);
					B3_3.setVisibility(4);
					B3_4.setVisibility(4);
					break;
    	}
    }
    
    //method which uses DeviceDiscovery to refresh the deviceList
    public void RefreshDeviceList() {
//    	
    	
//		@Override
//		protected Integer doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			
			deviceList = deviceDiscoverer.getDiscoveredDevices();
	    	
//	    	ArrayAdapter<ALDevice> dataAdapter = new ArrayAdapter<ALDevice>(this,
//					android.R.layout.simple_spinner_dropdown_item,deviceList);
//	    	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//	    	deviceListSpinner.setAdapter(dataAdapter);
	    	
			
			if (deviceListSpinner.getAdapter() == null){
				ALDeviceAdapter dataAdapter = new ALDeviceAdapter(this,
				deviceList);
				dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				deviceListSpinner.setAdapter(dataAdapter);
			} else {
				((ALDeviceAdapter)deviceListSpinner.getAdapter()).refill(deviceList);
			}
//			return null;
			
//		}
    }
    
//    //Asynk task to discover devices, used in refreshDeviceList
//    private class DiscoverDevicesTask extends AsyncTask<Void,Integer,Integer> {
//		@Override
//		protected Integer doInBackground(Void... params) {
//		deviceList = deviceDiscoverer.getDiscoveredDevices();
//		return 0;
//		}	
//    }
//    
//    //onCreateOptionsMenu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//    	MenuInflater inflater = getMenuInflater();
//    	inflater.inflate(R.menu.menu, menu);
//    	return true;
//    }
    
    //onOptionsItemSelected
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch (item.getItemId()){
    		case R.id.itemPrefs:
    			startActivity(new Intent(this, PrefsActivity.class));
    			break;
    	}
    	return true;
    }
    
}