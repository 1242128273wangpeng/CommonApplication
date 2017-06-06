package com.yimeng.yunma.lib.main.mvp;

import com.yimeng.yunma.lib.main.model.DownloadBean;
import com.yimeng.common.base.BaseView;
import com.yimeng.common.listener.ModelCallBack;

/**
 * Desc :
 * Created by WangPeng on 2017/5/24 0024.
 */

public interface DownloadContract {
    public interface DownloadView extends BaseView {
        public void showSuccess(DownloadBean downloadBean);
    }

    public interface DownloadModel {
        public void getDownloadURl(ModelCallBack<DownloadBean> callback);
    }
}
