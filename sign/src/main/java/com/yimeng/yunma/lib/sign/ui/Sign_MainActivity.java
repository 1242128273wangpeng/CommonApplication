package com.yimeng.yunma.lib.sign.ui;

import android.content.Intent;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.github.mzule.activityrouter.annotation.Router;
import com.yimeng.yunma.lib.sign.R;
import com.yimeng.yunma.lib.sign.R2;
import com.yimeng.yunma.lib.sign.adapter.SignCalendarAdapter;
import com.yimeng.common.base.BaseActivity;
import com.yimeng.common.ultraviewpager.UltraViewPager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Desc:
 * Create by WangPeng on 2017/5/10 0010
 */
@Router(value="sign_mainActivity/:text",stringParams ="text")
public class Sign_MainActivity extends BaseActivity {
    @BindView(R2.id.calendars_ultra_vp)
    UltraViewPager calendarsUltraVp;
    @BindView(R2.id.tv)
    TextView tv;
    private SignCalendarAdapter signCalendarAdapter;

    @Override
    protected void initData() {
        signCalendarAdapter = new SignCalendarAdapter();
        calendarsUltraVp.setAdapter(signCalendarAdapter);
        calendarsUltraVp.initIndicator();
        calendarsUltraVp.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.GREEN)
                .setNormalColor(Color.WHITE)
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
        calendarsUltraVp.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        calendarsUltraVp.getIndicator().build();
        tv.setText(getIntent().getStringExtra("text"));
    }

    @Override
    public int getLayoutId() {
        return R.layout.sign_activity_main;
    }
   @OnClick({R2.id.tv})
    public void onClick(View view){
      if(view.getId()== R.id.tv){
          startActivity(new Intent(Sign_MainActivity.this,Sign_SecondActivity.class));
      }
    }
}
