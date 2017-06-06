package com.yimeng.yunma.lib.main.mvp;

import com.yimeng.yunma.lib.main.MainConstants;
import com.yimeng.yunma.lib.main.model.NewsData;
import com.yimeng.common.base.BasePresenter;
import com.yimeng.common.listener.ModelCallBack;

/**
 * Created by Administrator on 2017/5/4 0004.
 */

/**
 * 写完Presenter之后再去写Model的具体实现,
 */
public class Test3Presenter extends BasePresenter<Test3Contract.Model, Test3Contract.View> implements ModelCallBack<NewsData> {

    public Test3Presenter(Test3Contract.View mvpView, Test3Contract.Model mModel) {
        super(mvpView, mModel);
    }

    // 这个方法是由Activity或者是Fragment传入的
    public void loadNewsForAdapter() {
        mModel.getNews(MainConstants.APIKEY, "苹果股价再创新高", this);
    }

    @Override
    public void success(NewsData rootsData) {
        mvpView.setAdapter(rootsData);
    }

    @Override
    public void fail(String error) {
        mvpView.showWarn(error);
    }
}
