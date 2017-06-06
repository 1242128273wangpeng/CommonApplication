package com.yimeng.common.base;

import com.yimeng.common.Presenter;

import org.greenrobot.eventbus.EventBus;

import rx.Subscription;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class BasePresenter<M, V extends BaseView> implements Presenter {
    protected V mvpView;
    protected M mModel;
    protected boolean mUseEventBus = false;
    protected Subscription mSubscription;

    public BasePresenter(V mvpView, M mModel) {
        this.mvpView = mvpView;
        this.mModel = mModel;
    }

    /**
     * 是否使用eventBus,默认为使用(true)，
     *
     * @return
     */
    public boolean setUseEventBus(boolean useEventBus) {
        this.mUseEventBus = useEventBus;
        return mUseEventBus;
    }

    @Override
    public void onPresenterStart() {
        //如果要使用eventbus请将此方法实现
        if (mUseEventBus)//如果要使用eventbus请将此方法返回true
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);//注册eventbus
            }
    }

    @Override
    public void onPresenterDestroy() {
        //如果要使用eventbus请将此方法实现
        if (mUseEventBus)//如果要使用eventbus请将此方法返回true
            EventBus.getDefault().unregister(this);//解除注册eventbus
        // 请求网络的时候会长时间持有Activity,Fragment导致内存泄露
        this.mModel = null;
        this.mvpView = null;
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

}
