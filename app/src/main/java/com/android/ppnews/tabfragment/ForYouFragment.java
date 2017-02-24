package com.android.ppnews.tabfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.ppnews.MyNIRecyclerViewAdapter;
import com.android.ppnews.PPFragment;
import com.android.ppnews.R;

/**
 * Created by wangyao on 24/2/17.
 */
public class ForYouFragment extends PPFragment implements HomeTabFragment {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private MyNIRecyclerViewAdapter mAdapter;


    @Override
    public void onTabReselected() {
        RefreshHelper.jumpToTop(mRecyclerView, true);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_foryou);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary), Color.DKGRAY);
        mSwipeRefreshLayout.setDistanceToTriggerSync(120);
        mSwipeRefreshLayout.setProgressViewOffset(false, 200, 450);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_foryou;
    }
}
