package com.android.ppnews.tabfragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.android.ppnews.BaseFragment;
import com.android.ppnews.R;

/**
 * Created by wy on 17-2-24.
 */

public class HomeFragment extends BaseFragment implements FootBarHelper.OnTabSelectedListener{
    private FootBarHelper mFootBarHelper;
    private ViewGroup mFootBar;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mFootBar = (ViewGroup) view.findViewById(R.id.foot_bar_layout);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFootBarHelper = new FootBarHelper(mFootBar,this);
    }

    @Override
    public void onTabSelected(HomeTab homeTab) {

    }
}
