package com.yimeng.common.widget;


import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yimeng.common.base.BaseActivity;
import com.yimeng.common.R;
import com.yimeng.common.utils.NetWorkUtils;
import com.yimeng.common.utils.PGActivityUtils;

/**
 * Desc : title的公共类
 * Create by WangPeng on 2017/5/22 0022
 */

public class CommonTitle implements View.OnClickListener {
    public static final int ADD = 0x1;
    public static final int SIGN = 0x2;
    public static final int SAVE = 0x3;
    private static CommonTitle title;
    private LinearLayout back;
    private LinearLayout right;
    private TextView titleName;
    private PGActivityUtils PGApp;
    private TextView netText;
    private Context context;
    private onRightAddClickListener onRightAddClickListener;
    private onRightSignClickListener onRightSignClickListener;
    private onRightSaveClickListener onRightSaveClickListener;

    /**
     * 方法名：CommonTitle
     * 功    能：单利模式获取唯一title
     * 参    数：
     * 返回值：
     */
    public static CommonTitle getInstance() {
        if (null == title) {
            title = new CommonTitle();
        }
        return title;
    }

    public void setOnRightAddClickListener(BaseActivity view,CommonTitle.onRightAddClickListener onRightAddClickListener) {
        right = (LinearLayout) view.findViewById(R.id.title_add);
        right.setVisibility(View.VISIBLE);
        right.setOnClickListener(this);
        this.onRightAddClickListener = onRightAddClickListener;
    }

    public void setOnRightSignClickListener(BaseActivity view,CommonTitle.onRightSignClickListener onRightSignClickListener) {
        right = (LinearLayout) view.findViewById(R.id.title_sign);
        right.setVisibility(View.VISIBLE);
        right.setOnClickListener(this);
        this.onRightSignClickListener = onRightSignClickListener;
    }

    public void setOnRightSaveClickListener(BaseActivity view,CommonTitle.onRightSaveClickListener onRightSaveClickListener) {
        right = (LinearLayout) view.findViewById(R.id.title_save);
        right.setVisibility(View.VISIBLE);
        right.setOnClickListener(this);
        this.onRightSaveClickListener = onRightSaveClickListener;
    }

    /**
     * 方法名：setTitle
     * 功    能：设置标题名称
     * 参    数：BaseActivity view, String info, PGActivityUtils PGApp, boolean flag
     * 返回值：
     */
    public void setTitle(BaseActivity view, String info, PGActivityUtils PGApp, boolean flag) {
        this.PGApp = PGApp;
        this.context = view;
        back = (LinearLayout) view.findViewById(R.id.title_back);
        back.setOnClickListener(this);
        if (flag) {
            back.setVisibility(View.VISIBLE);
        } else {
            back.setVisibility(View.GONE);
        }
        titleName = (TextView) view.findViewById(R.id.title_name);
        titleName.setText(info);
        netText = (TextView) view.findViewById(R.id.title_net);
        netText.setOnClickListener(this);
        boolean netConnect = NetWorkUtils.isNetworkConnected(context);
        if (netConnect) {
            netText.setVisibility(View.GONE);
        } else {
            netText.setVisibility(View.VISIBLE);
        }
    }

    public void setNetTextChange(BaseActivity view, boolean isConn) {
        netText = (TextView) view.findViewById(R.id.title_net);
        if (netText == null) {
            return;
        } else {
            this.context = view;
            netText.setOnClickListener(this);
            if (isConn) {
                if (netText.getVisibility() == View.VISIBLE) {
                    netText.setVisibility(View.GONE);
                }
            } else {
                if (netText.getVisibility() != View.VISIBLE) {
                    netText.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.title_back) {
            PGApp.finishTop();
        } else if (i == R.id.title_net) {
            Intent intent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
            context.startActivity(intent);
        } else if (i == R.id.title_add) {
            onRightAddClickListener.rightAddClick();
        } else if (i == R.id.title_sign) {
            onRightSignClickListener.rightSignClick();
        } else if (i == R.id.title_save) {
            onRightSaveClickListener.rightSaveClick();
        }
    }

    public interface onRightAddClickListener {
        public void rightAddClick();
    }
    public interface onRightSignClickListener {
        public void rightSignClick();
    }
    public interface onRightSaveClickListener {
        public void rightSaveClick();
    }
}
