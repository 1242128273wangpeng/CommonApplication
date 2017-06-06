package com.yimeng.yunma.lib.main.mvp;

import com.yimeng.yunma.lib.main.api.ApiService;
import com.yimeng.yunma.lib.main.model.DownloadBean;
import com.yimeng.common.listener.ModelCallBack;
import com.yimeng.common.net.AppClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Desc :
 * Created by WangPeng on 2017/5/24 0024.
 */

public class DownloadModel implements DownloadContract.DownloadModel {
    @Override
    public void getDownloadURl(final ModelCallBack<DownloadBean> callback) {
        AppClient.getInstance(ApiService.class).getDownloadByCall().enqueue(new Callback<DownloadBean>() {
            @Override
            public void onResponse(Call<DownloadBean> call, Response<DownloadBean> response) {
                if (response.isSuccessful()) {
                    callback.success(response.body());
                } else {
                    try {
                        callback.fail(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<DownloadBean> call, Throwable t) {
                callback.fail(t.getMessage());
            }
        });
    }
}
