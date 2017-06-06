package com.yimeng.yunma.lib.main.model;

import com.google.gson.annotations.SerializedName;

/**
 * Desc :
 * Created by WangPeng on 2017/5/24 0024.
 */

public class DownloadBean {

    /**
     * msg : null
     * resultCode : 0
     * resultMap : {"force_update":0,"id":29,"type":"Android","url":"http://jyhfz.shanghaiyimeng.com/upload/downloads/android/20170411/71223f36-b3b2-4a7c-8a52-e19e78ccbba029514.apk","version":"1.0.2"}
     */

    @SerializedName("msg")
    private Object msg;
    @SerializedName("resultCode")
    private String resultCode;
    @SerializedName("resultMap")
    private ResultMapBean resultMap;

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public ResultMapBean getResultMap() {
        return resultMap;
    }

    public void setResultMap(ResultMapBean resultMap) {
        this.resultMap = resultMap;
    }

    @Override
    public String toString() {
        return "DownloadBean{" +
                "msg=" + msg +
                ", resultCode='" + resultCode + '\'' +
                ", resultMap=" + resultMap.toString() +
                '}';
    }

    public static class ResultMapBean {
        /**
         * force_update : 0
         * id : 29
         * type : Android
         * url : http://jyhfz.shanghaiyimeng.com/upload/downloads/android/20170411/71223f36-b3b2-4a7c-8a52-e19e78ccbba029514.apk
         * version : 1.0.2
         */

        @SerializedName("force_update")
        private int forceUpdate;
        @SerializedName("id")
        private int id;
        @SerializedName("type")
        private String type;
        @SerializedName("url")
        private String url;
        @SerializedName("version")
        private String version;

        public int getForceUpdate() {
            return forceUpdate;
        }

        public void setForceUpdate(int forceUpdate) {
            this.forceUpdate = forceUpdate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        @Override
        public String toString() {
            return "ResultMapBean{" +
                    "forceUpdate=" + forceUpdate +
                    ", id=" + id +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", version='" + version + '\'' +
                    '}';
        }
    }
}
