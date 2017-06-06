package com.yimeng.yunma.lib.main.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yimeng.yunma.lib.main.MainConstants;
import com.yimeng.yunma.lib.main.R;
import com.yimeng.yunma.lib.main.R2;
import com.yimeng.yunma.lib.main.model.NewsData;
import com.yimeng.yunma.lib.main.mvp.Test2Contract;
import com.yimeng.yunma.lib.main.mvp.Test2Model;
import com.yimeng.yunma.lib.main.mvp.Test2Presenter;
import com.yimeng.common.MVPActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class Test2Activity extends MVPActivity<Test2Presenter> implements Test2Contract.View {

    @BindView(R2.id.goTest3Activity)
    Button goTest3Activity;

    @Override
    protected void createPresenter() {
        mvpPresenter = new Test2Presenter(this, new Test2Model());
    }

    @Override
    protected void initData() {
        mvpPresenter.load2Data(MainConstants.APIKEY, "苹果股价再创新高");
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_activity_test2;
    }

    @OnClick({R2.id.goTest3Activity})
    public void Onclick(View view) {
        int id = view.getId();
        if(id==R.id.goTest3Activity){
            startActivity(new Intent(Test2Activity.this, Test3Activity.class));
        }
    }

    @Override
    public void showTest2Success(NewsData rootsData) {
        Toast.makeText(this, "Test2成功" + rootsData.getListbeans(), Toast.LENGTH_SHORT).show();
    }

}
