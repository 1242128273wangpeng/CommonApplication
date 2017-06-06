package com.yimeng.yunma.lib.shop.mvp;

import com.yimeng.yunma.lib.shop.api.ApiShop;
import com.yimeng.yunma.lib.shop.model.PresentNetData;
import com.yimeng.common.net.AppClient;

import rx.Observable;

/**
 * Desc :
 * Created by WangPeng on 2017/6/2 0002.
 */

public class MainModel implements MainContract.Model {
    @Override
    public Observable<PresentNetData> loadGoods(String pageSize) {
        return AppClient.getInstance(ApiShop.class).getNews(pageSize);
    }
}
