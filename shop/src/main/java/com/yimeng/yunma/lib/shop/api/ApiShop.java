package com.yimeng.yunma.lib.shop.api;

import com.yimeng.yunma.lib.shop.model.PresentNetData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
*  Desc :
*  Create by WangPeng on 2017/6/5 0005
*/
public interface ApiShop {
    @GET("app/goodsList.do")
    Call<PresentNetData> getNewsByCall(@Query("pageSize") String pageSize);

    @GET("app/goodsList.do")
    Observable<PresentNetData> getNews(@Query("pageSize") String pageSize);
}
