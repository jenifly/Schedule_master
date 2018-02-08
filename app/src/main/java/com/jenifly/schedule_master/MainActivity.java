package com.jenifly.schedule_master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jenifly.schedule_master.view.BlurringView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_timeline_bg) BlurringView main_timeline_bg;
    @BindView(R.id.main_bgiv)
    ImageView main_bgiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        main_timeline_bg.setBlurredView(main_bgiv);
        main_timeline_bg.invalidate();
    }

}
