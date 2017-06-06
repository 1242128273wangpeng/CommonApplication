package com.yimeng.yunma.lib.shop.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/6/23.
 */
public class BasisData {
    @SerializedName("resultCode")
    private String resultCode;
    @SerializedName("msg")
    private String msg;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
