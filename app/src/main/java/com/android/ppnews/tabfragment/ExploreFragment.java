package com.android.ppnews.tabfragment;

import android.os.Parcelable;
import android.view.View;

import com.android.ppnews.R;
import com.android.ppnews.tabfragment.state.ExploreFragmentState;

/**
 * Created by wy on 17-2-23.
 */

public class ExploreFragment extends StatefullFragment<ExploreFragmentState> implements HomeTabFragment {


    public ExploreFragment() {
        super(null, "ExploreFragment_state", R.layout.fragment_explore);
    }

    @Override
    public void onTabReselected() {

    }

    @Override
    protected void onViewCreated(View inflate) {

    }

    @Override
    protected void updateViews(ExploreFragmentState exploreFragmentState, ExploreFragmentState state) {

    }
}
