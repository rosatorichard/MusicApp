package com.batchmates.android.musicplayer;

/**
 * Created by Android on 8/15/2017.
 */

public interface BasePresenter <V extends BaseView>{

    void addView(V view);

    void removeView();
}
