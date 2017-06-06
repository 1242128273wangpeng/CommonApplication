package com.yimeng.yunma.lib.shop.mvp;

import com.yimeng.yunma.lib.shop.model.PresentData;
import com.yimeng.yunma.lib.shop.model.PresentNetData;
import com.yimeng.common.base.BaseView;

import java.util.List;

import rx.Observable;

/**
 * Desc :
 * Created by WangPeng on 2017/6/2 0002.
 */

public interface MainContract {
    interface View extends BaseView {
        void setAdapter(List<PresentData> presentDatas);
    }

    interface Model {
        Observable<PresentNetData> loadGoods(String pageSize);
    }
}
