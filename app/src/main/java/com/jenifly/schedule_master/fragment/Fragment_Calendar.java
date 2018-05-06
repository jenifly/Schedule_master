package com.jenifly.schedule_master.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jenifly.schedule_master.R;
import com.jenifly.schedule_master.adapter.Frag_Calendar_Month_Adapter;
import com.jenifly.schedule_master.layoutManager.Calendar_Month_LayoutManager;
import com.jenifly.schedule_master.view.calendar.calendar.NCalendar;
import com.jenifly.schedule_master.view.calendar.listener.OnCalendarChangedListener;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jenifly on 2018/2/11.
 */

public class Fragment_Calendar extends Fragment implements OnCalendarChangedListener {

    @BindView(R.id.ncalendarrrr) NCalendar ncalendar;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.tv_year) TextView tv_year;
    @BindView(R.id.tv_month) TextView tv_month;
    @BindView(R.id.frag_calendar_menu) LinearLayout frag_calendar_menu;
  //  @BindView(R.id.tv_day) TextView tv_day;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, null);
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    private void init(){
        ncalendar.setMenu(frag_calendar_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Frag_Calendar_Month_Adapter aaAdapter = new Frag_Calendar_Month_Adapter(getContext());
        recyclerView.setAdapter(aaAdapter);
        ncalendar.setOnCalendarChangedListener(this);
        ncalendar.post(new Runnable() {
            @Override
            public void run() {
                List<String> list = new ArrayList<>();
                list.add("2018-01-21");
                list.add("2018-02-28");
                list.add("2018-02-3");
                list.add("2018-02-7");
                list.add("2018-02-8");
                list.add("2018-02-12");
                ncalendar.setPoint(list);
            }
        });
    }

    @OnClick(R.id.tv_month)void click(){
        ncalendar.toWeek();
    }

    public void toToday(View view) {
        ncalendar.toToday();
    }

    public void setDate(View view) {
        ncalendar.setDate("2017-12-31");
    }

    @Override
    public void onCalendarChanged(DateTime dateTime) {
//        if(tv_month.getText().equals(dateTime.getMonthOfYear() + "月") && tv_year.getText().equals(dateTime.getYear() + "年") &&
//             //   tv_day.getText().equals(dateTime.getDayOfMonth() + "日")){
//
//        }else {
//            tv_year.setText(dateTime.getYear() + "年");
//            tv_month.setText(dateTime.getMonthOfYear() + "月");
//            tv_day.setText(dateTime.getDayOfMonth() + "日  今天");
//        }
    }
}
