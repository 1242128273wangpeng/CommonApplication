package com.yimeng.yunma.lib.shop.model;



import java.util.ArrayList;

/**
*  Desc :
*  Create by WangPeng on 2017/6/5 0005
*/
public class OrderNetData extends BasisData {
    private ResultMap resultMap;

    public ResultMap getResultMap() {
        return resultMap;
    }

    public void setResultMap(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    public class ResultMap {
        private int count;

        private ArrayList<Result> result;

        public void setCount(int count) {
            this.count = count;
        }

        public int getCount() {
            return this.count;
        }

        public void setResult(ArrayList<Result> result) {
            this.result = result;
        }

        public ArrayList<Result> getResult() {
            return this.result;
        }

        @Override
        public String toString() {
            return "ResultMap{" +
                    "count=" + count +
                    ", result=" + result +
                    '}';
        }

        public class Result {
            private String goods_name; // 商品名称
            private String goods_num; // 库存
            private String goodsIntegral; // 物品单价
            private String number; // 购买的数量
            private String orderNo;// 订单号
            private String flag; // 是否由医院派送还是，本人领取
            private String createTime; // 订单创建时间
            private String id; //订单的ID
            private String imgUrl; // 商品图片路径
            private String logistics; // 物流单号（不一定有，选择派送之后才有）
            private String status; // 商品物流状态

            public String getGoods_name() {
                return goods_name;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public String getGoodsIntegral() {
                return goodsIntegral;
            }

            public void setGoodsIntegral(String goodsIntegral) {
                this.goodsIntegral = goodsIntegral;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getFlag() {
                return flag;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getLogistics() {
                return logistics;
            }

            public void setLogistics(String logistics) {
                this.logistics = logistics;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            @Override
            public String toString() {
                return "Result{" +
                        "goods_name='" + goods_name + '\'' +
                        ", goods_num='" + goods_num + '\'' +
                        ", goodsIntegral='" + goodsIntegral + '\'' +
                        ", number='" + number + '\'' +
                        ", flag='" + flag + '\'' +
                        ", createTime='" + createTime + '\'' +
                        ", id='" + id + '\'' +
                        ", imgUrl='" + imgUrl + '\'' +
                        ", logistics='" + logistics + '\'' +
                        ", status='" + status + '\'' +
                        '}';
            }
        }

    }
}
