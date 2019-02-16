package com.balakrishnan.poorna.mediaplayer;

import android.annotation.TargetApi;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SecondActivity1 extends AppCompatActivity {
TextView tv;
ListView lv;
Button b5,b6,b7;
ArrayList<Song> songs=new ArrayList<>();
MyAdapter adapter;
    public static MediaPlayer player = new MediaPlayer();
    private boolean playPause=false;
    private int seekForwardTime = 5 * 1000; // default 5 second
    private int seekBackwardTime = 5 * 1000; // default 5 second
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second1);
        tv=findViewById(R.id.textView);
        b6=findViewById(R.id.button6);//play
        b6.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if(playPause){
                    pauseit();
                }else{
                    startit();
                }


            }
        });
        b5=findViewById(R.id.button5);//pause
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = player.getCurrentPosition();
                // check if seekBackward time is greater than 0 sec
                if(currentPosition - seekBackwardTime >= 0){
                    // forward song
                    player.seekTo(currentPosition - seekBackwardTime);
                }else{
                    // backward to starting position
                    player.seekTo(0);
                }
            }
        });
        b7=findViewById(R.id.button7);//stop
        b7.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                stopit();

            }
        });
        lv=findViewById(R.id.list);
        File folder=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File[] files=folder.listFiles();
        for(File file:files){

            if(file.getName().endsWith(".mp3")){
                Song sg=new Song(file.getName());
                songs.add(sg);
              //  adapter=new MyAdapter(getApplicationContext(),songs);
               // lv.setAdapter(adapter);
            }
        }
       Collections.sort(songs, new Comparator<Song>() {
                   @Override
                   public int compare(Song o1, Song o2) {
                       return o1.name.compareTo(o2.name);
                   }
               });
               adapter = new MyAdapter(getApplicationContext(), songs);
               lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song ss=songs.get(position);
                String nm=ss.name;
                  String path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+nm;
                  File f = new File(path);

                if(f.exists()==true) {


                    Log.i("MainActivity", path);

                    try {
                        if(player.isPlaying() || player!=null){
                            player.stop();
                            player.reset();
                            playPause=false;
                            b6.setBackground(getApplicationContext().getDrawable(R.drawable.play));
                        }
                        player.setDataSource(path);
                        player.prepare();
                        player.start();
                        playPause=true;
                        b6.setBackground(getApplicationContext().getDrawable(R.drawable.pause));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"not exist "+ path, Toast.LENGTH_LONG).show();
                }
                int currentindex=position;
            }

        });
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void startit() {
        player.start();
        playPause=true;
        b6.setBackground(getApplicationContext().getDrawable(R.drawable.pause));
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void pauseit(){
        player.pause();
        playPause=false;
        b6.setBackground(getApplicationContext().getDrawable(R.drawable.play));
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void stopit(){
        if(player.isPlaying())
            player.stop();
        b6.setBackground(getApplicationContext().getDrawable(R.drawable.play));
        playPause=false;
        player.reset();
       // player = new MediaPlayer();
    }

}
