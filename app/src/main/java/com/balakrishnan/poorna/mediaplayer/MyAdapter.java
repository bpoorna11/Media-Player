package com.balakrishnan.poorna.mediaplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;



import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Song> {
    private Context context;
    private ArrayList<Song> list;
    public MyAdapter(Context context,ArrayList<Song> list){
        super(context, R.layout.mylayout,list);
        this.context=context;
        this.list=list;

    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView =inflater.inflate(R.layout.mylayout,parent,false);
        TextView textView=(TextView)rowView.findViewById(R.id.textView4);
        textView.setText(list.get(position).getName());

        return rowView;
    }
}
