package ch.k42.auroraprime.main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import ch.k42.auroraprime.R;
import ch.k42.auroraprime.dto.DeviceState;
import ch.k42.auroraprime.dto.QuorgSettings;
import ch.k42.auroraprime.dto.Request;
import ch.k42.auroraprime.net.*;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;
import android.graphics.Rect;


/**
 * Home Activity Screen of the AndroidPrime Application
 * 
 * 
 * @Author Philipp B�sch
 */
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
	
	int swapMode = 0;
	
	Intent i;
	
	IDeviceDiscovery deviceDiscoverer;
	PeriodicTaskPerformer deviceListUpdater;
	
	List<ALDevice> deviceList = new ArrayList<ALDevice>();
	ALDevice selectedDevice;
	
	ImageView[] fieldButtons = new ImageView[4];
	ImageView[][] optionButtons = new ImageView[4][4];
	Spinner deviceListSpinner;
	
	
	private static final String TAG = "HomeActivity";
	
	/**
	 * Listener for the four big screen selector buttons
	 * handles smaller buttons visibility
	 * 
	 * 
	 */
	private class bigButtonListener implements OnClickListener {

		public void onClick(View v) {
			AndroidPrimeApplication ourApplication = ((AndroidPrimeApplication) getApplication());
			
			switch (v.getId())
			{
			case R.id.button1: ourApplication.setSelectedField(0);
				break;
			case R.id.button2: ourApplication.setSelectedField(1);
				break;
			case R.id.button3: ourApplication.setSelectedField(2);
				break;
			case R.id.button4: ourApplication.setSelectedField(3);
				break;
			}	
			
			if (swapMode==0){
				switchButtonVisibility(ourApplication.getSelectedField()+1);
			} else {
				QuorgSettings settings1 = new QuorgSettings();
				settings1.setQuorg(ourApplication.getQuorgFields()[ourApplication.getSelectedField()].getActiveQuorg().getQuorgID());
				settings1.setMatrixID(ourApplication.getQuorgFields()[swapMode-1].getFieldID());
				Request request1 = new Request(Request.Command.SETQUORG ,settings1);
				
				QuorgSettings settings2 = new QuorgSettings();
				settings2.setQuorg(ourApplication.getQuorgFields()[swapMode-1].getActiveQuorg().getQuorgID());
				settings2.setMatrixID(ourApplication.getQuorgFields()[ourApplication.getSelectedField()].getFieldID());
				Request request2 = new Request(Request.Command.SETQUORG ,settings2);
				
				SendRequest(request1);
				SendRequest(request2);
				
				swapMode=0;
				
			}
			Log.d(TAG, "onBigButtonClicked");
					}	
	}
	
	
	/**
	 * Listener for the Module change button, opens the 
	 * activity with the quorg list
	 * 
	 * 
	 */
	private class swapModuleListener implements OnClickListener{

		public void onClick(View v) {
			switch (v.getId())
			{
			case R.id.B1_1: 	swapMode = 1;
								break;
			case R.id.B2_1: 	switchButtonVisibility(2);
								swapMode = 2;
								break;
			case R.id.B3_1: 	switchButtonVisibility(3);
								swapMode = 3;
								break;
			case R.id.B4_1:		switchButtonVisibility(4);
								swapMode = 4;
								break;
			}
			Log.d(TAG, "swapModuleClicked");
		}	
	}
	
	/**
	 * Listener for the Module swapping button
	 * 
	 * 
	 */
	private class changeModuleListener implements OnClickListener{

		public void onClick(View v) {
			AndroidPrimeApplication ourApplication = ((AndroidPrimeApplication) getApplication());
			switch (v.getId())
			{
			case R.id.B1_4: 	switchButtonVisibility(1);
								i = new Intent(HomeActivity.this, ListActivity.class);
								ourApplication.setSelectedField(0);
								break;
			case R.id.B2_4: 	switchButtonVisibility(2);
								i = new Intent(HomeActivity.this, ListActivity.class);
								ourApplication.setSelectedField(1);
								break;
			case R.id.B3_4: 	switchButtonVisibility(3);
								i = new Intent(HomeActivity.this, ListActivity.class); 
								ourApplication.setSelectedField(2);
								break;
			case R.id.B4_4:		switchButtonVisibility(4);
								i = new Intent(HomeActivity.this, ListActivity.class);
								ourApplication.setSelectedField(3);
								break;
			}
			Log.d(TAG, "swapModuleClicked");
			startActivity(i);
		}	
	}
	
	
	/**
	 * listener for the elements on the deviceList drop-down menu 
	 * will try to connect to the selected device in list
	 * 
	 * 
	 */
	private class deviceListListener implements OnItemSelectedListener{

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			
			ALDevice selectedDevice = ((ALDevice) parent.getItemAtPosition(pos));
			
			new connectTask().execute(selectedDevice);

		}

		public void onNothingSelected(AdapterView<?> arg0) {
		
			
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
              
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                        buttonSize,      
                        buttonSize
                );
                params1.setMargins(buttonOuterHorizontalPadding, buttonOuterVerticalPadding, 0, 0);
                fieldButtons[0].setLayoutParams(params1);
                
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                		buttonSize,      
                        buttonSize
                );
                params2.setMargins(buttonInnerHorizontalPadding, buttonOuterVerticalPadding, 0, 0);
                fieldButtons[1].setLayoutParams(params2);
                
                LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                		buttonSize,      
                        buttonSize
                );
                params3.setMargins(buttonOuterHorizontalPadding, buttonInnerVerticalPadding, 0, 0);
                fieldButtons[2].setLayoutParams(params3);
                
                LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(
                		buttonSize,      
                        buttonSize
                );
                params4.setMargins(buttonInnerHorizontalPadding, buttonInnerVerticalPadding, 0, 0);
                fieldButtons[3].setLayoutParams(params4);

                //small buttons
                
                LinearLayout.LayoutParams params1_1 = new LinearLayout.LayoutParams(
                        smallButtonSize,      
                        smallButtonSize
                );
                params1_1.setMargins(smallButtonOuterHorizontalPadding, smallButtonOuterVerticalPadding, 0, 0);
                optionButtons[0][0].setLayoutParams(params1_1);
                
                LinearLayout.LayoutParams params1_2 = new LinearLayout.LayoutParams(
                		smallButtonSize,      
                        smallButtonSize
                );
                params1_2.setMargins(smallButtonInnerHorizontalPadding, smallButtonOuterVerticalPadding, 0, 0);
                optionButtons[0][1].setLayoutParams(params1_2);
                
                LinearLayout.LayoutParams params1_3 = new LinearLayout.LayoutParams(
                		smallButtonSize,      
                        smallButtonSize
                );
                params1_3.setMargins(smallButtonOuterHorizontalPadding, smallButtonInnerVerticalPadding, 0, 0);
                optionButtons[0][2].setLayoutParams(params1_3);
                
                LinearLayout.LayoutParams params1_4 = new LinearLayout.LayoutParams(
                		smallButtonSize,      
                        smallButtonSize
                );
                params1_4.setMargins(smallButtonInnerHorizontalPadding, smallButtonInnerVerticalPadding, 0, 0);
                optionButtons[0][3].setLayoutParams(params1_4);
                
                LinearLayout.LayoutParams params2_1 = new LinearLayout.LayoutParams(
                		smallButtonSize,      
                        smallButtonSize
                );
                params2_1.setMargins(smallButtonOuterHorizontalPadding+buttonSize+buttonInnerHorizontalPadding, smallButtonOuterVerticalPadding, 0, 0);
                optionButtons[1][0].setLayoutParams(params2_1);
                
                LinearLayout.LayoutParams params2_2 = new LinearLayout.LayoutParams(
                		smallButtonSize,      
                        smallButtonSize
                );
                params2_2.setMargins(smallButtonInnerHorizontalPadding, smallButtonOuterVerticalPadding, 0, 0);
                optionButtons[1][1].setLayoutParams(params2_2);
                
                LinearLayout.LayoutParams params2_3 = new LinearLayout.LayoutParams(
                		smallButtonSize,      
                        smallButtonSize
                );
                params2_3.setMargins(smallButtonOuterHorizontalPadding+buttonSize+buttonInnerHorizontalPadding, smallButtonInnerVerticalPadding, 0, 0);
                optionButtons[1][2].setLayoutParams(params2_3);
                
                LinearLayout.LayoutParams params2_4 = new LinearLayout.LayoutParams(
                		smallButtonSize,      
                        smallButtonSize
                );
                params2_4.setMargins(smallButtonInnerHorizontalPadding, smallButtonInnerVerticalPadding, 0, 0);
                optionButtons[1][3].setLayoutParams(params2_4);
                
                LinearLayout.LayoutParams params3_1 = new LinearLayout.LayoutParams(
                		smallButtonSize,      
                        smallButtonSize
                );
                params3_1.setMargins(smallButtonOuterHorizontalPadding, smallButtonOuterVerticalPadding+buttonSize+buttonInnerVerticalPadding, 0, 0);
                optionButtons[2][0].setLayoutParams(params3_1);
                
                LinearLayout.LayoutParams params3_2 = new LinearLayout.LayoutParams(
                		smallButtonSize,      
                        smallButtonSize
                );
                params3_2.setMargins(smallButtonInnerHorizontalPadding, smallButtonOuterVerticalPadding+buttonSize+buttonInnerVerticalPadding, 0, 0);
                optionButtons[2][1].setLayoutParams(params3_2);
                
                LinearLayout.LayoutParams params3_3 = new LinearLayout.LayoutParams(
                		smallButtonSize,      
                        smallButtonSize
                );
                params3_3.setMargins(smallButtonOuterHorizontalPadding, smallButtonInnerVerticalPadding, 0, 0);
                optionButtons[2][2].setLayoutParams(params3_3);
                
                LinearLayout.LayoutParams params3_4 = new LinearLayout.LayoutParams(
                		smallButtonSize,      
                        smallButtonSize
                );
                params3_4.setMargins(smallButtonInnerHorizontalPadding, smallButtonInnerVerticalPadding, 0, 0);
                optionButtons[2][3].setLayoutParams(params3_4);
                
                LinearLayout.LayoutParams params4_1 = new LinearLayout.LayoutParams(
                		smallButtonSize,      
                        smallButtonSize
                );
                params4_1.setMargins(smallButtonOuterHorizontalPadding+buttonSize+buttonInnerHorizontalPadding, smallButtonOuterVerticalPadding+buttonSize+buttonInnerVerticalPadding, 0, 0);
                optionButtons[3][0].setLayoutParams(params4_1);
                
                LinearLayout.LayoutParams params4_2 = new LinearLayout.LayoutParams(
                		smallButtonSize,      
                        smallButtonSize
                );
                params4_2.setMargins(smallButtonInnerHorizontalPadding, smallButtonOuterVerticalPadding+buttonSize+buttonInnerVerticalPadding, 0, 0);
                optionButtons[3][1].setLayoutParams(params4_2);
                
                LinearLayout.LayoutParams params4_3 = new LinearLayout.LayoutParams(
                		smallButtonSize,      
                        smallButtonSize
                );
                params4_3.setMargins(smallButtonOuterHorizontalPadding+buttonSize+buttonInnerHorizontalPadding, smallButtonInnerVerticalPadding, 0, 0);
                optionButtons[3][2].setLayoutParams(params4_3);
                
                LinearLayout.LayoutParams params4_4 = new LinearLayout.LayoutParams(
                		smallButtonSize,      
                        smallButtonSize
                );
                params4_4.setMargins(smallButtonInnerHorizontalPadding, smallButtonInnerVerticalPadding, 0, 0);
                optionButtons[3][3].setLayoutParams(params4_4);
              
            }
        });
            
        //die Views Suchen
        fieldButtons[0] = (ImageView) findViewById(R.id.button1);
        fieldButtons[1] = (ImageView) findViewById(R.id.button2);
        fieldButtons[2] = (ImageView) findViewById(R.id.button3);
        fieldButtons[3] = (ImageView) findViewById(R.id.button4);
        
        optionButtons[0][0] = (ImageView) findViewById(R.id.B1_1);
        optionButtons[0][1] = (ImageView) findViewById(R.id.B1_2);
        optionButtons[0][2] = (ImageView) findViewById(R.id.B1_3);
        optionButtons[0][3] = (ImageView) findViewById(R.id.B1_4);
        optionButtons[1][0] = (ImageView) findViewById(R.id.B2_1);
        optionButtons[1][1] = (ImageView) findViewById(R.id.B2_2);
        optionButtons[1][2] = (ImageView) findViewById(R.id.B2_3);
        optionButtons[1][3] = (ImageView) findViewById(R.id.B2_4);
        optionButtons[2][0] = (ImageView) findViewById(R.id.B3_1);
        optionButtons[2][1] = (ImageView) findViewById(R.id.B3_2);
        optionButtons[2][2] = (ImageView) findViewById(R.id.B3_3);
        optionButtons[2][3] = (ImageView) findViewById(R.id.B3_4);
        optionButtons[3][0] = (ImageView) findViewById(R.id.B4_1);
        optionButtons[3][1] = (ImageView) findViewById(R.id.B4_2);
        optionButtons[3][2] = (ImageView) findViewById(R.id.B4_3);
        optionButtons[3][3] = (ImageView) findViewById(R.id.B4_4);
        
        deviceListSpinner = (Spinner) findViewById(R.id.deviceListSpinner);
        
        for (int i = 0; i<4;i++){
        	for (int j = 0; j<4;j++){
        		optionButtons[i][j].setVisibility(4);
        	}
        }
        
        OnClickListener bigListener = new bigButtonListener();
        deviceListListener spinnerListener = new deviceListListener();
        changeModuleListener buttonChangeModuleListener = new changeModuleListener();
        swapModuleListener buttonSwapModuleListener = new swapModuleListener();
        
        for (int i =0; i<4; i++){
    		fieldButtons[i].setOnClickListener(bigListener);
    		optionButtons[i][3].setOnClickListener(buttonChangeModuleListener);
    		optionButtons[i][0].setOnClickListener(buttonSwapModuleListener);
    	}
                
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
//        deviceListUpdater.startUpdates();
//        RefreshDeviceList();
        Log.d(TAG,"onCreated");
        
        RefreshButtonImages();
    }
    

    /**
	 * Called on Resume
	 * 
	 * 
	 * 
	 */
    @Override
    public void onResume() {
    	super.onResume();
    	deviceListUpdater.startUpdates();
    	RefreshButtonImages();
//    	new RefreshDeviceList().execute();
//    	RefreshDeviceList();
    	Log.d(TAG,"onResumed");
    }
    
    
    /**
	 * Calles on Pause
	 * 
	 * 
	 * 
	 */
    @Override
    public void onPause() {
    	super.onPause();
    	deviceListUpdater.stopUpdates();
    	Log.d(TAG,"onPaused");
    }
    
    
    /**
	 * make small buttons visible if field button is
	 * clicked, make small buttons invisible if clicked again or another
	 * or another field is clicked
	 * 
	 */
    private void switchButtonVisibility (int Field){
    	switch (Field){
    		case 1: if(optionButtons[0][0].getVisibility()==0) optionButtons[0][0].setVisibility(4); else optionButtons[0][0].setVisibility(0);
    				if(optionButtons[0][1].getVisibility()==0) optionButtons[0][1].setVisibility(4); else optionButtons[0][1].setVisibility(0);
    				if(optionButtons[0][2].getVisibility()==0) optionButtons[0][2].setVisibility(4); else optionButtons[0][2].setVisibility(0);
    				if(optionButtons[0][3].getVisibility()==0) optionButtons[0][3].setVisibility(4); else optionButtons[0][3].setVisibility(0);
    				for (int i = 1; i<4;i++){
			        	for (int j = 0; j<4;j++){
			        		optionButtons[i][j].setVisibility(4);
			        	}
			        }
    				break;
    				
    		case 2: if(optionButtons[1][0].getVisibility()==0) optionButtons[1][0].setVisibility(4); else optionButtons[1][0].setVisibility(0);
					if(optionButtons[1][1].getVisibility()==0) optionButtons[1][1].setVisibility(4); else optionButtons[1][1].setVisibility(0);
					if(optionButtons[1][2].getVisibility()==0) optionButtons[1][2].setVisibility(4); else optionButtons[1][2].setVisibility(0);
					if(optionButtons[1][3].getVisibility()==0) optionButtons[1][3].setVisibility(4); else optionButtons[1][3].setVisibility(0);
					for (int i = 0; i<4;i++){
		        		optionButtons[0][i].setVisibility(4);
					}
					for (int i = 2; i<4;i++){
			        	for (int j = 0; j<4;j++){
			        		optionButtons[i][j].setVisibility(4);
			        	}
			        }
					break;
					
    		case 3: if(optionButtons[2][0].getVisibility()==0) optionButtons[2][0].setVisibility(4); else optionButtons[2][0].setVisibility(0);
					if(optionButtons[2][1].getVisibility()==0) optionButtons[2][1].setVisibility(4); else optionButtons[2][1].setVisibility(0);
					if(optionButtons[2][2].getVisibility()==0) optionButtons[2][2].setVisibility(4); else optionButtons[2][2].setVisibility(0);
					if(optionButtons[2][3].getVisibility()==0) optionButtons[2][3].setVisibility(4); else optionButtons[2][3].setVisibility(0);
					for (int i = 0; i<2;i++){
			        	for (int j = 0; j<4;j++){
			        		optionButtons[i][j].setVisibility(4);
			        	}
			        }
					for (int i = 0; i<4;i++){
			        		optionButtons[3][i].setVisibility(4);
			        }
					break;
					
    		case 4: if(optionButtons[3][0].getVisibility()==0) optionButtons[3][0].setVisibility(4); else optionButtons[3][0].setVisibility(0);
    				if(optionButtons[3][1].getVisibility()==0) optionButtons[3][1].setVisibility(4); else optionButtons[3][1].setVisibility(0);
    				if(optionButtons[3][2].getVisibility()==0) optionButtons[3][2].setVisibility(4); else optionButtons[3][2].setVisibility(0);
    				if(optionButtons[3][3].getVisibility()==0) optionButtons[3][3].setVisibility(4); else optionButtons[3][3].setVisibility(0);
					for (int i = 0; i<3;i++){
			        	for (int j = 0; j<4;j++){
			        		optionButtons[i][j].setVisibility(4);
			        	}
			        }	
					break;
    	}
    }
    
    
    /**
	 * Refresh images on Buttons
	 * 
	 * 
	 * 
	 */
    public void RefreshButtonImages() {
    	
    	AndroidPrimeApplication ourApplication = ((AndroidPrimeApplication) getApplication());
    	for (int i=0;i<4;i++){
    		fieldButtons[i].setImageResource(ourApplication.getQuorgFields()[i].getActiveQuorg().getImage());
    		optionButtons[i][0].setImageResource(R.drawable.ic_switch);
    		optionButtons[i][1].setImageResource(R.drawable.ic_on);
    		optionButtons[i][2].setImageResource(R.drawable.ic_config);
    		optionButtons[i][3].setImageResource(R.drawable.ic_change);
    		
    	}
    }
    
    
    /**
	 * method which uses DeviceDiscovery to refresh the deviceList
	 * 
	 * 
	 * 
	 */
    public void RefreshDeviceList() {
			
		deviceList = deviceDiscoverer.getDiscoveredDevices();
	    	
		if (deviceListSpinner.getAdapter() == null){
			ALDeviceAdapter dataAdapter = new ALDeviceAdapter(this,
			deviceList);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			deviceListSpinner.setAdapter(dataAdapter);
		} else {
			((ALDeviceAdapter)deviceListSpinner.getAdapter()).refill(deviceList);
		}
    }

   
    /**
	 * Async Task class that tries to connect
	 * to a provided ALDevice
	 * 
	 * 
	 */
    private class connectTask extends AsyncTask<ALDevice, Integer, Integer> {
    	protected Integer doInBackground(ALDevice... params){
    		
    		selectedDevice = params[0];
    		
    		if(selectedDevice==null) return 0;
    		
    		//adress for net tester
			InetSocketAddress inetAddress;
			inetAddress = new InetSocketAddress("129.132.149.148", 1337);
			ALDevice netTesterDevice = new ALDevice(inetAddress, "NetTester");
			//
			
			AndroidPrimeApplication ourApplication = ((AndroidPrimeApplication) getApplication());
			
			if (ourApplication.getConnectClient().isConnected()) {
				ourApplication.getConnectClient().disconnect();
				ourApplication.newClient();
				Log.d(TAG, "new Client Created");
			};
				try {
//					ourApplication.connectClient.connect(netTesterDevice.getAddress());
					
					//test client
//					IClient client = ourApplication.connectClient;
					Log.d(TAG, "Connection Attempt to " + selectedDevice.getAddress());
					ourApplication.getConnectClient().connect(selectedDevice.getAddress());
					
					//test send
					String s = (String) ourApplication.getConnectClient().sendRequest("lollll");
					Log.d(TAG, "request sent, response: " + s);
					
				} catch (IOException e) {

					Log.d(TAG, "client Connection Failed!");
				}
    		
			return 0;
    	}

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
    
    
    //onCreateOptionsMenu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//    	MenuInflater inflater = getMenuInflater();
//    	inflater.inflate(R.menu.menu, menu);
//    	return true;
//    }
    
    //onOptionsItemSelected
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//    	switch (item.getItemId()){
//    		case R.id.itemPrefs:
//    			startActivity(new Intent(this, PrefsActivity.class));
//    			break;
//    	}
//    	return true;
//    }
    
}