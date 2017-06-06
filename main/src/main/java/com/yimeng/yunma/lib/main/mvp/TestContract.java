package com.yimeng.yunma.lib.main.mvp;

import com.yimeng.yunma.lib.main.model.RootsData;
import com.yimeng.common.base.BaseView;
import com.yimeng.common.listener.ModelCallBack;

/**
 * Created by wang2014 on 2017/4/24 0024.
 */

public interface TestContract {
    interface View extends BaseView {
        void showSuccess(RootsData rootsData);
    }

    interface Model {
        void loadData(String params, ModelCallBack<RootsData> callBack);
    }

}
