package com.yimeng.yunma.lib.shop.model;

/**
*  Desc :
*  Create by WangPeng on 2017/6/5 0005
*/
public class OrderData {
    private PresentData presentData;
    private String orderid;
    private String date;
    private String logisticsid;

    public OrderData() {
    }

    public OrderData(PresentData presentData, String orderid, String date, String logisticsid) {
        this.presentData = presentData;
        this.orderid = orderid;
        this.date = date;
        this.logisticsid = logisticsid;
    }

    public PresentData getPresentData() {
        return presentData;
    }

    public void setPresentData(PresentData presentData) {
        this.presentData = presentData;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLogisticsid() {
        return logisticsid;
    }

    public void setLogisticsid(String logisticsid) {
        this.logisticsid = logisticsid;
    }

    @Override
    public String toString() {
        return "OrderData{" +
                "presentData=" + presentData +
                ", orderid='" + orderid + '\'' +
                ", date='" + date + '\'' +
                ", logisticsid='" + logisticsid + '\'' +
                '}';
    }
}
