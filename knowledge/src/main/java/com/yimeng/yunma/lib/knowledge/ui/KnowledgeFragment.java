package com.yimeng.yunma.lib.knowledge.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yimeng.common.base.BaseFragment;
import com.yimeng.yunma.lib.knowledge.R;

/**
 * Desc :
 * Created by WangPeng on 2017/6/6 0006.
 */

public class KnowledgeFragment extends BaseFragment {

    public KnowledgeFragment() {

    }

    @Override
    protected View getFragmentView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.know_fragment_mine, container, false);
        return view;
    }

    @Override
    protected void initData() {

    }
}
