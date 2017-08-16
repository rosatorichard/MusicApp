package com.batchmates.android.musicplayer.view.mainactivity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.batchmates.android.musicplayer.R;
import com.batchmates.android.musicplayer.injection.mainactivity.DaggerMainActivityComponent;
import com.batchmates.android.musicplayer.model.MusicPOJO;
import com.batchmates.android.musicplayer.view.MyService;
import com.batchmates.android.musicplayer.view.secondactivity.SecondActivity;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View{

    private List<MusicPOJO> musicPojo=new ArrayList<>();
    private static final String TAG = "MediaPlayer";
    private MainActivityReciever reciever;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Inject MainActivityPresenter presenter;
    private MediaPlayer mediaPlayer= new MediaPlayer();
    private MediaMetadataRetriever metaData=new MediaMetadataRetriever();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Log.d(TAG, "onCreate: "+MediaStore.Audio.Albums.CONTENT_TYPE);
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
        else {


            Intent musicIntent= new Intent(this, MyService.class);
            musicIntent.putExtra("PATH","/sdcard/Music/Climb_Every_Mountain.mp3");
            musicIntent.putExtra("NAME","CLimb");
            startService(musicIntent);
        }
        setUpDagger();
        ButterKnife.bind(this);
        presenter.addView(this);

    }


    @BindView(R.id.tvSongCurrentlyPlaying)
    TextView songPlaying;

    private void setUpDagger() {
        DaggerMainActivityComponent.create().inject(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);



        switch(requestCode)
        {
            case REQUEST_EXTERNAL_STORAGE:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED)
                {
                    Intent musicIntent= new Intent(this, MyService.class);
                    musicIntent.putExtra("PATH","/sdcard/Music/Climb_Every_Mountain.mp3");
                    musicIntent.putExtra("NAME","CLimb");
                    startService(musicIntent);
                }
                break;
        }
    }

    @Override
    public void showError() {

    }

    public void startButton(View view) throws IOException {
        Log.d(TAG, "startButton: Start");
        Intent intent=new Intent("START");
        sendBroadcast(intent);
    }

    public void stopButton(View view) {
        Log.d(TAG, "stopButton: Stop");
        Intent intent=new Intent("STOP");
        sendBroadcast(intent);
    }

    public void newSong(View view) {
        Intent intent=new Intent(this, SecondActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }


    public class MainActivityReciever extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            songPlaying.setText(intent.getStringExtra("NAME"));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        reciever=new MainActivityReciever();
        IntentFilter intentFilter=new IntentFilter("CHANGE");
        registerReceiver(reciever,intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(reciever);
        Intent musicIntent= new Intent(this, MyService.class);
        stopService(musicIntent);
    }
}
