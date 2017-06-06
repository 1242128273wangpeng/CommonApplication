package com.yimeng.yunma.lib.shop.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yimeng.yunma.lib.shop.R;
import com.yimeng.yunma.lib.shop.model.PresentData;
import com.yimeng.common.recycler.adapter.BaseQuickAdapter;
import com.yimeng.common.recycler.adapter.BaseViewHolder;

import java.util.List;

/**
*  Desc :
*  Create by WangPeng on 2017/6/5 0005
*/

public class ShopAdapter extends BaseQuickAdapter<PresentData> {
    public ShopAdapter(Context context) {
        super(context);
    }

    public ShopAdapter(Context context, List<PresentData> data) {
        super(context, data);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.shop_item_socresexchange;
    }

    @Override
    protected void convert(BaseViewHolder holder, PresentData item, int position) {
        holder.setText(R.id.presentinfotv, item.getPresentinfo());
        holder.setText(R.id.inventory, "(库存" + item.getPresentscores() + "件)");
        holder.setText(R.id.presentscorestv, item.getPresentnum() + "分");
        if (position % 2 == 0) {
            holder.setBackgroundRes(R.id.scoresrootbg, R.drawable.shop_sorces_goods_bg);
        } else {
            holder.setBackgroundRes(R.id.scoresrootbg, R.drawable.shop_sorces_goods_selector);
        }
        if (Integer.valueOf(item.getPresentscores()) <= 0) {
            TextView textView0 = holder.getView(R.id.exchangebut);
            textView0.setBackgroundResource(R.drawable.shop_sure_grey);
            textView0.setOnClickListener(null);
        } else {
            TextView textView1 = holder.getView(R.id.exchangebut);
            textView1.setBackgroundResource(R.drawable.shop_pinkbutbg);
            textView1.setClickable(true);
            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Intent传递数据
//                    PresentData presentData = item;
//                    Intent intent = new Intent(mContext, ConfirmOrderActivity.class);
//                    intent.putExtra("presentData", presentData);
//                    mContext.startActivity(intent);
                }
            });
        }
        ImageView img = holder.getView(R.id.presentimg);
        Glide.with(mContext).load(item.getPresentimg()).fitCenter().crossFade().into(img);
    }
}
