package com.yimeng.yunma.newchangzhou.ui;

import android.support.v4.view.ViewPager;

import com.yimeng.common.base.BaseFragmentActivity;
import com.yimeng.common.utils.SystemBarHelper;
import com.yimeng.common.utils.ToastUtils;
import com.yimeng.yunma.newchangzhou.adapter.MainPagerAdapter;
import com.yimeng.yunma.newchangzhou.R;

import butterknife.BindView;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends BaseFragmentActivity {

    @BindView(R.id.main_vp)
    ViewPager mainVp;

    @Override
    protected void initScreen() {
        SystemBarHelper.setStatusBarDarkMode(this);
    }

    @Override
    protected void initData() {
        mainVp.setAdapter(new MainPagerAdapter(this.getSupportFragmentManager()));
    }

    @Override
    public int getLayoutId() {
        return R.layout.app_activity_main;
    }


    @Override
    protected String[] getNeedPermissions() {
        return new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE};
    }

    @Override
    protected void permissionGrantedSuccess() {
        ToastUtils.showToast("权限申请成功");
    }

    @Override
    protected void permissionGrantedFail() {
        ToastUtils.showToast("权限申请失败");
    }

}
