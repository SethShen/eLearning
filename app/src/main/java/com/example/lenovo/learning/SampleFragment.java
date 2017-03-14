package com.example.lenovo.learning;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SampleFragment extends Fragment {

    private static final String ARG_POSITION = "position";

    private int position;

    public static SampleFragment newInstance(int position) {
        SampleFragment f = new SampleFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_POSITION);
        View rootView = null;
        SampleActivity sampleActivity = new SampleActivity();
        ViewGroup group = null;
        ViewPager pager = null;
        switch (position){
            case 0:
                rootView = inflater.inflate(R.layout.page0, container, false);
                break;
            case 1:
                //销毁广告栏
                rootView = inflater.inflate(R.layout.page1, container, false);
                break;
            case 2:
                //销毁广告栏
                rootView = inflater.inflate(R.layout.page2, container, false);
                break;
            case 3:
                //销毁广告栏
                rootView = inflater.inflate(R.layout.page3, container, false);
                break;
            case 4://销毁广告栏
                rootView = inflater.inflate(R.layout.page4, container, false);
                break;
            case 5://销毁广告栏
                rootView = inflater.inflate(R.layout.page5, container, false);
                break;
            case 6://销毁广告栏
                rootView = inflater.inflate(R.layout.page6, container, false);
                break;
            case 7://销毁广告栏
                rootView = inflater.inflate(R.layout.page7, container, false);
                break;
        }
        return rootView;
    }
}