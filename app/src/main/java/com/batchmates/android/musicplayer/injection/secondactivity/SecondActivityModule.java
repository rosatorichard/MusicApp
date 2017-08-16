package com.batchmates.android.musicplayer.injection.secondactivity;

import com.batchmates.android.musicplayer.view.secondactivity.SecondActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android on 8/15/2017.
 */
@Module
public class SecondActivityModule {

    @Provides
    public SecondActivityPresenter secondActivityPresenter()
    {
        return new SecondActivityPresenter();
    }
}
