package com.example.lenovo.learning.com.tekinarslan.material.musicList;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.learning.R;
import com.example.lenovo.learning.com.tekinarslan.material.MyDownloads;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Seth on 2017/3/25.
 */

public class MusicPlayer extends AppCompatActivity{
    private static final String EXTRA_PASS_MUSIC_URL = "com.example.lenovo.learning.com.tekinarslan.material.musicList.EXTRA_PASS_MUSIC_URL";
    private static final String EXTRA_PASS_MUSIC_TITLE = "com.example.lenovo.learning.com.tekinarslan.material.musicList.EXTRA_PASS_MUSIC_Title";
    private static final String EXTRA_PASS_MUSIC_Time = "com.example.lenovo.learning.com.tekinarslan.material.musicList.EXTRA_PASS_MUSIC_Time";
    private Button mPlayOrPause;
    private Button mStop;
    private Button mQuit;
    private TextView mStatusTextview;
    private TextView mTitleTextView;
    private TextView mAllTimeTextView;
    private TextView mTimeTextView;
    private SeekBar mSeekBar;
    private boolean isPrepared;
    private boolean tag2 = false;
    private MediaPlayer mMediaPlayer;
    private MusicList musicList;
    private String myurl;
    private Button delete;
    private boolean ifshow = false;
    private MusicListFragment musicListFragment;
    //封装传递数据的Intent
    public static Intent MusicIntent(Context context, List<MusicInfo> musicInfos, int position) {
        Intent intent = new Intent(context, MusicPlayer.class);

        intent.putExtra(EXTRA_PASS_MUSIC_URL, musicInfos.get(position).getUrl());
        intent.putExtra(EXTRA_PASS_MUSIC_TITLE, musicInfos.get(position).getTitle());
        intent.putExtra(EXTRA_PASS_MUSIC_Time, MusicAdapter.formatTime(musicInfos.get(position).getDuration()));

        return intent;
    }


    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.player_page);
        Intent intent = getIntent();
        ifshow = intent.getBooleanExtra("ifshow",false);
        delete = (Button)findViewById(R.id.delete);
        if(ifshow){
            delete.setVisibility(View.VISIBLE);
            delete.setEnabled(true);
        }

        /*if(musicListFragment == null){
            musicListFragment = new MusicListFragment();
        }*/
        final String url = getIntent().getStringExtra(EXTRA_PASS_MUSIC_URL);
        myurl = url;
        String title = getIntent().getStringExtra(EXTRA_PASS_MUSIC_TITLE);
        String totalTime = getIntent().getStringExtra(EXTRA_PASS_MUSIC_Time);
        mPlayOrPause = (Button)findViewById(R.id.BtnPlayorPause);
        mStop = (Button) findViewById(R.id.BtnStop);
        mQuit = (Button) findViewById(R.id.BtnQuit);
        mStatusTextview = (TextView) findViewById(R.id.MusicStatus);
        mTitleTextView = (TextView) findViewById(R.id.music_player_title);
        mTimeTextView = (TextView) findViewById(R.id.MusicTime);
        mAllTimeTextView = (TextView) findViewById(R.id.MusicTotal);
        mSeekBar = (SeekBar) findViewById(R.id.MusicSeekBar);

        mTitleTextView.setMovementMethod(new ScrollingMovementMethod());
        mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource(url);
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        isPrepared = false;
        mTitleTextView.setText(title);
        mAllTimeTextView.setText(totalTime);
        //删除
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                File file = new File(myurl);
                if(file.exists()){
                    file.delete();
                }
                if(musicList==null){
                    musicList = new MusicList(MusicPlayer.this);
                }
                Toast.makeText(MusicPlayer.this,"已删除",Toast.LENGTH_LONG).show();
                if(mMediaPlayer.isPlaying()){
                    mMediaPlayer.stop();
                }
                Toast.makeText(MusicPlayer.this,myurl,Toast.LENGTH_LONG).show();
                musicList.upDateUI(myurl);
                Intent intent = new Intent(MusicPlayer.this, MyDownloads.class);
                startActivity(intent);
            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mMediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mPlayOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPrepared == false) {
                    mSeekBar.setProgress(mMediaPlayer.getCurrentPosition());
                    mSeekBar.setMax(mMediaPlayer.getDuration());
                    isPrepared = true;
                }
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    mStatusTextview.setText("Pause");
                    mPlayOrPause.setText("Play");
                } else {
                    mMediaPlayer.start();
                    mStatusTextview.setText("Playing");
                    mPlayOrPause.setText("Pause");
                }
                if(tag2 == false){
                    mHandler.post(mRunnable);
                    tag2 = true;
                }
            }

        });

        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStatusTextview.setText("Stoped");
                mPlayOrPause.setText("Play");
                isPrepared = false;
                if(mMediaPlayer != null){
                    mMediaPlayer.stop();

                    try {
                        mMediaPlayer.reset();
                        mMediaPlayer.setDataSource(url);
                        mMediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        mQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandler.removeCallbacks(mRunnable);
                MusicPlayer.this.finish();
            }
        });

    }
    //通过 Handler 更新 UI 上的组件状态
    public Handler mHandler = new Handler();
    public Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mTimeTextView.setText(MusicAdapter.formatTime(mMediaPlayer.getCurrentPosition()));
            mSeekBar.setProgress(mMediaPlayer.getCurrentPosition());
            mSeekBar.setMax(mMediaPlayer.getDuration());
            mAllTimeTextView.setText(MusicAdapter.formatTime(mMediaPlayer.getDuration()));
            mHandler.postDelayed(mRunnable,200);
        }
    };
}
