package ch.k42.auroraprime.main;

import java.util.List;

import ch.k42.auroraprime.R;
import ch.k42.auroraprime.net.ALDevice;
//import android.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ALDeviceAdapter extends ArrayAdapter<ALDevice>{

		Context context;
		int layoutResourceId;
		List<ALDevice> data = null;
		
		public ALDeviceAdapter(Context context, int layoutResourceId, List<ALDevice> data) {
			 super(context, layoutResourceId, data);
			 this.layoutResourceId = layoutResourceId;
			 this.context = context;
			 this.data = data;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View row = convertView;
			DeviceListElementView txView = null;
			
			if (row == null)
			{
				LayoutInflater inflater = ((Activity)context).getLayoutInflater();
				row = inflater.inflate(layoutResourceId, null);
			
			
				txView = new DeviceListElementView();
				txView.text = (TextView) row.findViewById(R.id.device_text);
			
				row.setTag(txView);
			} else {
				txView = (DeviceListElementView) row.getTag();
			}
			
			ALDevice currentDevice = data.get(position);
			txView.text.setText(currentDevice.getName());
			return row;
			
		}
		
		protected static class DeviceListElementView {
			protected TextView text;
		}
		
	
}
