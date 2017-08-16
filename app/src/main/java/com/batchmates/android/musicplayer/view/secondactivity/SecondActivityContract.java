package com.batchmates.android.musicplayer.view.secondactivity;

import android.content.Context;

import com.batchmates.android.musicplayer.BasePresenter;
import com.batchmates.android.musicplayer.BaseView;
import com.batchmates.android.musicplayer.model.MusicPOJO;

import java.util.List;

/**
 * Created by Android on 8/15/2017.
 */

public interface SecondActivityContract {

    interface View extends BaseView
    {
        void listOfMusic(List<MusicPOJO> musicPOJOList);
    }

    interface Presenter extends BasePresenter<View>
    {
        void CreateListOfMusic(Context context);
    }
}
