package com.android.ppnews;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.FrameLayout;

public class MainActivity extends BaseActivity {


    private FrameLayout mContent;
    private SwipeRefreshLayout mSwipeRefreshLayout;


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
        addFragment(NewsFragment.getInstance(""));
        mSwipeRefreshLayout = (SwipeRefreshLayout) this.findViewById(R.id.srl);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary), Color.DKGRAY);
        mSwipeRefreshLayout.setDistanceToTriggerSync(120);
        mSwipeRefreshLayout.setProgressViewOffset(false,200,450);

        getProgressBar(R.id.progress);

    }

    public void setRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        mSwipeRefreshLayout.setOnRefreshListener(listener);
    }

    public void setRefreshing(boolean enable) {
        mSwipeRefreshLayout.setRefreshing(enable);
    }



}
