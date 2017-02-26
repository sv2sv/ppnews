package com.android.ppnews.tabfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.ppnews.MyNIRecyclerViewAdapter;
import com.android.ppnews.PPFragment;
import com.android.ppnews.R;
import com.android.ppnews.net.JHCall;
import com.android.ppnews.net.JHNewsType;
import com.android.ppnews.net.JHService;
import com.android.ppnews.pojo.JHNew;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wangyao on 24/2/17.
 */
public class ForYouFragment extends StatefullFragment<ForYouFragmentState> implements HomeTabFragment {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private MyNIRecyclerViewAdapter mAdapter;

    public ForYouFragment() {
        super(null, "ForYouFragment_state", R.layout.fragment_foryou);
    }


    @Override
    public void onTabReselected() {
        RefreshHelper.jumpToTop(mRecyclerView, true);
    }



    @Override
    protected void onViewCreated(View inflate) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.srl);
        mRecyclerView = (RecyclerView) inflate.findViewById(R.id.list_foryou);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary), Color.DKGRAY);
        mSwipeRefreshLayout.setDistanceToTriggerSync(120);
        mSwipeRefreshLayout.setProgressViewOffset(false, 200, 450);
        JHCall.getService().getData(JHNewsType.TOP, JHService.KEY).enqueue(new Callback<JHNew>() {
            @Override
            public void onResponse(Call<JHNew> call, Response<JHNew> response) {
                mAdapter = new MyNIRecyclerViewAdapter(response.body().getResult().getData(), null);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<JHNew> call, Throwable t) {
                Toast.makeText(getContext(),"net fail",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void updateViews(ForYouFragmentState forYouFragmentState, ForYouFragmentState state) {

    }
}
