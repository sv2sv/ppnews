package com.android.ppnews.tabfragment;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.ppnews.MyNIRecyclerViewAdapter;
import com.android.ppnews.PPApplication;
import com.android.ppnews.PPDepend;
import com.android.ppnews.R;
import com.android.ppnews.net.JHCall;
import com.android.ppnews.net.JHNewsType;
import com.android.ppnews.net.JHService;
import com.android.ppnews.pojo.JHNew;
import com.android.ppnews.tabfragment.helper.RefreshHelper;
import com.android.ppnews.tabfragment.state.ForYouFragmentState;

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
    private List<JHNew.ResultBean.DataBean> datas;



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
        setupPullToRefresh();
        getDataFromNetAndFail();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataFromNetAndFail();
            }
        });
    }

    private void getDataFromNetAndFail() {
        JHCall.getService().getData(JHNewsType.TOP.str, JHNewsType.TOP.KEY).enqueue(new Callback<JHNew>() {
            @Override
            public void onResponse(Call<JHNew> call, Response<JHNew> response) {
                mAdapter = new MyNIRecyclerViewAdapter(response.body().getResult().getData(), new MyNIRecyclerViewAdapter.onItemClickListener() {
                    @Override
                    public void onClick(JHNew.ResultBean.DataBean item) {
                        FragmentTransaction ft = getParentFragment().getFragmentManager().beginTransaction();
                        NewDetailFragment newDetailFragment = new NewDetailFragment();
                        Bundle args = new Bundle();
                        args.putString("url",item.getUrl());
                        newDetailFragment.setArguments(args);
                        ft.replace(R.id.home_fragment_content, newDetailFragment , "NewDetailFragment");
                        ft.commit();
                    }
                });
                mRecyclerView.setAdapter(mAdapter);
                if(mSwipeRefreshLayout.isRefreshing()){
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<JHNew> call, Throwable t) {
                mAdapter = new MyNIRecyclerViewAdapter(null, null);
                mRecyclerView.setAdapter(mAdapter);
                PPDepend.showToast("net fail",1);
                if(mSwipeRefreshLayout.isRefreshing()){
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }


    private void setupPullToRefresh() {
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary), Color.DKGRAY);
        mSwipeRefreshLayout.setDistanceToTriggerSync(120);
        mSwipeRefreshLayout.setProgressViewOffset(false, 200, 450);
    }

    @Override
    protected void updateViews(ForYouFragmentState forYouFragmentState, ForYouFragmentState state) {
        this.mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        this.mRecyclerView.setTag(forYouFragmentState);
        if(forYouFragmentState != state){
            PPDepend.showToast("false",1);
            mSwipeRefreshLayout.setRefreshing(false);
            mSwipeRefreshLayout.setEnabled(false);
        }

    }


    @Override
    public void onDestroyView() {
        PPDepend.showToast("onDestroyView",1);
        mSwipeRefreshLayout.setRefreshing(false);
        mRecyclerView.setAdapter(null);
        super.onDestroyView();
    }

    public List<JHNew.ResultBean.DataBean> getDa(JHNewsType type) {
        JHCall.getService().getData(type.str, type.KEY).enqueue(new Callback<JHNew>() {
            @Override
            public void onResponse(Call<JHNew> call, Response<JHNew> response) {
                datas = response.body().getResult().getData();
            }

            @Override
            public void onFailure(Call<JHNew> call, Throwable t) {

            }
        });
        for (JHNew.ResultBean.DataBean d : datas) {

        }

        return datas;
    }
}
