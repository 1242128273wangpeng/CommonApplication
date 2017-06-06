package com.yimeng.yunma.lib.main.mvp;

import com.yimeng.yunma.lib.main.model.NoticeEvent;
import com.yimeng.yunma.lib.main.model.RootsData;
import com.yimeng.common.base.BasePresenter;
import com.yimeng.common.listener.ModelCallBack;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by wang2014 on 2017/4/24 0024.
 */

public class TestPresenter extends BasePresenter<TestContract.Model, TestContract.View> implements ModelCallBack<RootsData> {

    public TestPresenter(TestContract.View mvpView, TestContract.Model mModel) {
        super(mvpView, mModel);
    }

    public void loadData(String apiKey) {
        mModel.loadData(apiKey, this);
    }

    @Override
    public void success(RootsData rootsData) {
        mvpView.showSuccess(rootsData);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveNotice(NoticeEvent noticeEvent) {
        mvpView.showWarn("come in Test2 receiveNotice" + Thread.currentThread().getName());
    }

    @Override
    public void fail(String error) {
        mvpView.showWarn("发生错误:" + error);
    }
}
