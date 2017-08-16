package com.batchmates.android.musicplayer.view.secondactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.batchmates.android.musicplayer.R;
import com.batchmates.android.musicplayer.injection.secondactivity.DaggerSecondActivityComponent;
import com.batchmates.android.musicplayer.model.MusicPOJO;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity implements SecondActivityContract.View{

    @Inject SecondActivityPresenter presenter;
    private RecyclerView.LayoutManager layoutManager;
    private DefaultItemAnimator defaultItemAnimator=new DefaultItemAnimator();
    private RecyclerAdapeterHelper recyclerAdapeterHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);
        setUpDagger();
        presenter.addView(this);

        presenter.CreateListOfMusic(this);

    }


    @BindView(R.id.rvMyRecycler)
    RecyclerView recyclerView;


    private void setUpDagger() {
        DaggerSecondActivityComponent.create().inject(this);
    }

    @Override
    public void showError() {

    }

    @Override
    public void listOfMusic(List<MusicPOJO> musicPOJOList) {
        layoutManager=new LinearLayoutManager(this);
        recyclerAdapeterHelper=new RecyclerAdapeterHelper(musicPOJOList,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(defaultItemAnimator);
        recyclerView.setAdapter(recyclerAdapeterHelper);
    }

}
