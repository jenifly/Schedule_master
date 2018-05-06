package com.jenifly.schedule_master.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jenifly.schedule_master.R;
import com.jenifly.schedule_master.view.multichoicescirclebutton.MultiChoicesCircleButton;
import com.jenifly.schedule_master.view.verticalViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jenifly on 2018/2/11.
 */

public class Fragment_TimeBoarde extends Fragment {

    @BindView(R.id.mainviewpage) verticalViewPager mViewPager;
    //@BindView(R.id.viewPagerTag) ViewPagerTag viewPagerTag;
    @BindView(R.id.delpage) ImageView delpage;
    @BindView(R.id.addnewpage) ImageView addnewpage;
    @BindView(R.id.returnmain) ImageView returnmain;
    @BindView(R.id.mianpage) ImageView mianpage;
    @BindView(R.id.pagebarlt) LinearLayout pagebarlt;
    @BindView(R.id.llayoutviewpage) RelativeLayout llayoutviewpage;
    @BindView(R.id.multiChoicesCircleButton) MultiChoicesCircleButton multiChoicesCircleButton;

/*    private final static int RESUTECODE = 0;
    private fragAdapter fragPagerAdapter;
    private DisplayMetrics dm2;
    private boolean isRunTimer = false;
    private boolean currentIsFull = true;
    private int mainpageindex;
    private int mLastPos = -1;
    private Runnable mBlurRunnable;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            EventBus.getDefault().post(new secondEvent());
            handler.postDelayed(this,1000);
        }
    };*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeboarde, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init(){
        List<MultiChoicesCircleButton.Item> buttonItems = new ArrayList<>();
        MultiChoicesCircleButton.Item item1 = new MultiChoicesCircleButton.Item("背景选择", getContext().getResources().getDrawable(R.mipmap.ic_pic), 30);
        MultiChoicesCircleButton.Item item2 = new MultiChoicesCircleButton.Item("关于", getContext().getResources().getDrawable(R.mipmap.ic_smilingface), 90);
        MultiChoicesCircleButton.Item item3 = new MultiChoicesCircleButton.Item("文字设置", getContext().getResources().getDrawable(R.mipmap.ic_text), 150);
        MultiChoicesCircleButton.Item item4 = new MultiChoicesCircleButton.Item("相恋时间",getContext().getResources().getDrawable(R.mipmap.ic_time), 30);
        MultiChoicesCircleButton.Item item5 = new MultiChoicesCircleButton.Item("其他设置", getContext().getResources().getDrawable(R.mipmap.ic_setting), 150);
        buttonItems.add(item1);
        buttonItems.add(item2);
        buttonItems.add(item3);
        buttonItems.add(item4);
        buttonItems.add(item5);
        multiChoicesCircleButton.setButtonItems(buttonItems);
    }
}
