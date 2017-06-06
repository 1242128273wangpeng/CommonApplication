package com.yimeng.yunma.lib.main.mvp;

import com.yimeng.yunma.lib.main.model.NewsData;
import com.yimeng.common.base.BasePresenter;
import com.yimeng.common.listener.ModelCallBack;

/**
 * Created by Administrator on 2017/5/2 0002.
 */

public class Test2Presenter extends BasePresenter<Test2Contract.Model, Test2Contract.View> implements ModelCallBack<NewsData> {
    public Test2Presenter(Test2Contract.View mvpView, Test2Contract.Model mModel) {
        super(mvpView, mModel);
    }

    public void load2Data(String apiKey, String keyWords) {
        mModel.loadData2(apiKey, keyWords, this);
    }

    @Override
    public void success(NewsData rootsData) {
        mvpView.showTest2Success(rootsData);
    }

    @Override
    public void fail(String error) {
        mvpView.showWarn("发生错误:" + error);
    }
}
