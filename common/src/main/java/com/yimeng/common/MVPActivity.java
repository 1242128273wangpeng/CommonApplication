package com.yimeng.common;

import android.os.Bundle;

import com.yimeng.common.base.BaseActivity;
import com.yimeng.common.base.BasePresenter;
import com.yimeng.common.base.BaseView;
import com.yimeng.common.utils.ToastUtils;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public abstract class MVPActivity<P extends BasePresenter> extends BaseActivity implements BaseView {
    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        createPresenter();
        super.onCreate(savedInstanceState);
        mvpPresenter.onPresenterStart();
    }

    protected abstract void createPresenter();

    @Override
    protected void onDestroy() {
        if (mvpPresenter != null) {
            mvpPresenter.onPresenterDestroy();
        }
        super.onDestroy();
    }

    @Override
    public void showWarn(String message) {
        ToastUtils.showToast("message:"+message);
    }
}
