package com.yimeng.yunma.lib.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yimeng.common.base.BaseFragment;


/**
*  Desc :
*  Create by WangPeng on 2017/6/6 0006
*/
public class MainFragment extends BaseFragment {


    public MainFragment() {
    }

    @Override
    protected View getFragmentView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.main_fragment_main, container, false);
        return view;
    }

    @Override
    protected void initData() {

    }

}
