package com.batchmates.android.musicplayer.injection.secondactivity;

import com.batchmates.android.musicplayer.view.secondactivity.SecondActivity;

import dagger.Component;

/**
 * Created by Android on 8/15/2017.
 */
@Component(modules = SecondActivityModule.class)
public interface SecondActivityComponent {

    void inject(SecondActivity secondActivity);
}
