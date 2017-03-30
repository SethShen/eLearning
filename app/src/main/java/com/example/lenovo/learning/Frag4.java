package com.example.lenovo.learning;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;

import com.example.lenovo.learning.com.tekinarslan.material.Frag3;
import com.example.lenovo.learning.com.tekinarslan.material.MyDownloads;
import com.example.lenovo.learning.com.tekinarslan.material.musicList.MusicListFragment;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by lenovo on 2017/3/20.
 */

public class Frag4 extends Activity {

    private ImageView userpic;
    private SampleActivity sampleActivity;
    private MusicListFragment musicListFragment;
    private Frag3 frag3;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_like);
        userpic=(ImageView)findViewById(R.id.userpic);
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
                if (musicListFragment == null) {
                    musicListFragment = new MusicListFragment();
                }
                intent = new Intent(this,musicListFragment.getClass());
                startActivity(intent);
                break;
            case R.id.spokenEnglish:
                if (frag3 == null) {
                    frag3 = new Frag3();
                }
                intent = new Intent(this,frag3.getClass());
                startActivity(intent);
                break;
            case R.id.like:

                break;
        }
    }
    public void myClick(View view){
        switch (view.getId()){
            case R.id.userpic:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("修改头像");
                //调用相机拍照
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        updateUserPic();
                    }
                });
                //从相册里面取照片
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
                break;
            case R.id.mydownloads:
                Intent intent = new Intent(this,MyDownloads.class);
                startActivity(intent);
                break;
            case R.id.mysettings:

                break;
            case R.id.about:
                intent = new Intent(this,About.class);
                startActivity(intent);
                break;
        }
    }

    private void updateUserPic() {
        Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT, null);//返回被选中项的URI
        intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");//得到所有图片的URI
        startActivityForResult(intent1,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK){
            switch (requestCode) {
                case 1:
                    ContentResolver resolver = getContentResolver();
                    //照片的原始资源地址
                    Uri originalUri = data.getData();
                    try {
                        //使用ContentProvider通过URI获取原始图片
                        Bitmap photo = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                        if (photo != null) {
                            userpic.setBackgroundColor(Color.parseColor("#80000000"));
                            userpic.setImageBitmap(photo);
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
