package com.yimeng.yunma.lib.sign.ui;

import com.yimeng.yunma.lib.sign.R;
import com.yimeng.common.base.BaseActivity;
import com.yimeng.common.widget.CommonTitle;

/**
 * Desc :
 * Created by WangPeng on 2017/5/22 0022.
 */

public class Sign_SecondActivity extends BaseActivity {
    @Override
    protected void initData() {
        CommonTitle.getInstance().setTitle(this, "资质", PGApp, true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.sign_activity_second;
    }
}
