package com.yimeng.yunma.lib.shop.app;

import android.app.Application;

import com.apkfuns.logutils.LogUtils;
import com.yimeng.yunma.lib.shop.BuildConfig;
import com.yimeng.yunma.lib.shop.ShopConstants;
import com.squareup.leakcanary.LeakCanary;
import com.yimeng.common.net.AppClient;
import com.yimeng.common.utils.ToastUtils;

/**
*  Desc :
*  Create by WangPeng on 2017/6/5 0005
*/

public class MyShopApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppClient.init(this, ShopConstants.BASEURL);
        ToastUtils.init(this);
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
            LogUtils.configAllowLog = true;
        }
    }
}
