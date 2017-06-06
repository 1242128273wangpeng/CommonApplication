package com.yimeng.yunma.lib.shop.mvp;

import com.yimeng.yunma.lib.shop.model.PresentData;
import com.yimeng.yunma.lib.shop.model.PresentNetData;
import com.yimeng.common.base.BasePresenter;
import com.yimeng.common.rx.RxManager;
import com.yimeng.common.rx.RxSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc :
 * Created by WangPeng on 2017/6/2 0002.
 */

public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
    public MainPresenter(MainContract.View mvpView, MainContract.Model mModel) {
        super(mvpView, mModel);
    }

    public void loadGoods(String pageSize) {
        mSubscription = RxManager.getInstance().doSubscribe( mModel.loadGoods(pageSize), new RxSubscriber<PresentNetData>(false) {
            @Override
            protected void _onNext(PresentNetData data) {
                mvpView.setAdapter(transform(data));
            }

            @Override
            protected void _onError(String error) {
                mvpView.showWarn(error);
            }
        });
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
