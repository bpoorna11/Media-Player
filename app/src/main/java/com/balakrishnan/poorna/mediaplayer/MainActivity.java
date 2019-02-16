package com.balakrishnan.poorna.mediaplayer;

import android.annotation.TargetApi;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
MediaPlayer player ;
TextView tv;
ImageView im;
Button b1 ,b2,b3,b4;
private boolean playPause=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       player =MediaPlayer.create(getApplicationContext(),R.raw.talk);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.textView);
        im=findViewById(R.id.imageView);
        b1=findViewById(R.id.button);//play
        b1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(playPause){
                    pauseit();
                }else{
                    startit();
                }

            }
        });
        b2=findViewById(R.id.button2);

        b3=findViewById(R.id.button3);//stop
        b3.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                stopit();
            }
        });
        b4=findViewById(R.id.button4);
        b4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                stopit();
                Intent in=new Intent(getApplicationContext(),SecondActivity1.class);
                startActivity(in);

            }
        });

    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void stopit(){
       if(player.isPlaying())
            player.stop();
        b1.setBackground(getApplicationContext().getDrawable(R.drawable.play));
        playPause=false;
        player = MediaPlayer.create(getApplicationContext(), R.raw.talk);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void startit() {
        player.start();
        playPause=true;
        b1.setBackground(getApplicationContext().getDrawable(R.drawable.pause));
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void pauseit(){
        player.pause();
        playPause=false;
        b1.setBackground(getApplicationContext().getDrawable(R.drawable.play));
    }
    @Override
    public void onClick(View v) {

    }

}
