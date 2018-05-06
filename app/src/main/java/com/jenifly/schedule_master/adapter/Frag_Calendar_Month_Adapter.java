package com.jenifly.schedule_master.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jenifly.schedule_master.R;
import com.jenifly.schedule_master.utils.ScreenUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Frag_Calendar_Month_Adapter extends RecyclerView.Adapter<Frag_Calendar_Month_Adapter.viewHolder> implements View.OnClickListener
        ,ValueAnimator.AnimatorUpdateListener{

    private Context context;
    private ValueAnimator valueAnimator = new ValueAnimator(); //动画
    private int defaultHeight;
    private ViewGroup baseView;
    private ViewGroup.LayoutParams currentLp;
    private HashMap<Integer,Boolean> openOrClose = new HashMap<>();

    public Frag_Calendar_Month_Adapter(Context context) {
        this.context = context;
        defaultHeight = (int)ScreenUtils.dp2px(context,60);
        valueAnimator.addUpdateListener(this);
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.item_frag_calender_month, parent,false));
    }

    @Override
    public void onBindViewHolder(viewHolder holder, final int position) {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        holder.ly_title.setOnClickListener(this);
        int m = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        holder.itemView.measure(m, m);
        holder.ly_title.setTag(holder.itemView.getMeasuredHeight() + 66);
        holder.ly_title.setTag(R.id.id_item_frag_calendar_month_title_key, position);
        if(openOrClose.containsKey(position)){
            if(!openOrClose.get(position)){
                lp.height = defaultHeight;
            }else
                lp.height = holder.itemView.getMeasuredHeight();
        }else
            lp.height = defaultHeight;

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_frag_calendar_month_title:
                autoTelescopic(view);
                break;
        }
    }

    private void autoTelescopic(View view){
        baseView = (ViewGroup)view.getParent();
        int index = (int)view.getTag(R.id.id_item_frag_calendar_month_title_key);
        if(baseView.getHeight() > defaultHeight) {
            openOrClose.put(index,false);
            ((RelativeLayout)view).getChildAt(1).setVisibility(View.VISIBLE);
            valueAnimator.setIntValues((int) view.getTag(), defaultHeight);
            valueAnimator.setDuration(300);
        }else {
            ((RelativeLayout)view).getChildAt(1).setVisibility(View.INVISIBLE);
            openOrClose.put(index,true);
            valueAnimator.setIntValues(defaultHeight, (int) view.getTag());
            valueAnimator.setDuration(300);
        }
        currentLp = baseView.getLayoutParams();
        valueAnimator.start();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        currentLp.height = (int) valueAnimator.getAnimatedValue();
        baseView.setLayoutParams(currentLp);
    }

    class viewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fc_item_time) TextView textView;
        @BindView(R.id.item_frag_calendar_month_title) RelativeLayout ly_title;
        @BindView(R.id.item_frag_calendar_month_body) LinearLayout ly_body;

        public viewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}




