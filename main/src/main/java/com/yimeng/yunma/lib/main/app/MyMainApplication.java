package com.yimeng.yunma.lib.main.app;

import android.app.Application;

import com.apkfuns.logutils.LogUtils;
import com.yimeng.yunma.lib.main.BuildConfig;
import com.yimeng.yunma.lib.main.MainConstants;
import com.squareup.leakcanary.LeakCanary;
import com.yimeng.common.net.AppClient;

/**
 * Created by wang2014 on 2017/5/2 0002.
 */

public class MyMainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppClient.init(this, MainConstants.BASEURL);
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
            LogUtils.configAllowLog = true;
        }
    }
}
