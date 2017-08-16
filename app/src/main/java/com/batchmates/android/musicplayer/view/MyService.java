package com.batchmates.android.musicplayer.view;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

import java.io.IOException;

public class MyService extends Service {


    private static final String TAG = "start service";
    private MediaPlayer mediaPlayer;
    private MyReciever reciever;
    private IntentFilter intentFilter;
    private Context myContext;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer=new MediaPlayer();
        reciever=new MyReciever();
        intentFilter=new IntentFilter();
        intentFilter.addAction("STOP");
        intentFilter.addAction("START");
        intentFilter.addAction("CHANGE");
        registerReceiver(reciever,intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        unregisterReceiver(reciever);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        myContext=this;
        try {
            mediaPlayer.setDataSource(this, Uri.parse(intent.getStringExtra("PATH")));
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onStartCommand: start service");
        return super.onStartCommand(intent, flags, startId);
    }


    public class MyReciever extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: ");

            switch (intent.getAction())
            {
                case "STOP":
                    Log.d(TAG, "onReceive: STOP");
                    mediaPlayer.pause();
                    break;

                case "START":
                    Log.d(TAG, "onReceive: START");
                    mediaPlayer.start();
                    break;

                case "CHANGE":
                    Log.d(TAG, "onReceive: CHANGE");
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer=new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    try {
                        mediaPlayer.setDataSource(myContext,Uri.parse(intent.getStringExtra("PATH")));
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.prepareAsync();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
