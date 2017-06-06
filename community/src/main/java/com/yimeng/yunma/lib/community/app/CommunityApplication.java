package com.yimeng.yunma.lib.community.app;

import android.app.Application;

import com.apkfuns.logutils.LogUtils;
import com.yimeng.yunma.lib.community.BuildConfig;
import com.yimeng.yunma.lib.community.CommunityConstants;
import com.squareup.leakcanary.LeakCanary;
import com.yimeng.common.net.AppClient;
import com.yimeng.common.utils.ToastUtils;

/**
 * Desc :
 * Created by WangPeng on 2017/5/23 0023.
 */

public class CommunityApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppClient.init(this, CommunityConstants.BASEURL);
        ToastUtils.init(this);
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
            LogUtils.configAllowLog = true;
        }
    }
}