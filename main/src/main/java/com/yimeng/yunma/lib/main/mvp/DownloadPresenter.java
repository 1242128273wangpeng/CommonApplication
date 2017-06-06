package com.yimeng.yunma.lib.main.mvp;

import com.yimeng.yunma.lib.main.model.DownloadBean;
import com.yimeng.common.base.BasePresenter;
import com.yimeng.common.listener.ModelCallBack;

/**
 * Desc :
 * Created by WangPeng on 2017/5/24 0024.
 */

public class DownloadPresenter extends BasePresenter<DownloadContract.DownloadModel, DownloadContract.DownloadView> implements ModelCallBack<DownloadBean> {

    public DownloadPresenter(DownloadContract.DownloadView mvpView, DownloadContract.DownloadModel mModel) {
        super(mvpView, mModel);
    }

    public void getDownloadURL() {
        mModel.getDownloadURl(this);
    }

    @Override
    public void success(DownloadBean downloadBean) {
        mvpView.showSuccess(downloadBean);
    }

    @Override
    public void fail(String error) {
        mvpView.showWarn(error);
    }
}
