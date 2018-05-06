package com.jenifly.schedule_master.view.timelyView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jenifly.schedule_master.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jenifly on 2017/7/5.
 */

public class TimerView extends LinearLayout {

    private int days, hours, minutes, seconds;

    @BindView(R.id.timer_days) DaysView daysView;
    @BindView(R.id.timer_hour) HMSView hourView;
    @BindView(R.id.timer_minute) HMSView mintueView;
    @BindView(R.id.timer_second) HMSView secondView;
    @BindView(R.id.timerview_tv) TextView textView;
    @BindView(R.id.timerview_point0) ImageView imageView0;
    @BindView(R.id.timerview_point1) ImageView imageView1;

    public TimerView(Context context) {
        super(context);
        init();
    }

    public TimerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TimerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater inflater =(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_timer, null);
        ButterKnife.bind(view);
        this.addView(view);
    }

    public void setColor(int alpha, int color){
        daysView.setColor(color);
        hourView.setColor(color);
        mintueView.setColor(color);
        secondView.setColor(color);
        imageView0.getDrawable().setAlpha(alpha);
        imageView1.getDrawable().setAlpha(alpha);
        textView.setTextColor(color);
    }

    public void setTime(int time[]){
        setTime(time[0], time[1], time[2], time[3]);
    }

    public void setTime(int days, int hours, int minutes, int seconds){
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        daysView.setDays(days);
        hourView.setValue(hours,0);
        mintueView.setValue(minutes,1);
        secondView.setValue(seconds,1);
        invalidate();
    }

    public void addOne(){
        ++seconds;
        secondView.addOne(300);
        if(seconds == 60){
            seconds = 0;
            ++minutes;
            mintueView.addOne(600);
            if(minutes == 60){
                minutes = 0;
                ++hours;
                hourView.addOne(600);
                if(hours == 24) {
                    ++days;
                    daysView.addDay(800);
                }
            }
        }
    }
}
