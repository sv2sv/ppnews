package com.android.ppnews.tabfragment;

import com.android.ppnews.R;
import com.android.ppnews.tabfragment.state.LibraryFragmentState;

/**
 * Created by wangyao on 27/2/17.
 */

public class LibraryFragment extends StatefullFragment<LibraryFragmentState> implements HomeTabFragment {
    public LibraryFragment() {
        super(null, "LibraryFragment_state", R.layout.fragment_library);
    }

    @Override
    public void onTabReselected() {

    }

    @Override
    protected void updateViews(LibraryFragmentState libraryFragmentState, LibraryFragmentState state) {

    }
}
