package com.sneh92.sneh92;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by pooja on 18/6/16.
 */
public class CustomAdapter extends BaseAdapter{
    Context context;
    String[] result;
    private static LayoutInflater inflater=null;
    public CustomAdapter(Context con, String[] list) {
        context=con;
        result=list;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public  class Holder
    {
        TextView fname;
        TextView mname;
        TextView lname;

    }
    @Override
      public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View view=inflater.inflate(R.layout.custom_list,null);
        holder.fname=(TextView) view.findViewById(R.id.fname);
        holder.mname=(TextView) view.findViewById(R.id.mname);
        holder.lname=(TextView)view.findViewById(R.id.lname);
       String s[]=result[position].split(";");
        holder.fname.setText(s[0]);
        holder.mname.setText(s[0]);  //TODO
        holder.lname.setText(s[0]);
        return view;
    }
}
