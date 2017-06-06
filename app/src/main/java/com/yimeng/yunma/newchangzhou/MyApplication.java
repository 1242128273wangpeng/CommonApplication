package com.yimeng.yunma.newchangzhou;

import com.apkfuns.logutils.LogUtils;
import com.github.mzule.activityrouter.annotation.Modules;
import com.squareup.leakcanary.LeakCanary;
import com.yimeng.common.CommonConstants;
import com.yimeng.common.downloader.DownloadConfig;
import com.yimeng.common.downloader.FileDownloader;
import com.yimeng.common.downloader.model.PreferencesUtils;
import com.yimeng.common.net.AppClient;
import com.yimeng.common.utils.ToastUtils;

import java.io.File;

/**
 * Created by wang2014 on 2017/5/2 0002.
 */
@Modules({"app", "main", "common", "shop", "sign", "mine", "knowledge", "community"})
public class MyApplication extends SimpleTinkerApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        AppClient.init(this, AppConstants.DOWNLOAD_BASEURL);
        ToastUtils.init(getApplicationContext());
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
            LogUtils.configAllowLog = true;
        }
        FileDownloader.init(getApplicationContext());
        String DOWNLOAD_SAVE_PATH = PreferencesUtils.getString(getApplicationContext(), CommonConstants.SAVE_PATH_KEY, CommonConstants.DEFAULT_SAVE_PATH);
        DownloadConfig config = new DownloadConfig.Builder().setDownloadDir(DOWNLOAD_SAVE_PATH + File.separator).build();
        FileDownloader.setConfig(config);
    }
}
