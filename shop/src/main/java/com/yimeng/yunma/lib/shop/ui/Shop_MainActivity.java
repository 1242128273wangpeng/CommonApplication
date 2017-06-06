package com.yimeng.yunma.lib.shop.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.mzule.activityrouter.annotation.Router;
import com.yimeng.common.MVPActivity;
import com.yimeng.common.recycler.helper.RecyclerViewHelper;
import com.yimeng.common.recycler.listener.OnRequestDataListener;
import com.yimeng.yunma.lib.shop.R;
import com.yimeng.yunma.lib.shop.R2;
import com.yimeng.yunma.lib.shop.adapter.ShopAdapter;
import com.yimeng.yunma.lib.shop.model.PresentData;
import com.yimeng.yunma.lib.shop.mvp.MainContract;
import com.yimeng.yunma.lib.shop.mvp.MainModel;
import com.yimeng.yunma.lib.shop.mvp.MainPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Router(value = "shop_mainActivity")
public class Shop_MainActivity extends MVPActivity<MainPresenter> implements MainContract.View {
    @BindView(R2.id.shopText)
    TextView shopText;
    @BindView((R2.id.mainShopRecycler))
    RecyclerView mainShopRecycler;
    private ShopAdapter shopAdapter;

    @Override
    protected void createPresenter() {
        mvpPresenter = new MainPresenter(this, new MainModel());
    }

    @Override
    protected void initData() {
        shopText.setTextSize(22f);
        shopText.setTextColor(Color.BLUE);
        String color = getIntent().getStringExtra("color");
        String id = getIntent().getStringExtra("id");
        shopText.setText(color);
        shopText.append("-" + id);
        mvpPresenter.loadGoods("1");
        shopAdapter = new ShopAdapter(this, null);
    }

    @Override
    public int getLayoutId() {
        return R.layout.shop_activity_main;
    }

    @OnClick({R2.id.shopText})
    public void OnClick(View view) {
        if (view.getId() == R.id.shopText) {
            startActivity(new Intent(this, SecondActivity.class));
        }
    }

    @Override
    public void setAdapter(List<PresentData> presentDatas) {
        shopAdapter.updateItems(presentDatas);
        RecyclerViewHelper.initRecyclerViewG(this, mainShopRecycler, shopAdapter, 2);
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
