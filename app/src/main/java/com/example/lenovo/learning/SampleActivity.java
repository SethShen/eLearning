package com.example.lenovo.learning;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lenovo.learning.com.tekinarslan.material.Frag3;
import com.example.lenovo.learning.com.tekinarslan.material.musicList.MusicAdapter;
import com.example.lenovo.learning.com.tekinarslan.material.musicList.MusicList;
import com.example.lenovo.learning.com.tekinarslan.material.musicList.MusicListFragment;
import com.example.lenovo.learning.com.tekinarslan.material.musicList.MusicPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SampleActivity extends ActionBarActivity {

    private ImageView[] imageViews = null;
    private ImageView imageView = null;
    private ViewPager advPager = null;
    private AtomicInteger what = new AtomicInteger(0);
    private boolean isContinue = true;
    private ViewGroup group=null;
    private ListView listView;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private Frag3 frag3;
    private Frag4 frag4;
    private MusicListFragment musicListFragment;
    private MusicAdapter musicAdapter;
    private RecyclerView mMusicRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        init();
        initViewPager();
        setListview();
    }
    public void init(){
        tv1 = (TextView) findViewById(R.id.main);
        tv2 = (TextView) findViewById(R.id.listen);
        tv3 = (TextView) findViewById(R.id.spokenEnglish);
        tv4 = (TextView) findViewById(R.id.like);
    }

    private void initViewPager() {
        advPager = (ViewPager) findViewById(R.id.adv_pager);
        group = (ViewGroup) findViewById(R.id.viewGroup);
        //这里存放的是四张广告背景
        List<View> advPics = new ArrayList<View>();

        ImageView img1 = new ImageView(this);
        img1.setBackgroundResource(R.mipmap.ic_launcher);/******/
        advPics.add(img1);

        ImageView img2 = new ImageView(this);
        img2.setBackgroundResource(R.mipmap.ic_ab_drawer);/********/
        advPics.add(img2);

        ImageView img3 = new ImageView(this);
        img3.setBackgroundResource(R.mipmap.plus);/********/
        advPics.add(img3);

        ImageView img4 = new ImageView(this);
        img4.setBackgroundResource(R.mipmap.shadow);/********/
        advPics.add(img4);

        //      对imageviews进行填充
        imageViews = new ImageView[advPics.size()];
        //小图标
        for (int i = 0; i < advPics.size(); i++) {
            imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(20, 20));/***布局参数****/
            imageView.setPadding(5, 5, 5, 5);//imageview内容与边框的内间距
            imageViews[i] = imageView;
            if (i == 0) {
                imageViews[i]
                        .setBackgroundResource(R.mipmap.ic_launcher);/********/
            } else {
                imageViews[i]
                        .setBackgroundResource(R.mipmap.ic_launcher);/********/
            }
            group.addView(imageViews[i]);
        }

        advPager.setAdapter(new AdvAdapter(advPics));
        advPager.setOnPageChangeListener(new GuidePageChangeListener());
        advPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isContinue = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        isContinue = true;
                        break;
                    default:
                        isContinue = true;
                        break;
                }
                return false;
            }
        });
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (isContinue) {
                        viewHandler.sendEmptyMessage(what.get());
                        whatOption();
                    }
                }
            }

        }).start();
    }


    private void whatOption() {
        what.incrementAndGet();
        if (what.get() > imageViews.length - 1) {
            what.getAndAdd(-4);//实现循环
        }
        try {
            Thread.sleep(2000);//2s钟的延迟
        } catch (InterruptedException e) {

        }
    }

    private final Handler viewHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            advPager.setCurrentItem(msg.what);
            super.handleMessage(msg);
        }

    };

    private final class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {        //页面滚动

        }

        @Override
        public void onPageSelected(int arg0) {              //选中页面，功能重写

        }

    }

    public final class AdvAdapter extends PagerAdapter {       //适配解析
        private List<View> views = null;

        public AdvAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(views.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {

        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(views.get(arg1), 0);
            return views.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }
    }

    /***************首页listview************************/
    public void setListview(){
            mMusicRecyclerView = (RecyclerView) findViewById(R.id.recorderfile);
            mMusicRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            musicAdapter = new MusicAdapter(MusicList.getMusicInfos(),SampleActivity.this);
            musicAdapter.setOnMusicClickListener(new MusicAdapter.OnMusicClickListener() {
                @Override
                public void OnMusicClick(View view, int position) {
                    Intent intent = MusicPlayer.MusicIntent(SampleActivity.this, MusicList.getMusicInfos(), position);
                    startActivity(intent);
                }
            });
            mMusicRecyclerView.setAdapter(musicAdapter);
        }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.main:
                break;
            case R.id.listen:
                if (musicListFragment==null) {
                    musicListFragment = new MusicListFragment();
                }
                Intent intent = new Intent(this,musicListFragment.getClass());
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
                if (frag4 == null) {
                    frag4 = new Frag4();
                }
                intent = new Intent(this,frag4.getClass());
                startActivity(intent);
                break;
        }
    }
}
