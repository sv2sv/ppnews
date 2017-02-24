package com.android.ppnews.tabfragment;

import android.content.Context;

import com.android.ppnews.R;

/**
 * Created by wy on 17-2-23.
 */
public class ForYouTab extends HomeTab {
    protected ForYouTab() {
        super(0);
    }

    @Override
    public HomeTabFragment getFragment(Context context) {
        return new ForYouFragment();
    }

    @Override
    public int getTabLayoutId() {
        return R.id.foryou;
    }
}
