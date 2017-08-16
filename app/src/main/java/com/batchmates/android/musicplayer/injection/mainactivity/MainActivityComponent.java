package com.batchmates.android.musicplayer.injection.mainactivity;

import com.batchmates.android.musicplayer.view.mainactivity.MainActivity;

import dagger.Component;

/**
 * Created by Android on 8/15/2017.
 */
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}
