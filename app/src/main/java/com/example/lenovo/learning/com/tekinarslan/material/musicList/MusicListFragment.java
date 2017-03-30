package com.example.lenovo.learning.com.tekinarslan.material.musicList;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lenovo.learning.Frag4;
import com.example.lenovo.learning.R;
import com.example.lenovo.learning.SampleActivity;
import com.example.lenovo.learning.com.tekinarslan.material.Frag3;

import java.util.List;

public class MusicListFragment extends AppCompatActivity {

    private RecyclerView mMusicRecyclerView;
    private MusicAdapter mAdapter;
    private SampleActivity sampleActivity;
    private Frag3 frag3;
    private Frag4 frag4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        mMusicRecyclerView = (RecyclerView) findViewById(R.id.music_recycler_view);
        mMusicRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateUI();
    }

    public void updateUI() {
        MusicList musicList = MusicList.get(this);
        final List<MusicInfo> musicInfos = musicList.getMusicInfos();

        mAdapter = new MusicAdapter(musicInfos, MusicListFragment.this);
        mAdapter.setOnMusicClickListener(new MusicAdapter.OnMusicClickListener() {

            @Override
            public void OnMusicClick(View view, int position) {
                Intent intent = MusicPlayer.MusicIntent(MusicListFragment.this, musicInfos, position);
                startActivity(intent);
            }
        });
        mMusicRecyclerView.setAdapter(mAdapter);

    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.main:
                if(sampleActivity==null){
                    sampleActivity=new SampleActivity();
                }
                Intent intent = new Intent(this,sampleActivity.getClass());
                startActivity(intent);
                break;
            case R.id.listen:

                break;
            case R.id.spokenEnglish:
                if (frag3 == null) {
                    frag3 = new Frag3();
                }
                intent = new Intent(this,frag3.getClass());
                startActivity(intent);
                break;
            case R.id.like:
                if (frag4 == null) {
                    frag4 = new Frag4();
                }
                intent = new Intent(this,frag4.getClass());
                startActivity(intent);
                break;
        }
    }
}
