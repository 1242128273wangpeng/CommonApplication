package com.yimeng.yunma.lib.shop.model;



import java.util.List;

/**
*  Desc :
*  Create by WangPeng on 2017/6/5 0005
*/
public class PresentNetData extends BasisData {
    private ResultMap resultMap;

    public ResultMap getResultMap() {
        return resultMap;
    }

    public void setResultMap(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    public class ResultMap {
        private int count;

        private List<Result> result;

        public void setCount(int count) {
            this.count = count;
        }

        public int getCount() {
            return this.count;
        }

        public void setResult(List<Result> result) {
            this.result = result;
        }

        public List<Result> getResult() {
            return this.result;
        }

    }

    public class Result {
        private String createTime;
        private int createUserId;
        private String displayName;
        private Long goodsIntegral;
        private String goodsName;
        private Long goodsNum;
        private int id;
        private String imageUrl;
        private int isDisplay;
        private String lastModifyTime;
        private Long lastModifyUserId;
        private int status;
        private String statusName;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public Long getGoodsIntegral() {
            return goodsIntegral;
        }

        public void setGoodsIntegral(Long goodsIntegral) {
            this.goodsIntegral = goodsIntegral;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public Long getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(Long goodsNum) {
            this.goodsNum = goodsNum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getIsDisplay() {
            return isDisplay;
        }

        public void setIsDisplay(int isDisplay) {
            this.isDisplay = isDisplay;
        }

        public String getLastModifyTime() {
            return lastModifyTime;
        }

        public void setLastModifyTime(String lastModifyTime) {
            this.lastModifyTime = lastModifyTime;
        }

        public Long getLastModifyUserId() {
            return lastModifyUserId;
        }

        public void setLastModifyUserId(Long lastModifyUserId) {
            this.lastModifyUserId = lastModifyUserId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }
    }
}
