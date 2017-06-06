package com.yimeng.yunma.lib.main.ui;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.apkfuns.logutils.LogUtils;
import com.github.mzule.activityrouter.annotation.Router;
import com.yimeng.yunma.lib.main.R;
import com.yimeng.yunma.lib.main.model.DownloadBean;
import com.yimeng.yunma.lib.main.mvp.DownloadContract;
import com.yimeng.yunma.lib.main.mvp.DownloadModel;
import com.yimeng.yunma.lib.main.mvp.DownloadPresenter;
import com.yimeng.common.MVPActivity;
import com.yimeng.common.downloader.DownloadListener;
import com.yimeng.common.downloader.FileDownloader;
import com.yimeng.common.downloader.entity.FileInfo;
import com.yimeng.common.utils.ToastUtils;

@Router("download")
public class Main_DownloadActivity extends MVPActivity<DownloadPresenter> implements DownloadContract.DownloadView {
    private MyDownloaderListener myDownloaderListener;
    private AlertDialog alertDialog;
    private NotificationCompat.Builder builder;
    private NotificationManager notifyManager;

    @Override
    protected void initData() {
        mvpPresenter.getDownloadURL();
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_activity_download;
    }

    @Override
    protected void createPresenter() {
        mvpPresenter = new DownloadPresenter(this, new DownloadModel());
    }

    @Override
    public void showSuccess(final DownloadBean downloadBean) {
        ToastUtils.showToast(downloadBean.toString());
        if (downloadBean != null && !downloadBean.getResultMap().getUrl().equals("")) {
            alertDialog = new AlertDialog.Builder(this).setMessage("有新的版本,立即更新?").setNegativeButton("暂不", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (alertDialog.isShowing()) {
                        alertDialog.dismiss();
                    }
                }
            }).setPositiveButton("更新", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (alertDialog.isShowing()) {
                        alertDialog.dismiss();
                    }
                    myDownloaderListener = new MyDownloaderListener();
                    FileDownloader.start(downloadBean.getResultMap().getUrl(), "patch_signed_7zip.apk", myDownloaderListener, true);
                }
            }).create();
            alertDialog.show();
        }
    }

    private void sendNotification() {
        //获取PendingIntent
        Intent mainIntent = new Intent(this, Main_MainActivity2.class);
        PendingIntent mainPendingIntent = PendingIntent.getActivity(this, 0, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //获取NotificationManager实例
        notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //实例化NotificationCompat.Builde并设置相关属性
        //设置小图标
        builder = new NotificationCompat.Builder(this)
                //设置小图标
                .setSmallIcon(R.mipmap.ic_launcher)
                //设置通知标题
                .setContentTitle("最简单的Notification")
                //设置通知内容
                .setContentText("只有小图标、标题、内容")
                .setContentIntent(mainPendingIntent);
        //设置通知时间，默认为系统发出通知的时间，通常不用设置
        //.setWhen(System.currentTimeMillis());
        //通过builder.build()方法生成Notification对象,并发送通知,id=1
        notifyManager.notify(1, builder.build());
    }

    public class MyDownloaderListener implements DownloadListener {

        @Override
        public void onStart(FileInfo fileInfo) {
            LogUtils.i("start %s", fileInfo.getSpeed());
            sendNotification();
        }

        @Override
        public void onUpdate(FileInfo fileInfo) {
            builder.setProgress(100, (int) (fileInfo.getLoadBytes() * 100f / fileInfo.getTotalBytes()), false);
            notifyManager.notify(1, builder.build());
            //下载进度提示
            builder.setContentText("下载" + (int) (fileInfo.getLoadBytes() * 100f / fileInfo.getTotalBytes()) + "%");
            LogUtils.i("update %s", fileInfo.getLoadBytes());
        }

        @Override
        public void onStop(FileInfo fileInfo) {
            LogUtils.i("stop %s", fileInfo.getLoadBytes());
        }

        @Override
        public void onComplete(FileInfo fileInfo) {
            builder.setProgress(100, 100, false);
            notifyManager.notify(1, builder.build());
            //下载进度提示
            builder.setContentText("下载完成");
            LogUtils.i("complete %s", "total:" + fileInfo.getTotalBytes() + "  " + "load:" + fileInfo.getLoadBytes());
        }

        @Override
        public void onCancel(FileInfo fileInfo) {
            LogUtils.i("cancel");
        }

        @Override
        public void onError(FileInfo fileInfo, String errorMsg) {
            LogUtils.i("error");
        }
    }
}
