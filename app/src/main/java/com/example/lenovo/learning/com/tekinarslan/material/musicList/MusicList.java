package com.example.lenovo.learning.com.tekinarslan.material.musicList;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seth on 2017/3/22.
 */

public class MusicList extends FragmentActivity{
    private static MusicList sMusicList;
    private static List<MusicInfo> mMusicInfos;
    private Cursor mCursor;
    public static MusicList get(Context context){
        if(sMusicList == null){
            sMusicList = new MusicList(context);
        }
        return sMusicList;
    }
    public MusicList(Context context){

        mMusicInfos = new ArrayList<>();

        ContentResolver contentResolver = context.getContentResolver();
        mCursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.TITLE,
                        MediaStore.Audio.Media.ALBUM,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.DURATION,
                        MediaStore.Audio.Media.SIZE,
                        MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.Media.ALBUM_ID}, null, null, null);

        for (int i = 0; i < mCursor.getCount(); ++i) {
            MusicInfo musicInfo = new MusicInfo();
            mCursor.moveToNext();
            long id = mCursor.getLong(0);
            String title = mCursor.getString(1);
            String album = mCursor.getString(2);
            String displayName = mCursor.getString(3);
            String artist = mCursor.getString(4);
            long duration = mCursor.getLong(5);
            long size = mCursor.getLong(6);
            String url = mCursor.getString(7);
            long albumId = mCursor.getLong(8);

            musicInfo.setTitle(title);
            musicInfo.setId(id);
            musicInfo.setAlbum(album);
            musicInfo.setArtist(artist);
            musicInfo.setSize(size);
            musicInfo.setDisplayName(displayName);
            musicInfo.setDuration(duration);
            musicInfo.setUrl(url);
            musicInfo.setAlbumId(albumId);

            mMusicInfos.add(musicInfo);
        }
    }
    //从数据库中获取歌曲列表
    public static List<MusicInfo> getMusicInfos() {
        return mMusicInfos;
    }
    public void upDateUI(String myurl){
        int i = 0;
        for(i=0;i<mCursor.getCount();i++){
            if(myurl.toString().toLowerCase().equals(mCursor.getString(7).toLowerCase())){
                break;
            }
        }
        mMusicInfos.remove(i);
    }
}
