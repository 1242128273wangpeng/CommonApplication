package com.yimeng.common.base;

import android.app.ActivityManager;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.yimeng.common.broadcast.NetBroadcastReceiver;
import com.yimeng.common.utils.PGActivityUtils;
import com.yimeng.common.widget.CommonTitle;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends BaseCheckPermissionActivity implements NetBroadcastReceiver.onDisconnectNet {
    public PGActivityUtils PGApp;
    private Unbinder mUnbinder;
    private NetBroadcastReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        initNetBroadcast();
        PGApp = PGActivityUtils.getInstance();
        PGApp.addActivity(this);
        initData();
    }

    /**
     * 返回键监听
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            PGApp.finishTop();
            return true;
        }
        return false;
    }

    private void initNetBroadcast() {
        receiver = new NetBroadcastReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(receiver, intentFilter);
        receiver.setOnDisconnectNet(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    protected abstract void initData();

    public abstract int getLayoutId();

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(0x00000000);
            ActivityManager.TaskDescription description = new ActivityManager.TaskDescription(null, null, android.R.attr.colorPrimary);
            setTaskDescription(description);
        }
    }

    // 子类可以重写
    @Override
    protected String[] getNeedPermissions() {
        return new String[0];
    }

    // 子类可以重写
    @Override
    protected void permissionGrantedSuccess() {
        Log.i("授权成功", "true");
    }
    // 子类可以重写
    @Override
    protected void permissionGrantedFail() {
        Log.i("授权失败", "false");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        if (null != receiver) {
            receiver.onDestroy();
            unregisterReceiver(receiver);
            receiver = null;
        }
    }


    @Override
    public void connect(boolean isConn) {
        CommonTitle.getInstance().setNetTextChange(this,isConn);
    }

}
