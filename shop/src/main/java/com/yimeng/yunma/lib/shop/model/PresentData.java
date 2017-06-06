package com.yimeng.yunma.lib.shop.model;



import java.io.Serializable;

/**
*  Desc :
*  Create by WangPeng on 2017/6/5 0005
*/
public class PresentData extends BasisData implements Serializable {
    private String presentid; // 订单的ID
    private String presentimg; // 商品图片
    private String presentinfo; //商品名字
    private String presentscores; // 商品单价
    private String presentnum; // 商品数量
    public PresentData() {
    }


    public PresentData(String presentid, String presentimg, String presentinfo, String presentscores, String presentnum, String presentlogistics) {
        this.presentid = presentid;
        this.presentimg = presentimg;
        this.presentinfo = presentinfo;
        this.presentscores = presentscores;
        this.presentnum = presentnum;
    }

    public String getPresentid() {
        return presentid;
    }

    public void setPresentid(String presentid) {
        this.presentid = presentid;
    }

    public String getPresentimg() {
        return presentimg;
    }

    public void setPresentimg(String presentimg) {
        this.presentimg = presentimg;
    }

    public String getPresentinfo() {
        return presentinfo;
    }

    public void setPresentinfo(String presentinfo) {
        this.presentinfo = presentinfo;
    }

    public String getPresentscores() {
        return presentscores;
    }

    public void setPresentscores(String presentscores) {
        this.presentscores = presentscores;
    }

    public String getPresentnum() {
        return presentnum;
    }

    public void setPresentnum(String presentnum) {
        this.presentnum = presentnum;
    }

    @Override
    public String toString() {
        return "PresentData{" +
                "presentid='" + presentid + '\'' +
                ", presentimg='" + presentimg + '\'' +
                ", presentinfo='" + presentinfo + '\'' +
                ", presentscores='" + presentscores + '\'' +
                ", presentnum='" + presentnum + '\'' +
                '}';
    }
}
