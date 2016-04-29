package xuezhou_cs680.easytravel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kun on 4/28/16.
 */
public class Adapter extends ArrayAdapter<String> {
    //declarations
    ArrayList<String> city= new ArrayList<String>();
    ArrayList<String> date= new ArrayList<String>();
    ArrayList<String> amt= new ArrayList<String>();
    Context c;
    LayoutInflater inflater;

    public Adapter(Context context,ArrayList<String> city,ArrayList<String> date,ArrayList<String> amt){
        super(context,R.layout.item_layout_expk,city);

        this.c=context;
        this.city=city;
        this.date=date;
        this.amt=amt;
    }

    //inner class shall hold our views for each row
    public class ViewHolder{
        TextView city;
        TextView time;
        TextView amt;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_layout_expk, null);
        }
        //our viewholder object
        final ViewHolder holder = new ViewHolder();
        //initialize our views
        holder.city = (TextView) convertView.findViewById(R.id.where);
        holder.time = (TextView) convertView.findViewById(R.id.when);
        holder.amt = (TextView) convertView.findViewById(R.id.amount);

        //assign data
        holder.city.setText(city.get(position));
        holder.time.setText(date.get(position));
        holder.amt.setText(amt.get(position));

        return convertView;
    }
}


