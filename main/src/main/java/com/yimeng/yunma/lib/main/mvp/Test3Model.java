package com.yimeng.yunma.lib.main.mvp;
import com.yimeng.yunma.lib.main.api.ApiService;
import com.yimeng.yunma.lib.main.model.NewsData;
import com.yimeng.common.listener.ModelCallBack;
import com.yimeng.common.net.AppClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/5/4 0004.
 */

public class Test3Model implements Test3Contract.Model {
    @Override
    public void getNews(String apiKey, String keyWords, final ModelCallBack<NewsData> callback) {
        AppClient.getInstance(ApiService.class).getDetailsByCall(apiKey,keyWords).enqueue(new Callback<NewsData>() {
            @Override
            public void onResponse(Call<NewsData> call, Response<NewsData> response) {
                if(response.isSuccessful()){
                    callback.success(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewsData> call, Throwable t) {
                callback.fail(t.getMessage());
            }
        });
    }
}
