package com.yimeng.common.application;

import android.app.Application;
import android.content.Context;

import com.github.mzule.activityrouter.annotation.Modules;
import com.yimeng.common.net.AppClient;

/**
 * Desc :
 * Created by WangPeng on 2017/5/23 0023.
 */
public class CommonApplication extends Application {
    private static final String TAG = "Init";
    private static Context context;

    public static Context getContext() {
        return context;
    }

}
