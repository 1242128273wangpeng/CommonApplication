package com.yimeng.yunma.lib.main.mvp;

import com.yimeng.yunma.lib.main.model.NewsData;
import com.yimeng.common.base.BaseView;
import com.yimeng.common.listener.ModelCallBack;

/**
 * Created by Administrator on 2017/5/4 0004.
 */

/**
 * 先创建Bean再来写Contract(考虑view要调用的方法，请求数据时传入的参数),写完Contract去写Presenter
 */
public interface Test3Contract {
    interface View extends BaseView {
        void setAdapter(NewsData newsData);
    }

    interface Model {
        // 这个方法中的参数都是由Presenter中的方法调用传入的
        void getNews(String apiKey, String keyWords, ModelCallBack<NewsData> callback);
    }
}
