package com.yimeng.yunma.newchangzhou.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yimeng.common.R;
import com.yimeng.common.utils.DisplayUtil;
import com.yimeng.yunma.newchangzhou.ui.MainActivity;

/**
 * Desc :
 * Created by WangPeng on 2017/5/23 0023.
 */

public class WelcomePagerAdapter extends PagerAdapter {
    private Context mContext;
    private int[] mResLists;

    public WelcomePagerAdapter(Context context, int[] resLists) {
        this.mContext = context;
        this.mResLists = resLists;
    }

    @Override
    public int getCount() {
        return mResLists == null ? 0 : mResLists.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        FrameLayout frameLayout = new FrameLayout(mContext);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        ImageView pager_img = new ImageView(mContext);
        pager_img.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        pager_img.setImageResource(mResLists[position]);
        pager_img.setPadding(10,4,10,4);
        pager_img.setScaleType(ImageView.ScaleType.FIT_XY);
        frameLayout.addView(pager_img);
        if (position == mResLists.length-1) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            Button enter = new Button(mContext);
            enter.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
            enter.setText("进入");
            enter.setTextColor(Color.WHITE);
            enter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Routers.open(mContext,"module://main_mainActivity");
                    mContext.startActivity(new Intent(mContext,MainActivity.class));
                }
            });
            layoutParams.gravity = Gravity.BOTTOM;
            layoutParams.setMargins((DisplayUtil.getScreenWidth(mContext)-enter.getMeasuredWidth())/2,0,0,50);
            frameLayout.addView(enter,layoutParams);
        }
        container.addView(frameLayout);
        return frameLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        FrameLayout frameLayout = (FrameLayout) object;
        container.removeView(frameLayout);
    }

}
