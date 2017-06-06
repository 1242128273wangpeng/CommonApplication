package com.yimeng.yunma.lib.main.mvp;
import com.yimeng.yunma.lib.main.api.ApiService;
import com.yimeng.yunma.lib.main.model.RootsData;
import com.yimeng.common.listener.ModelCallBack;
import com.yimeng.common.net.AppClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wang2014 on 2017/4/24 0024.
 */

public class TestModel implements TestContract.Model {

    @Override
    public void loadData(String apiKey, final ModelCallBack<RootsData> callBack) {
        AppClient.getInstance(ApiService.class).getNewsByCall(apiKey).enqueue(new Callback<RootsData>() {
            @Override
            public void onResponse(Call<RootsData> call, Response<RootsData> response) {
                if (response.isSuccessful()) {
                    callBack.success(response.body());
                }
            }

            @Override
            public void onFailure(Call<RootsData> call, Throwable t) {
                callBack.fail(t.getMessage());
            }
        });
    }
}
