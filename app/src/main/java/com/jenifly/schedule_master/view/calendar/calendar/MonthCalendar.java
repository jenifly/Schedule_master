package com.jenifly.schedule_master.view.calendar.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.Toast;

import com.jenifly.schedule_master.R;
import com.jenifly.schedule_master.view.calendar.adapter.CalendarAdapter;
import com.jenifly.schedule_master.view.calendar.adapter.MonthAdapter;
import com.jenifly.schedule_master.view.calendar.listener.OnClickMonthViewListener;
import com.jenifly.schedule_master.view.calendar.listener.OnMonthCalendarChangedListener;
import com.jenifly.schedule_master.utils.DateUtils;
import com.jenifly.schedule_master.view.calendar.view.CalendarView;
import com.jenifly.schedule_master.view.calendar.view.MonthView;

import org.joda.time.DateTime;

/**
 * Created by Jenifly on 2018/2/18.
 */

public class MonthCalendar extends CalendarPager implements OnClickMonthViewListener {

    private OnMonthCalendarChangedListener onMonthCalendarChangedListener;
    private int lastPosition = -1;

    public MonthCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected CalendarAdapter getCalendarAdapter() {

        mPageSize = DateUtils.getIntervalMonths(startDateTime, endDateTime) + 1;
        mCurrPage = DateUtils.getIntervalMonths(startDateTime, mInitialDateTime);

        return new MonthAdapter(getContext(), mPageSize, mCurrPage, mInitialDateTime, this);
    }


    @Override
    protected void initCurrentCalendarView(int position) {

        MonthView currView = (MonthView) calendarAdapter.getCalendarViews().get(position);
        MonthView lastView = (MonthView) calendarAdapter.getCalendarViews().get(position - 1);
        MonthView nextView = (MonthView) calendarAdapter.getCalendarViews().get(position + 1);


        if (currView == null) {
            return;
        }

        if (lastView != null)
            lastView.clear();

        if (nextView != null)
            nextView.clear();


        //只处理翻页
        if (lastPosition == -1) {
            currView.setDateTimeAndPoint(mInitialDateTime, pointList);
            mSelectDateTime = mInitialDateTime;
            lastSelectDateTime = mInitialDateTime;
            if (onMonthCalendarChangedListener != null) {
                onMonthCalendarChangedListener.onMonthCalendarChanged(mSelectDateTime);
            }
        } else if (isPagerChanged) {
            int i = position - lastPosition;
            mSelectDateTime = mSelectDateTime.plusMonths(i);

            if (isDefaultSelect) {
                //日期越界
                if (mSelectDateTime.getMillis() > endDateTime.getMillis()) {
                    mSelectDateTime = endDateTime;
                } else if (mSelectDateTime.getMillis() < startDateTime.getMillis()) {
                    mSelectDateTime = startDateTime;
                }

                currView.setDateTimeAndPoint(mSelectDateTime, pointList);
                if (onMonthCalendarChangedListener != null) {
                    onMonthCalendarChangedListener.onMonthCalendarChanged(mSelectDateTime);
                }
            } else {
                if (DateUtils.isEqualsMonth(lastSelectDateTime, mSelectDateTime)) {
                    currView.setDateTimeAndPoint(lastSelectDateTime, pointList);
                }
            }

        }
        lastPosition = position;
    }

    public void setOnMonthCalendarChangedListener(OnMonthCalendarChangedListener onMonthCalendarChangedListener) {
        this.onMonthCalendarChangedListener = onMonthCalendarChangedListener;
    }

    @Override
    protected void setDateTime(DateTime dateTime) {
        if (dateTime.getMillis() > endDateTime.getMillis() || dateTime.getMillis() < startDateTime.getMillis()) {
            Toast.makeText(getContext(), R.string.illegal_date, Toast.LENGTH_SHORT).show();
            return;
        }

        SparseArray<CalendarView> calendarViews = calendarAdapter.getCalendarViews();
        if (calendarViews.size() == 0) {
            return;
        }

        isPagerChanged = false;

        MonthView currectMonthView = getCurrectMonthView();
        DateTime initialDateTime = currectMonthView.getInitialDateTime();

        //不是当月
        if (!DateUtils.isEqualsMonth(initialDateTime, dateTime)) {
            int months = DateUtils.getIntervalMonths(initialDateTime, dateTime);
            int i = getCurrentItem() + months;
            setCurrentItem(i, Math.abs(months) < 2);
            currectMonthView = getCurrectMonthView();
        }

        currectMonthView.setDateTimeAndPoint(dateTime, pointList);
        mSelectDateTime = dateTime;
        lastSelectDateTime = dateTime;

        isPagerChanged = true;

        if (onMonthCalendarChangedListener != null) {
            onMonthCalendarChangedListener.onMonthCalendarChanged(mSelectDateTime);
        }


    }

    @Override
    public void onClickCurrentMonth(DateTime dateTime) {
        dealClickEvent(dateTime, getCurrentItem());
    }

    @Override
    public void onClickLastMonth(DateTime dateTime) {
        int currentItem = getCurrentItem() - 1;
        dealClickEvent(dateTime, currentItem);
    }

    @Override
    public void onClickNextMonth(DateTime dateTime) {
        int currentItem = getCurrentItem() + 1;
        dealClickEvent(dateTime, currentItem);
    }

    private void dealClickEvent(DateTime dateTime, int currentItem) {

        if (dateTime.getMillis() > endDateTime.getMillis() || dateTime.getMillis() < startDateTime.getMillis()) {
            Toast.makeText(getContext(), R.string.illegal_date, Toast.LENGTH_SHORT).show();
            return;
        }

        isPagerChanged = false;
        setCurrentItem(currentItem, true);
        MonthView nMonthView = getCurrectMonthView();
        nMonthView.setDateTimeAndPoint(dateTime, pointList);
        mSelectDateTime = dateTime;
        lastSelectDateTime = dateTime;

        isPagerChanged = true;

        if (onMonthCalendarChangedListener != null) {
            onMonthCalendarChangedListener.onMonthCalendarChanged(dateTime);
        }
    }


    public MonthView getCurrectMonthView() {
        return (MonthView) calendarAdapter.getCalendarViews().get(getCurrentItem());
    }

}
