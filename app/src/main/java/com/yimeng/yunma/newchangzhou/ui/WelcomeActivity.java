package com.yimeng.yunma.newchangzhou.ui;

import android.graphics.Color;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;

import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.yimeng.common.base.BaseFragmentActivity;
import com.yimeng.common.ultraviewpager.UltraViewPager;
import com.yimeng.common.utils.DisplayUtil;
import com.yimeng.common.utils.SystemBarHelper;
import com.yimeng.common.utils.ToastUtils;
import com.yimeng.yunma.newchangzhou.adapter.WelcomePagerAdapter;
import com.yimeng.yunma.newchangzhou.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class WelcomeActivity extends BaseFragmentActivity {
    @BindView(R.id.wel_vp)
    ViewPager welVp;
    @BindView(R.id.ultra_vp)
    UltraViewPager ultraVp;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private static List<Fragment> fragmentList = new ArrayList<Fragment>();
    private long exitTime;//上一次按退出键时间
    private static final long DURTIME = 2000;//双击回退键间隔时间

    @Override
    protected void initData() {
        welVp = (ViewPager) findViewById(R.id.wel_vp);
        fragmentList.add(WelcomeFragment.newInstance(R.drawable.ad01));
        fragmentList.add(WelcomeFragment.newInstance(R.drawable.ad02));
        fragmentList.add(WelcomeFragment.newInstance(R.drawable.ad03));
        fragmentList.add(WelcomeFragment.newInstance(R.drawable.ad04));
        fragmentList.add(WelcomeFragment.newInstance(R.drawable.ad05));
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }
        };
        welVp.setAdapter(fragmentPagerAdapter);
        welVp.setCurrentItem(0);
        welVp.setVisibility(View.GONE);
        ultraVp.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        PagerAdapter adapter = new WelcomePagerAdapter(this, new int[]{R.drawable.ad01, R.drawable.ad02, R.drawable.ad03, R.drawable.ad04, R.drawable.ad05});
        ultraVp.setAdapter(adapter);
        //内置indicator初始化
        ultraVp.initIndicator();
        ultraVp.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.GREEN)
                .setNormalColor(Color.WHITE)
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
        ultraVp.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        ultraVp.getIndicator().setMargin(0, 0, 0, DisplayUtil.dp2px(this, 60));
        ultraVp.getIndicator().build();
        SystemBarHelper.tintStatusBar(this, getResources().getColor(R.color.colorPrimary));
        checkPatch();
    }

    private void checkPatch() {
        File patchFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/libpatch/patch_signed_7zip.apk");
        if (patchFile.exists()) {
            System.out.println("patchFile-->文件存在");
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), patchFile.getAbsolutePath());
        } else {
            System.out.println("patchFile-->文件不存在");
        }
    }

    @Override
    protected void initScreen() {
        SystemBarHelper.setNotTitleScreen(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.app_activity_welcome;
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

    @Override
    public void connect(boolean isConn) {
        super.connect(isConn);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - exitTime > DURTIME) {
                ToastUtils.showToast("再按一次返回键退出");
                exitTime = System.currentTimeMillis();
                return true;
            } else {
                PGApp.exit();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);

    }

}
