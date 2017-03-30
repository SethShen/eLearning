package com.example.lenovo.learning.com.tekinarslan.material.musicList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.learning.R;

import java.util.List;

/**
 * Created by Seth on 2017/3/25.
 */

//定义点击事件接口

public  class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicHolder>{

    private Context mContext;
    private static List<MusicInfo> mMusicInfos;
    private OnMusicClickListener mOnMusicClickListener;
    public void setOnMusicClickListener(OnMusicClickListener mOnMusicClickListener){
        this.mOnMusicClickListener = mOnMusicClickListener;
    }
    public MusicAdapter(List<MusicInfo> musicInfos, Context context){
        mMusicInfos = musicInfos;
        mContext = context;
    }

    @Override
    public MusicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_music, parent, false);

        return new MusicHolder(view);
    }

    @Override
    public void onBindViewHolder(final MusicHolder holder, int position) {
        MusicInfo musicInfo = mMusicInfos.get(position);
        holder.bindMusic(musicInfo);
        if(mOnMusicClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();
                    mOnMusicClickListener.OnMusicClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mMusicInfos.size();
    }
    public interface OnMusicClickListener {
        void OnMusicClick(View view, int position);
    };


    public static class MusicHolder extends RecyclerView.ViewHolder{

        private TextView mDurationTextView;
        private TextView mTitleTextView;
        private TextView mArtistTextView;

        private MusicInfo mMusicInfo;

        public MusicHolder(View itemView) {
            super(itemView);

            mDurationTextView = (TextView) itemView.findViewById(R.id.music_duration);
            mTitleTextView = (TextView) itemView.findViewById(R.id.music_title);
            mArtistTextView = (TextView) itemView.findViewById(R.id.music_Artist);
        }
        public void bindMusic(MusicInfo music){
            mMusicInfo = music;
            mDurationTextView.setText(formatTime(mMusicInfo.getDuration()));
            mTitleTextView.setText(mMusicInfo.getTitle());
            mArtistTextView.setText(mMusicInfo.getArtist());
        }

    }


    public static String formatTime(long time) {
        String min = time / (1000 * 60) + "";
        String sec = time % (1000 * 60) + "";
        if (min.length() < 2) {
            min = "0" + time / (1000 * 60) + "";
        } else {
            min = time / (1000 * 60) + "";
        }
        if (sec.length() == 4) {
            sec = "0" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 3) {
            sec = "00" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 2) {
            sec = "000" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 1) {
            sec = "0000" + (time % (1000 * 60)) + "";
        }
        return min + ":" + sec.trim().substring(0, 2);
    }
}




