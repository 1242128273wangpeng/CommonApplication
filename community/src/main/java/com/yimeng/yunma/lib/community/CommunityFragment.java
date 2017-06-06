package com.yimeng.yunma.lib.community;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yimeng.common.base.BaseFragment;


/**
 * Desc :
 * Create by WangPeng on 2017/6/6 0006
 */
public class CommunityFragment extends BaseFragment {


    public CommunityFragment() {
    }


    @Override
    protected View getFragmentView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.community_fragment_community, container, false);
        return view;
    }

    @Override
    protected void initData() {

    }

}
