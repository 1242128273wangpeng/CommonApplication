package com.yimeng.yunma.lib.main.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yimeng.yunma.lib.main.MainConstants;
import com.yimeng.yunma.lib.main.R;
import com.yimeng.yunma.lib.main.R2;
import com.yimeng.yunma.lib.main.model.RootsData;
import com.yimeng.yunma.lib.main.mvp.TestContract;
import com.yimeng.yunma.lib.main.mvp.TestModel;
import com.yimeng.yunma.lib.main.mvp.TestPresenter;
import com.yimeng.common.MVPActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class TestActivity extends MVPActivity<TestPresenter> implements TestContract.View {
    @BindView(R2.id.toTest2Activity)
    Button toTest2Activity;

    @Override
    protected void createPresenter() {
        mvpPresenter = new TestPresenter(this, new TestModel());
        mvpPresenter.setUseEventBus(true);
    }

    @Override
    public void showSuccess(RootsData rootsData) {
        Toast.makeText(this, "Test成功" + rootsData.getResultString(), Toast.LENGTH_SHORT).show();
    }

    @OnClick({R2.id.toTest2Activity})
    public void OnClick(View view) {
        int id = view.getId();
        if(id==R.id.toTest2Activity){
            startActivity(new Intent(this, Test2Activity.class));
        }
    }

    @Override
    protected void initData() {
        mvpPresenter.loadData(MainConstants.APIKEY);
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_activity_test;
    }

}
