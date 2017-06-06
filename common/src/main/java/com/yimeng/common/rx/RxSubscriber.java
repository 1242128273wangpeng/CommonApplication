package com.yimeng.common.rx;

import android.content.Context;
import android.widget.Toast;

import com.yimeng.common.application.CommonApplication;

import java.io.IOException;

import rx.Subscriber;

/**
 * Desc :
 * Create by WangPeng on 2017/6/2 0002
 */
public abstract class RxSubscriber<T> extends Subscriber<T> {
    private Context mContext;
    private boolean mIsShowLoading;//是否显示加载loading

    public RxSubscriber(boolean isShowLoading) {
        mContext = CommonApplication.getContext();
        mIsShowLoading = isShowLoading;
    }

    @Override
    public void onCompleted() {
        cancelLoading();
    }

    @Override
    public void onError(Throwable e) {
        //统一处理请求异常的情况
        if (e instanceof IOException) {
            Toast.makeText(mContext, "网络链接异常...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        _onError(e.getMessage());
        cancelLoading();
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onStart() {
        super.onStart();
        showLoading();
    }

    /**
     * 可在此处统一显示loading view
     */
    private void showLoading() {
        if (mIsShowLoading) {
        }
    }

    private void cancelLoading() {
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String error);

}
