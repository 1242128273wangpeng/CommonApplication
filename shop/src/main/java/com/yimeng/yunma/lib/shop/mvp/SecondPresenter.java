package com.yimeng.yunma.lib.shop.mvp;

import com.yimeng.yunma.lib.shop.model.PresentData;
import com.yimeng.yunma.lib.shop.model.PresentNetData;
import com.yimeng.common.base.BasePresenter;
import com.yimeng.common.listener.ModelCallBack;

import java.util.ArrayList;
import java.util.List;

/**
*  Desc :
*  Create by WangPeng on 2017/6/5 0005
*/

public class SecondPresenter extends BasePresenter<SecondContract.Model, SecondContract.View> implements ModelCallBack<PresentNetData> {
    public SecondPresenter(SecondContract.View mvpView, SecondContract.Model mModel) {
        super(mvpView, mModel);
    }

    public void loadGoods(String pageSize) {
        mModel.loadGoods(pageSize, this);
    }

    @Override
    public void success(PresentNetData rootsData) {
        List<PresentData> presentDataList = transform(rootsData);
        mvpView.setAdapter(presentDataList);
    }

    @Override
    public void fail(String error) {
        mvpView.showWarn(error);
    }

    public List<PresentData> transform(PresentNetData presentNetData) {
        List<PresentData> presentDataList = new ArrayList<PresentData>();
        List<PresentNetData.Result> resultList = presentNetData.getResultMap().getResult();
        for (int i = 0; i < resultList.size(); i++) {
            PresentNetData.Result result = resultList.get(i);
            PresentData presentbean = new PresentData(String.valueOf(result.getId()), result.getImageUrl(), result.getGoodsName(), String.valueOf(result.getGoodsNum()), String.valueOf(result.getGoodsIntegral()), "");
            presentDataList.add(presentbean);
        }
        return presentDataList;
    }
}
