package com.yimeng.yunma.lib.main.ui;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yimeng.yunma.lib.main.R;
import com.yimeng.yunma.lib.main.R2;
import com.yimeng.yunma.lib.main.adapter.NewAdapter;
import com.yimeng.yunma.lib.main.model.NewsData;
import com.yimeng.yunma.lib.main.mvp.Test3Contract;
import com.yimeng.yunma.lib.main.mvp.Test3Model;
import com.yimeng.yunma.lib.main.mvp.Test3Presenter;
import com.yimeng.common.MVPActivity;
import com.yimeng.common.recycler.helper.RecyclerViewHelper;
import com.yimeng.common.recycler.listener.OnRecyclerViewItemClickListener;
import com.yimeng.common.recycler.listener.OnRequestDataListener;
import com.yimeng.common.utils.ToastUtils;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/5/4 0004.
 */

public class Test3Activity extends MVPActivity<Test3Presenter> implements Test3Contract.View {
    @BindView(R2.id.recycler)
    RecyclerView recycler;
    private NewAdapter newAdapter;

    @Override
    protected void createPresenter() {
        mvpPresenter = new Test3Presenter(this, new Test3Model());
    }

    @Override
    protected void initData() {
        mvpPresenter.loadNewsForAdapter();
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showToast("empty");
            }
        }, 10000);
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_activity_test3;
    }

    @Override
    public void setAdapter(NewsData newsData) {
        newAdapter = new NewAdapter(this, newsData.getListbeans());
        if (newsData == null || newsData.getListbeans() == null || newsData.getListbeans().size() == 0) {
            View empty = this.getLayoutInflater().inflate(R.layout.main_empty_view, null, false);
            newAdapter.setEmptyView(empty);
        }
        RecyclerViewHelper.initRecyclerViewV(this, recycler, true, newAdapter);
        newAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.showToast("当前位置" + position);
            }
        });
        newAdapter.enableLoadMore(true);
        newAdapter.setRequestDataListener(new OnRequestDataListener() {
            @Override
            public void onLoadMore() {

            }
        });
    }

    @Override
    public void connect(boolean isConn) {
        if (isConn) {
            View empty = this.getLayoutInflater().inflate(R.layout.main_empty_view, null, false);
            if (newAdapter != null) {
                newAdapter.addHeaderView(empty);
            }
        } else {
            if (newAdapter != null && newAdapter.getHeaderView() != null) {
                newAdapter.removeItem(0);
            }
        }
    }
}
