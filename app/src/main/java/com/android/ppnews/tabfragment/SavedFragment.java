package com.android.ppnews.tabfragment;

import android.os.Parcelable;

import com.android.ppnews.R;
import com.android.ppnews.tabfragment.state.SavedFragmentState;

/**
 * Created by wangyao on 27/2/17.
 */

public class SavedFragment extends StatefullFragment<SavedFragmentState> implements HomeTabFragment {
    public SavedFragment() {
        super(null, "SavedFragment_state", R.layout.fragment_saved);
    }

    @Override
    public void onTabReselected() {

    }

    @Override
    protected void updateViews(SavedFragmentState savedFragmentState, SavedFragmentState state) {

    }
}
