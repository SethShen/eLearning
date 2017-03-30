package com.example.lenovo.learning.com.tekinarslan.material;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.learning.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by lenovo on 2017/3/22.
 */

public class SpeakDataDetails extends Activity {
    private TextView speakdatadetails;
    private TextView control;
    private String title ;
    private String article;
    private Intent intent;
    private Button start;
    private Button stop;
    private MediaRecorder mediaRecorder;
    private File recorderfile;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstateState) {
        super.onCreate(savedInstateState);
        setContentView(R.layout.speakdatadetails);
        initView();
        mediaRecorder = new MediaRecorder();
        mediaPlayer = new MediaPlayer();
        // 麦克风源录音
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        // 输出格式
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        // 编码格式
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        // 根目录
        File sdcard = Environment.getExternalStorageDirectory();
        // 将录制的音频文件存储到SDCard根目录下
        recorderfile = new File(sdcard, title + ".amr");
        if(!recorderfile.exists()){
            try {
                recorderfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void initView() {
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        control = (TextView) findViewById(R.id.control);
        intent = getIntent();
        article = intent.getStringExtra("article");
        title = intent.getStringExtra("title");
        speakdatadetails = (TextView) findViewById(R.id.speakdatadetails);
        speakdatadetails.setText(article);
        speakdatadetails.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    public void onClick(View view) throws NoSuchFieldException, IllegalAccessException {
        switch (view.getId()) {
            case R.id.start:
                control.setText("录音中...");
                start.setEnabled(false);
                stop.setEnabled(true);
                startWork();
                break;
            case R.id.stop:
                control.setText("停止录音");
                start.setEnabled(true);
                stop.setEnabled(false);
                stopWork();
                break;
        }
    }

    public void startWork() {
        if(mediaRecorder==null){
            mediaRecorder = new MediaRecorder();
        }
        // 设置录制音频的输出存放文件
        mediaRecorder.setOutputFile(recorderfile.getAbsolutePath());
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaRecorder.start();
        control.setText("录音中....");
    }

    public void stopWork() throws NoSuchFieldException, IllegalAccessException {

        mediaRecorder.stop();
        mediaRecorder.release();

         AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("请选择");
                builder.setPositiveButton("试听", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            mediaPlayer.reset();
                            mediaPlayer.setDataSource(recorderfile.getAbsolutePath());
                            if(!mediaPlayer.isPlaying()){
                                mediaPlayer.prepare();
                                mediaPlayer.start();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                builder.setNeutralButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //删除
                        if(recorderfile.exists()){
                            recorderfile.delete();
                             control.setText("已删除");
                        }
                   }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
        mediaRecorder.release();
        mediaRecorder = null;
    }
}
