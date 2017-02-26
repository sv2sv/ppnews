package com.android.ppnews.tabfragment;

import android.os.Bundle;
import android.view.View;

import com.android.ppnews.PPFragment;

/**
 * Created by wy on 17-2-23.
 */

public class ExploreFragment extends StatefullFragment<ExploreFragmentState> implements HomeTabFragment {


    public ExploreFragment() {
        super(null, "ExploreFragment_state", 0);
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
