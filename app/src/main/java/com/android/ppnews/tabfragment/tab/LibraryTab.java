package com.android.ppnews.tabfragment.tab;

import android.content.Context;

import com.android.ppnews.R;
import com.android.ppnews.tabfragment.HomeTabFragment;
import com.android.ppnews.tabfragment.LibraryFragment;
import com.android.ppnews.tabfragment.tab.HomeTab;

/**
 * Created by wy on 17-2-23.
 */
public class LibraryTab extends HomeTab {
    protected LibraryTab() {
        super(1);
    }

    @Override
    public HomeTabFragment getFragment(Context context) {
        return new LibraryFragment();
    }

    @Override
    public int getTabLayoutId() {
        return R.id.library;
    }
}
