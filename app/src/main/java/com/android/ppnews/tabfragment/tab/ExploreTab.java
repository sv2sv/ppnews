package com.android.ppnews.tabfragment.tab;

import android.content.Context;

import com.android.ppnews.R;
import com.android.ppnews.tabfragment.ExploreFragment;
import com.android.ppnews.tabfragment.HomeTabFragment;

/**
 * Created by wy on 17-2-23.
 */
public class ExploreTab extends HomeTab {
    protected ExploreTab() {
        super(2);
    }

    @Override
    public HomeTabFragment getFragment(Context context) {
        return new ExploreFragment();
    }

    @Override
    public int getTabLayoutId() {
        return R.id.findmore;
    }
}
