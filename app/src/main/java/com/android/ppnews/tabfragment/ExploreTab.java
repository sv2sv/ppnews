package com.android.ppnews.tabfragment;

import android.content.Context;

import com.android.ppnews.R;

/**
 * Created by wy on 17-2-23.
 */
public class ExploreTab extends HomeTab {
    protected ExploreTab() {
        super(2);
    }

    @Override
    public HomeTabFragment getFragment(Context context) {
        return null;
    }

    @Override
    public int getTabLayoutId() {
        return R.id.findmore;
    }
}
