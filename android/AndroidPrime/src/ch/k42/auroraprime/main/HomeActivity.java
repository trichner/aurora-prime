package ch.k42.auroraprime.main;

import ch.k42.auroraprime.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
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
	
	private class Button_Listener implements OnClickListener {

		public void onClick(View v) {
			// get id of button pressed, send intent and start ListActivity
			Intent i=null;
			switch (v.getId())
			{
			case R.id.button1: i = new Intent(HomeActivity.this, ListActivity.class);
							   i.putExtra("sourceButton", "button1"); break;
			case R.id.button2: i = new Intent(HomeActivity.this, ListActivity.class);
			   				   i.putExtra("sourceButton", "button2"); break;
			case R.id.button3: i = new Intent(HomeActivity.this, ListActivity.class);
							   i.putExtra("sourceButton", "button3"); break;
			case R.id.button4: i = new Intent(HomeActivity.this, ListActivity.class);
							   i.putExtra("sourceButton", "button4"); break;
			}
			Log.d(TAG, "onClicked");
			startActivity(i);
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
	//LinearLayout buttonRow1;
	//LinearLayout buttonRow2;
	
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
            	buttonOuterVerticalPadding = (int) ((layoutHeight - 2*buttonSize - buttonInnerVerticalPadding)/2);

            	Log.d(TAG, "buttonSize = " + buttonSize + " buttonOuterHorizontalPadding = " + buttonOuterHorizontalPadding + " buttonInnerHorizontalPadding = " + buttonInnerHorizontalPadding
            			+ " buttonOuterVerticalPadding = " + buttonOuterVerticalPadding + " buttonInnerVerticalPadding = " + buttonInnerVerticalPadding);
            	
            	// Calculate SmallButton sizes and positions
            	smallButtonSize = (int) buttonSize/2;
            	smallButtonOuterHorizontalPadding = (int) buttonOuterHorizontalPadding-(smallButtonSize/2);
            	smallButtonOuterVerticalPadding = (int) buttonOuterVerticalPadding-(smallButtonSize/2);
            	smallButtonInnerHorizontalPadding = smallButtonInnerVerticalPadding = smallButtonSize;
            	
            	Log.d(TAG, "smallbuttonSize = " + smallButtonSize + " smallButtonOuterHorizontalPadding = " + smallButtonOuterHorizontalPadding + " smallButtonOuterVerticalPadding " + smallButtonOuterVerticalPadding);
            	
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
        
        
        OnClickListener listener = new Button_Listener();
        
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        
       
    }

}