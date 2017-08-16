package com.batchmates.android.musicplayer.view.mainactivity;

/**
 * Created by Android on 8/15/2017.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter{

    MainActivityContract.View view;

    @Override
    public void addView(MainActivityContract.View view) {
        this.view=view;
    }

    @Override
    public void removeView() {
        this.view=null;
    }
}
