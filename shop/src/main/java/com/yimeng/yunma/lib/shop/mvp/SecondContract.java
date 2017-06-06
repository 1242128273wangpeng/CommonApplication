package com.yimeng.yunma.lib.shop.mvp;

import com.yimeng.yunma.lib.shop.model.PresentData;
import com.yimeng.yunma.lib.shop.model.PresentNetData;
import com.yimeng.common.base.BaseView;
import com.yimeng.common.listener.ModelCallBack;

import java.util.List;

/**
*  Desc :
*  Create by WangPeng on 2017/6/5 0005
*/

public interface SecondContract {
    interface View extends BaseView {
        void setAdapter(List<PresentData> presentDatas);
    }

    interface Model {
        void loadGoods(String pageSize, ModelCallBack<PresentNetData> callBack);
    }
}
