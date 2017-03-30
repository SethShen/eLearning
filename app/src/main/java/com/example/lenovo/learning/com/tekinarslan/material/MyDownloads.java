package com.example.lenovo.learning.com.tekinarslan.material;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lenovo.learning.R;
import com.example.lenovo.learning.com.tekinarslan.material.musicList.MusicAdapter;
import com.example.lenovo.learning.com.tekinarslan.material.musicList.MusicList;
import com.example.lenovo.learning.com.tekinarslan.material.musicList.MusicPlayer;

/**
 * Created by lenovo on 2017/3/26.
 */

public class MyDownloads extends Activity {
    private MusicAdapter musicAdapter;
    private RecyclerView mMusicRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydownloads);
        mMusicRecyclerView = (RecyclerView) findViewById(R.id.myrecorderfile);
        mMusicRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        setListview();
    }
    public void setListview(){
        musicAdapter = new MusicAdapter(MusicList.getMusicInfos(),MyDownloads.this);
        musicAdapter.setOnMusicClickListener(new MusicAdapter.OnMusicClickListener() {
            @Override
            public void OnMusicClick(View view, int position) {
                Intent intent = MusicPlayer.MusicIntent(MyDownloads.this, MusicList.getMusicInfos(), position);
                intent.putExtra("ifshow",true);
                startActivity(intent);
            }
        });
        mMusicRecyclerView.setAdapter(musicAdapter);
    }

}
