package com.yimeng.yunma.newchangzhou.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yimeng.common.base.BaseFragment;
import com.yimeng.yunma.newchangzhou.R;

import butterknife.BindView;


/**
 * Desc :
 * Create by WangPeng on 2017/5/23 0023
 */
public class WelcomeFragment extends BaseFragment {
    private static final String IMG_PARAM = "param";
    @BindView(R.id.wel_iv)
    ImageView welIv;
    private int mParam;

    public WelcomeFragment() {

    }

    public static WelcomeFragment newInstance(int param) {
        WelcomeFragment fragment = new WelcomeFragment();
        Bundle args = new Bundle();
        args.putInt(IMG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getInt(IMG_PARAM);
        }
    }

    @Override
    protected View getFragmentView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        return view;
    }

    @Override
    protected void initData() {
        welIv.setImageResource(mParam);
    }
}
