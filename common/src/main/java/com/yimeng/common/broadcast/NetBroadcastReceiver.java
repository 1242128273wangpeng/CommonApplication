package com.yimeng.common.broadcast;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class NetBroadcastReceiver extends BroadcastReceiver {
    private onDisconnectNet onDisconnectNet;
    private Activity activity;

    public NetBroadcastReceiver(Activity activity) {
        this.activity = activity;
    }

    public void setOnDisconnectNet(NetBroadcastReceiver.onDisconnectNet onDisconnectNet) {
        this.onDisconnectNet = onDisconnectNet;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            for (NetworkInfo mNetworkInfo : networkInfo) {
                NetworkInfo.State state = mNetworkInfo.getState();
                if (NetworkInfo.State.CONNECTED == state) {
                    onDisconnectNet.connect(true);
                    return;
                }
            }
            onDisconnectNet.connect(false);
        }
    }


    public void onDestroy() {
        activity = null;
        onDisconnectNet = null;
    }

    public interface onDisconnectNet {
        void connect(boolean isConn);
    }
}
