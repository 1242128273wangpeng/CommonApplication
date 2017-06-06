package com.yimeng.yunma.lib.shop.model;

import java.io.Serializable;

/**
*  Desc :
*  Create by WangPeng on 2017/6/5 0005
*/
public class AddressData implements Serializable {
    private String id;
    private String name;
    private String phone;
    private String addrses;
    private String status;

    public AddressData() {
    }

    public AddressData(String name, String phone, String addrses, String status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.addrses = addrses;
        this.status = status;
    }

    public AddressData(String id, String name, String phone, String addrses, String status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.addrses = addrses;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getAddrses() {
        return addrses;
    }

    public void setAddrses(String addrses) {
        this.addrses = addrses;
    }

    @Override
    public String toString() {
        return "AddressData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", addrses='" + addrses + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
