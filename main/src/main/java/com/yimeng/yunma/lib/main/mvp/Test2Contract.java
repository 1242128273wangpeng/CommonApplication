package com.yimeng.yunma.lib.main.mvp;

import com.yimeng.yunma.lib.main.model.NewsData;
import com.yimeng.common.base.BaseView;
import com.yimeng.common.listener.ModelCallBack;

/**
 * Created by Administrator on 2017/5/2 0002.
 */

public interface Test2Contract {
    interface View extends BaseView {
        void showTest2Success(NewsData rootsData);
    }

    interface Model {
        void loadData2(String apiKey, String keyWords, ModelCallBack<NewsData> callback);
    }
}
