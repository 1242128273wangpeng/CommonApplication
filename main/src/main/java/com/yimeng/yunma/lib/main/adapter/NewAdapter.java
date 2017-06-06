package com.yimeng.yunma.lib.main.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yimeng.yunma.lib.main.R;
import com.yimeng.yunma.lib.main.model.NewsBean;
import com.yimeng.common.recycler.adapter.BaseQuickAdapter;
import com.yimeng.common.recycler.adapter.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4 0004.
 */

public class NewAdapter extends BaseQuickAdapter<NewsBean> {
    public NewAdapter(Context context) {
        super(context);
    }
    public NewAdapter(Context context, List<NewsBean> data) {
        super(context, data);
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.main_item_news;
    }

    @Override
    protected void convert(BaseViewHolder holder, NewsBean item,int pos) {
       ImageView img  = holder.getView(R.id.img);
        Glide.with(mContext).load(item.getImgUrl()).into(img);
        holder.setText(R.id.title,item.getTitle());
        holder.setText(R.id.content,item.getContent());
    }
}
