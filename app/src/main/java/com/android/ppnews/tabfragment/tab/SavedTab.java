package com.android.ppnews.tabfragment.tab;

import android.content.Context;

import com.android.ppnews.R;
import com.android.ppnews.tabfragment.HomeTabFragment;
import com.android.ppnews.tabfragment.SavedFragment;
import com.android.ppnews.tabfragment.tab.HomeTab;

/**
 * Created by wy on 17-2-23.
 */
public class SavedTab extends HomeTab {
    protected SavedTab() {
        super(3);
    }

    @Override
    public HomeTabFragment getFragment(Context context) {
        return new SavedFragment();
    }

    @Override
    public int getTabLayoutId() {
        return R.id.laterread;
    }
}
