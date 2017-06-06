package com.yimeng.yunma.lib.shop.ui;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;

import com.yimeng.yunma.lib.shop.R;
import com.yimeng.yunma.lib.shop.R2;
import com.yimeng.yunma.lib.shop.adapter.ShopAdapter;
import com.yimeng.yunma.lib.shop.model.PresentData;
import com.yimeng.yunma.lib.shop.mvp.SecondContract;
import com.yimeng.yunma.lib.shop.mvp.SecondModel;
import com.yimeng.yunma.lib.shop.mvp.SecondPresenter;
import com.yimeng.common.MVPActivity;
import com.yimeng.common.recycler.helper.RecyclerViewHelper;
import com.yimeng.common.recycler.listener.OnRequestDataListener;

import java.util.List;

import butterknife.BindView;

/**
*  Desc :
*  Create by WangPeng on 2017/6/5 0005
*/

public class SecondActivity extends MVPActivity<SecondPresenter> implements SecondContract.View {
    @BindView(R2.id.shopRecycler)
    RecyclerView shopRecycler;
    private ShopAdapter shopAdapter;

    @Override
    protected void createPresenter() {
        mvpPresenter = new SecondPresenter(this, new SecondModel());
    }

    @Override
    protected void initData() {
        mvpPresenter.loadGoods("1");
    }

    @Override
    public int getLayoutId() {
        return R.layout.shop_activity_second;
    }

    @Override
    public void setAdapter(List<PresentData> presentDatas) {
        shopAdapter = new ShopAdapter(this, presentDatas);
        RecyclerViewHelper.initRecyclerViewG(this, shopRecycler, shopAdapter, 2);
        shopAdapter.enableLoadMore(true);
        shopAdapter.setRequestDataListener(new OnRequestDataListener() {
            @Override
            public void onLoadMore() {
                System.out.println("加载更多...");
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("加载更多完成");
                        shopAdapter.loadComplete();
                        shopAdapter.noMoreData();
                    }
                }, 2000);
            }
        });
    }

}
