package com.yimeng.yunma.lib.shop.mvp;

import com.yimeng.yunma.lib.shop.api.ApiShop;
import com.yimeng.yunma.lib.shop.model.PresentNetData;
import com.yimeng.common.listener.ModelCallBack;
import com.yimeng.common.net.AppClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
*  Desc :
*  Create by WangPeng on 2017/6/5 0005
*/

public class SecondModel implements SecondContract.Model {
    @Override
    public void loadGoods(String pageSize, final ModelCallBack<PresentNetData> callBack) {
        AppClient.getInstance(ApiShop.class).getNewsByCall(pageSize).enqueue(new Callback<PresentNetData>() {
            @Override
            public void onResponse(Call<PresentNetData> call, Response<PresentNetData> response) {
                if (response.isSuccessful()) {
                    callBack.success(response.body());
                }else{
                    try {
                        callBack.fail(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<PresentNetData> call, Throwable t) {
                callBack.fail(t.getMessage());
            }
        });
    }
}
