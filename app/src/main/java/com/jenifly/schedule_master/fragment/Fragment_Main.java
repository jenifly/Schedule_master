package com.jenifly.schedule_master.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jenifly.schedule_master.R;
import com.jenifly.schedule_master.adapter.RecyclerViewAdapter;
import com.jenifly.schedule_master.cache.Cache;
import com.jenifly.schedule_master.view.blurredview.BlurredView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jenifly on 2018/2/11.
 */

@SuppressLint("ValidFragment")
public class Fragment_Main extends Fragment {

    @BindView(R.id.main_toolbar) LinearLayout main_toolbar;
    @BindView(R.id.yahooweather_recyclerview) RecyclerView mRecyclerView;

    private int mScrollerY;
    private BlurredView mBlurredView;

    @SuppressLint("ValidFragment")
    public Fragment_Main(BlurredView mBlurredView){
        this.mBlurredView = mBlurredView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init(){
        if(Cache.statusBarHeight > 0){
            main_toolbar.setPadding(0,Cache.statusBarHeight,0,0);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new RecyclerViewAdapter(getContext()));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollerY += dy;
                if (Math.abs(mScrollerY) > 1000) {
                    mBlurredView.setBlurredTop(100);
                    Cache.blurredLevel = 100;
                } else {
                    mBlurredView.setBlurredTop(mScrollerY / 10);
                    Cache.blurredLevel = Math.abs(mScrollerY) / 10;
                }
                mBlurredView.setBlurredLevel(Cache.blurredLevel);
            }
        });
    }
}
