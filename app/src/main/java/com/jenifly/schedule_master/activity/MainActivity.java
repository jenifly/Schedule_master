package com.jenifly.schedule_master.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import com.jenifly.schedule_master.R;
import com.jenifly.schedule_master.cache.Cache;
import com.jenifly.schedule_master.fragment.Fragment_Calendar;
import com.jenifly.schedule_master.fragment.Fragment_Main;
import com.jenifly.schedule_master.fragment.Fragment_TimeBoarde;
import com.jenifly.schedule_master.helper.StuBarTranslucentAPI21Helper;
import com.jenifly.schedule_master.view.blurredview.BlurredView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewPager) ViewPager viewPager;
    @BindView(R.id.yahooweather_blurredview) BlurredView mBlurredView;

    private ArrayList<Fragment> fragmentlist = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StuBarTranslucentAPI21Helper.initState(this);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            Cache.statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        fragmentlist.add(new Fragment_Calendar());
        fragmentlist.add(new Fragment_Main(mBlurredView));
        fragmentlist.add(new Fragment_TimeBoarde());
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentlist.get(position);
            }
            @Override
            public int getCount() {
                return fragmentlist.size();
            }
        });
        viewPager.setCurrentItem(1);
        viewPager.setOnPageChangeListener(new OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                positionOffset = position == 0?1-positionOffset:positionOffset;
                if(positionOffset > 0)
                    mBlurredView.setBlurredLevel((int) (positionOffset * (100 - Cache.blurredLevel)) + Cache.blurredLevel);
            }
            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
