package com.yimeng.yunma.lib.community.api;

import com.yimeng.yunma.lib.community.PublishBean;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Desc :
 * Created by WangPeng on 2017/6/1 0001.
 */

public interface ApiCommunity {
    /**
     * "status":1,     //1成功  0失败
     * "message":"获取数据成功",
     * "data":null
     */
// https://jyhfz.shanghaiyimeng.com/app/addComPost.do
//         imagelist = ""
//        "userid" -> "296"
//        "postContent" -> "Hi"
//        "postTitle" ->
//        "circleTypeid" -> "12"
//        "posttype" -> "1"
    @Multipart
    @POST("app/addComPost.do")
    Call<PublishBean> getPublishByCall(@PartMap Map<String, RequestBody> partMap, @Part MultipartBody.Part... files);

}
