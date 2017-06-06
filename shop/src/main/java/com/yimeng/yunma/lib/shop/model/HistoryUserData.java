package com.yimeng.yunma.lib.shop.model;

/**
*  Desc :
*  Create by WangPeng on 2017/6/5 0005
*/
public class HistoryUserData {
    private String name;
    private String phone;
    private String qq;

    public HistoryUserData() {
    }

    public HistoryUserData(String name, String phone, String qq) {
        this.name = name;
        this.phone = phone;
        this.qq = qq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Override
    public String toString() {
        return "HistoryUserData{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", qq='" + qq + '\'' +
                '}';
    }
}
