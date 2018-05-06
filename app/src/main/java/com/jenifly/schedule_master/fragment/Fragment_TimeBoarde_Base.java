/*
package com.jenifly.schedule_master.fragment;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jenifly.schedule_master.R;
import com.jenifly.schedule_master.constance.fragConst;
import com.jenifly.schedule_master.view.timelyView.TimerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

*/
/**
 * Created by Administrator on 2016/8/20.
 *//*

public class Fragment_TimeBoard_Base extends Fragment { // implements View.OnTouchListener {

    @BindView(R.id.mainrlt)
    CardView mainrlt;
    @BindView(R.id.timerview)
    TimerView timerView;
    @BindView(R.id.rootlt)
    FrameLayout rootlt;
    @BindView(R.id.main_text_1)
    TextView main_text_1;
    @BindView(R.id.main_text_2)
    TextView main_text_2;
    @BindView(R.id.main_text_3)
    TextView main_text_3;

    private DisplayMetrics dm2;
    private String fragTag = "";
    private boolean isNewFragment = false;
    //private BaseInfo mBaseInfo;

    public String getFragTag() { // 被反射的方法
        return fragTag;
    }

//    public int getFragId(){
//        return mBaseInfo.getTBID();
//    }

//    public Fragment_TimeBoard_Base(BaseInfo mBaseInfo) {
//        this.mBaseInfo = mBaseInfo;
//        this.fragTag = fragConst.new_mainfrag_count + "";
//        fragConst.new_mainfrag_count++;
//        isNewFragment = true;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        init();
        return view;
    }

    private void init() {
        mainrlt.setOnTouchListener(new ZoomListenter(this, mainrlt));
        dm2 = getResources().getDisplayMetrics();
        if ((fragConst.new_mainfrag_count > 1) && isNewFragment) {
            PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 0.1f, 1f);
            PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 0.1f, 1f);
            ObjectAnimator scale = ObjectAnimator.ofPropertyValuesHolder(rootlt, pvhX, pvhY);
            scale.setDuration(50);
            scale.start();
        }
        isNewFragment = false;
        refesh();
    }

    private void refesh(){
        if(mBaseInfo.getTimestamp() == 0)
            timerView.setTime(0,0,0,0);
        else
            timerView.setTime(TimeStampUtils.getTimeStamp(mBaseInfo.getTimestamp()));
        setBackgroud();
        setText();
    }

    private void setBackgroud(){
        if(mBaseInfo.getbBg().equals("null"))
            mainrlt.setBackground(getResources().getDrawable(R.drawable.bg).mutate());
        else
            mainrlt.setBackground(getResources().getDrawable(Integer.parseInt(mBaseInfo.getbBg())).mutate());
    }

    public void ReBackground(){
        mainrlt.setBackground(getResources().getDrawable(Integer.parseInt(baseInfo.getbBg())).mutate());
    }

    public BaseInfo getmBaseInfo(){
        return mBaseInfo;
    }

    private void setText(){
        String text_1,text_2,text_3;
        if(mBaseInfo.getText1().equals("null"))
            text_1 = "此处为标题：";
        else
            text_1 = mBaseInfo.getText1();
        if(mBaseInfo.getText2().equals("null"))
            text_2 = "此处为副标题";
        else
            text_2 = mBaseInfo.getText2();
        if(mBaseInfo.getText3().equals("null"))
            text_3 = "此处为底栏";
        else
            text_3 = mBaseInfo.getText3();
        main_text_1.setText(text_1);
        main_text_2.setText(text_2);
        main_text_3.setText(text_3);
    }

    //删除动画
    public void delAnime() {
        if (fragConst.fraglist.size() <= 1) {
            return;
        }
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.01f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.01f);
        ObjectAnimator scalexy = ObjectAnimator.ofPropertyValuesHolder(mainrlt, pvhX, pvhY);

        ObjectAnimator scale = ObjectAnimator.ofFloat(mainrlt, "translationY", 0, -2500);

        scale.setDuration(300);
        scalexy.setDuration(300);
        scale.start();
        scalexy.start();
        setAlpha(255);
    }

    public void setAlpha(int alpha){
        int color = ColorUtils.getColorWithAlpha(alpha,main_text_1.getCurrentTextColor());
        mainrlt.getBackground().setAlpha(alpha);
        main_text_1.setTextColor(color);
        main_text_2.setTextColor(color);
        main_text_3.setTextColor(color);
        timerView.setColor(alpha, color);
    }

    @Subscribe
    public void onEventMainThread(baseEvent event) {
        if (event instanceof secondEvent) {
            timerView.addOne();
        }
        if (event instanceof refeshEvent) {
            refeshEvent rEvent = (refeshEvent)event;
            if(baseInfo.getTBID() == mBaseInfo.getTBID()) {
                switch (rEvent.getType()){
                    case BaseInfo.TIMESTAMP:
                        mBaseInfo.setTimestamp(baseInfo.getTimestamp());
                        timerView.setTime(TimeStampUtils.getTimeStamp(baseInfo.getTimestamp()));
                        break;
                    case BaseInfo.TEXT1:
                        mBaseInfo.setText1(baseInfo.getText1());
                        mBaseInfo.setText2(baseInfo.getText2());
                        mBaseInfo.setText3(baseInfo.getText3());
                        setText();
                        break;
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
*/
