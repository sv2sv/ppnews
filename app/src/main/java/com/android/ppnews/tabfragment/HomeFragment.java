package com.android.ppnews.tabfragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

import com.android.ppnews.R;

/**
 * Created by wy on 17-2-24.
 */

public class HomeFragment extends StatefullFragment<HomeFragmentState> implements FootBarHelper.OnTabSelectedListener {
    private FootBarHelper mFootBarHelper;
    private ViewGroup mFootBar;

    public HomeFragment() {
        super(new HomeFragmentState(), "HomeFragment_state", R.id.home_fragment);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mFootBar = (ViewGroup) view.findViewById(R.id.foot_bar_layout);
    }

    @Override
    protected void updateViews(HomeFragmentState homeFragmentState, HomeFragmentState homeFragmentState2) {
        if (homeFragmentState2 == null || !homeFragmentState2.homeTab.equals(homeFragmentState.homeTab)) {
            FragmentManager childFragmentManager = getChildFragmentManager();
            Fragment findFragmentByTag = childFragmentManager.findFragmentByTag(homeFragmentState.homeTab.toString());
            if (findFragmentByTag == null) {
                FragmentTransaction beginTransaction = childFragmentManager.beginTransaction();
                findFragmentByTag = (Fragment) homeFragmentState.homeTab.getFragment(getActivity());
                beginTransaction.replace(R.id.home_fragment_content, findFragmentByTag, homeFragmentState.homeTab.toString());
                beginTransaction.commit();
            }
            childFragmentManager.executePendingTransactions();
           /* if (homeFragmentState.initialCardId != null && (findFragmentByTag instanceof CardListFragment)) {
                ((CardListFragment) findFragmentByTag).setInitialCardId(((HomeFragmentState) state()).initialCardId);
            }*/
           /* updateCoordinatorLayout();
            updateAppBarLayout();
            updateToolbar();*/
            updateTabBar();
          /*  updateImmersiveMode();
            getNavigationDrawerActivity().updateDrawerEntries();*/
        }
    }

    private void updateTabBar() {
        this.mFootBarHelper.setSelectedTab(((HomeFragmentState) state()).homeTab);
    }

    @Override
    protected int getLayoutId() {
        return R.id.;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFootBarHelper = new FootBarHelper(mFootBar,this);
    }

    @Override
    public void onTabSelected(HomeTab homeTab) {
        Parcelable homeFragmentState = new HomeFragmentState(homeTab);
        if (homeFragmentState.equals(state())) {
            getHomeTabFragment().onTabReselected();
        } else if (homeTab.equals(HomeTab.FOR_YOU_TAB)) {
            changeState((HomeFragmentState) homeFragmentState, true);
        } else {
            pushState((HomeFragmentState) homeFragmentState, true);
        }
    }

    public HomeTabFragment getHomeTabFragment() {
        return (HomeTabFragment) getChildFragmentManager().findFragmentByTag(((HomeFragmentState) state()).homeTab.toString());
    }
}
