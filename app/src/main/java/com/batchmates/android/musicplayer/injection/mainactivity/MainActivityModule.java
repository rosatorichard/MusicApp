package com.batchmates.android.musicplayer.injection.mainactivity;

import com.batchmates.android.musicplayer.view.mainactivity.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android on 8/15/2017.
 */
@Module
public class MainActivityModule {

    @Provides
    public MainActivityPresenter mainActivityPresenter()
    {
        return new MainActivityPresenter();
    }
}
