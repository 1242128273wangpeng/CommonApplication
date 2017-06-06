package com.yimeng.yunma.lib.sign.app;

import android.app.Application;

import com.apkfuns.logutils.LogUtils;
import com.yimeng.yunma.lib.sign.BuildConfig;
import com.squareup.leakcanary.LeakCanary;
import com.yimeng.common.utils.ToastUtils;

/**
 * Desc :
 * Created by WangPeng on 2017/5/10 0010.
 */
public class SignApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.init(this);
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
            LogUtils.configAllowLog = true;
        }
    }
}
