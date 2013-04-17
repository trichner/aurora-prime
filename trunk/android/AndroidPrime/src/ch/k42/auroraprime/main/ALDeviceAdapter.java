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

		private final Activity activity;
		int layoutResourceId;
		List<ALDevice> data;
		
		public ALDeviceAdapter(Activity activity, List<ALDevice> data) {
			 super(activity, R.layout.device_spinner_item_row, data);
			 this.activity = activity;
//			 this.context = context;
			 this.data = data;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View row = convertView;
			DeviceListElementView txView = null;
			
			if (row == null)
			{
				LayoutInflater inflater = activity.getLayoutInflater();
				row = inflater.inflate(R.layout.device_spinner_item_row, null);
			
			
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
		
		@Override
		public View getDropDownView(int position, View convertView,
				ViewGroup parent) {
			View row = convertView;
			DeviceListElementView txView = null;
			
			if (row == null)
			{
				LayoutInflater inflater = activity.getLayoutInflater();
				row = inflater.inflate(R.layout.device_spinner_item_row, null);
			
			
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

		public void refill(List<ALDevice> newData){
			this.clear();
			addAll(newData);
			notifyDataSetChanged();
		}
		
		protected static class DeviceListElementView {
			protected TextView text;
		}
		
	
}
