package com.yimeng.yunma.newchangzhou.utils;

import com.yimeng.common.base.BaseFragment;
import com.yimeng.yunma.lib.community.ui.CommunityFragment;
import com.yimeng.yunma.lib.knowledge.ui.KnowledgeFragment;
import com.yimeng.yunma.lib.main.ui.MainFragment;
import com.yimeng.yunma.lib.mine.ui.MineFragment;
import com.yimeng.yunma.lib.shop.ui.ShopFragment;

import java.util.HashMap;

/**
 * Desc :
 * Created by WangPeng on 2017/6/6 0006.
 */

public class FragmentFactory {
    private static HashMap<Integer, BaseFragment> fragmentCache = new HashMap<>();

    public static BaseFragment createFragment(int index) {
        BaseFragment fragment = null;
        fragment = fragmentCache.get(index);
        if (fragment == null) {
            switch (index) {
                case 0:
                    fragment = new MainFragment();
                    break;
                case 1:
                    fragment = new KnowledgeFragment();
                    break;
                case 2:
                    fragment = new CommunityFragment();
                    break;
                case 3:
                    fragment = new ShopFragment();
                    break;
                case 4:
                    fragment = new MineFragment();
                    break;
            }
            if (fragment != null) {
                fragmentCache.put(index, fragment);
            }
        }
        return fragment;
    }

    public static int getSize() {
        return 5;
    }

}
