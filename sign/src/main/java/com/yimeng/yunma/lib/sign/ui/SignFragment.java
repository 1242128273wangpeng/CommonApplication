package com.yimeng.yunma.lib.sign.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yimeng.common.base.BaseFragment;
import com.yimeng.yunma.lib.sign.R;


/**
 * Desc :
 * Create by WangPeng on 2017/6/6 0006
 */
public class SignFragment extends BaseFragment {


    public SignFragment() {
    }


    @Override
    protected View getFragmentView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.sign_fragment_sign, container, false);
        return view;
    }

    @Override
    protected void initData() {

    }

}
