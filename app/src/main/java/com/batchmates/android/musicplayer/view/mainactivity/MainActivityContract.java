package com.batchmates.android.musicplayer.view.mainactivity;

import com.batchmates.android.musicplayer.BasePresenter;
import com.batchmates.android.musicplayer.BaseView;

/**
 * Created by Android on 8/15/2017.
 */

public interface MainActivityContract {

    interface View extends BaseView
    {

    }


    interface Presenter extends BasePresenter<View>
    {

    }
}
