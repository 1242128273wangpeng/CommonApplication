package com.yimeng.yunma.lib.community;

import com.google.gson.annotations.SerializedName;

/**
 * Desc :
 * Created by WangPeng on 2017/5/27 0027.
 */

public class PublishBean {
    /**
     * status : 1
     * message : 获取数据成功
     * data : null
     */

    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PublishBean{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
