package ch.k24.auroraprime.quorg;

import java.util.List;

import ch.k42.auroraprime.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A custom Array Adapter for the List View
 * to show the Quorgs
 * 
 * @Author Philipp Bšsch
 */
public class QuorgAdapter extends ArrayAdapter<Quorg>{

    Context context; 
    int layoutResourceId;    
    List<Quorg> data = null;
    
    public QuorgAdapter(Context context, int layoutResourceId, List<Quorg> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        WeatherHolder holder = null;
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new WeatherHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            
            row.setTag(holder);
        }
        else
        {
            holder = (WeatherHolder)row.getTag();
        }
        
        Quorg quorg = data.get(position);
        holder.txtTitle.setText(quorg.getName());
        holder.imgIcon.setImageResource(quorg.getImage());
        
        return row;
    }
    
    static class WeatherHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
    }
}
