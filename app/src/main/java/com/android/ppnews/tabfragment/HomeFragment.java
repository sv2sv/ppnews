package com.android.ppnews.tabfragment;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;

import android.os.Parcelable;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.ppnews.PPActivity;
import com.android.ppnews.R;
import com.android.ppnews.tabfragment.helper.FootBarHelper;
import com.android.ppnews.tabfragment.state.HomeFragmentState;
import com.android.ppnews.tabfragment.tab.HomeTab;

/**
 * Created by wy on 17-2-24.
 */

public class HomeFragment extends StatefullFragment<HomeFragmentState> implements FootBarHelper.OnTabSelectedListener {
    private FootBarHelper mFootBarHelper;
    private ViewGroup mFootBar;
    private Toolbar mToolbar;
    private AppBarLayout mAppbarLayout;
    private ViewGroup mContentContainer;
    private CoordinatorLayout mCoordinatorLayout;


    public HomeFragment() {
        super(new HomeFragmentState(), "HomeFragment_state", R.layout.fragment_home);
    }


    @Override
    protected void onViewCreated(View inflate) {
        this.mFootBar = (ViewGroup) inflate.findViewById(R.id.foot_bar_layout);
        this.mFootBarHelper = new FootBarHelper(mFootBar,this);
        this.mToolbar = (Toolbar) inflate.findViewById(R.id.toolbar);
        this.mAppbarLayout = (AppBarLayout) inflate.findViewById(R.id.appbar);
        this.mContentContainer = (ViewGroup) inflate.findViewById(R.id.home_fragment_content);
        this.mCoordinatorLayout = (CoordinatorLayout) inflate.findViewById(R.id.main_content);
        ((PPActivity)getActivity()).setSupportActionBar(mToolbar);

        mRootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getContext(),"this is fragment",Toast.LENGTH_LONG).show();
                return false;
            }
        });
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
