package com.yimeng.yunma.lib.main.api;


import com.yimeng.yunma.lib.main.model.DownloadBean;
import com.yimeng.yunma.lib.main.model.NewsData;
import com.yimeng.yunma.lib.main.model.RootsData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 2014wang on 2016/10/6   @desc
 */
public interface ApiService {
    @GET("news/words")
    Call<RootsData> getNewsByCall(@Query("key") String apiKey);

    @GET("news/query")
    Call<NewsData> getDetailsByCall(@Query("key") String apiKey, @Query("q") String keyword);

    @GET("app/version.do")
    Call<DownloadBean> getDownloadByCall();
}
