package com.yimeng.yunma.lib.main.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.yimeng.yunma.lib.main.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class UltraPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mUrlLists;

    public UltraPagerAdapter(Context context, List<String> urlLists) {
        this.mContext = context;
        this.mUrlLists = urlLists;
    }

    @Override
    public int getCount() {
        return mUrlLists == null ? 0 : mUrlLists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.main_layout_child, null);
        //new LinearLayout(container.getContext());
        ImageView pager_img = (ImageView) linearLayout.findViewById(R.id.pager_imgview);
        Glide.with(mContext).load(mUrlLists.get(position)).into(pager_img);
        linearLayout.setId(R.id.main_item_id);
        switch (position) {
            case 0:
                linearLayout.setBackgroundColor(Color.parseColor("#2196F3"));
                break;
            case 1:
                linearLayout.setBackgroundColor(Color.parseColor("#673AB7"));
                break;
            case 2:
                linearLayout.setBackgroundColor(Color.parseColor("#009688"));
                break;
        }
        container.addView(linearLayout);
        return linearLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        LinearLayout view = (LinearLayout) object;
        container.removeView(view);
    }
}
