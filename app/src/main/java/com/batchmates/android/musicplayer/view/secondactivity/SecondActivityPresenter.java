package com.batchmates.android.musicplayer.view.secondactivity;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;

import com.batchmates.android.musicplayer.model.MusicPOJO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 8/15/2017.
 */

public class SecondActivityPresenter implements SecondActivityContract.Presenter{

    private static final String TAG = "SecondActivityPresenter";
    private List<MusicPOJO> musicPojo= new ArrayList<>();
    private MediaMetadataRetriever metaData=new MediaMetadataRetriever();
    SecondActivityContract.View view;

    @Override
    public void addView(SecondActivityContract.View view) {
        this.view=view;
    }

    @Override
    public void removeView() {
        this.view=null;
    }

    @Override
    public void CreateListOfMusic(Context context) {
        File home= new File("/sdcard/Music/");

        File file[]=home.listFiles();
        byte[] picture=null;
        for (int i = 0; i <home.listFiles().length ; i++) {
            metaData.setDataSource(context, Uri.parse(file[i].getPath()));

            if(metaData.getEmbeddedPicture()==null)
            {
                picture=null;
            }
            else
            {
                picture=metaData.getEmbeddedPicture();
            }
            musicPojo.add(new MusicPOJO(metaData.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM),
                    metaData.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE),picture
                    ,metaData.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUMARTIST),file[i].getPath()));
        }
        view.listOfMusic(musicPojo);
    }
}
