package com.android.ppnews;

import com.android.ppnews.tabfragment.HomeFragment;

public class HomeActivity extends PPActivity {


    private HomeFragment mHomeFragment;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.content;
    }

    @Override
    protected void init() {
        super.init();
        this.mHomeFragment = PPDepend.getFragment(this, R.id.home_fragment);
        /*addFragment(NewsFragment.getInstance(""));
        mSwipeRefreshLayout = (SwipeRefreshLayout) this.findViewById(R.id.srl);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary), Color.DKGRAY);
        mSwipeRefreshLayout.setDistanceToTriggerSync(120);
        mSwipeRefreshLayout.setProgressViewOffset(false,200,450);

        getProgressBar(R.id.progress);*/

    }

    @Override
    public void onBackPressed() {
        if (!this.mHomeFragment.handleOnBackPressed()) {
            super.onBackPressed();
        }
    }
}
