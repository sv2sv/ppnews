package com.android.ppnews.tabfragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.ppnews.MyNIRecyclerViewAdapter;
import com.android.ppnews.PPApplication;
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
        JHCall.getService().getData(JHNewsType.TOP, JHService.KEY).enqueue(new Callback<JHNew>() {
            @Override
            public void onResponse(Call<JHNew> call, Response<JHNew> response) {
                mAdapter = new MyNIRecyclerViewAdapter(response.body().getResult().getData(), null);
                mRecyclerView.setAdapter(mAdapter);
                if(mSwipeRefreshLayout.isRefreshing()){
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<JHNew> call, Throwable t) {
                mAdapter = new MyNIRecyclerViewAdapter(null, null);
                mRecyclerView.setAdapter(mAdapter);
                Toast.makeText(getContext(),"net fail",Toast.LENGTH_LONG).show();
                if(mSwipeRefreshLayout.isRefreshing()){
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    public enum A {
        ad("a", 1), fd("f", 2);
        private String a;
        private int i;

        A(String str, int i) {
            this.a = str;
        }
    }

    private void setupPullToRefresh() {
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary), Color.DKGRAY);
        mSwipeRefreshLayout.setDistanceToTriggerSync(120);
        mSwipeRefreshLayout.setProgressViewOffset(false, 200, 450);
    }

    @Override
    protected void updateViews(ForYouFragmentState forYouFragmentState, ForYouFragmentState state) {
        this.mRecyclerView.setTag(forYouFragmentState);
        if(forYouFragmentState != state){
            ((PPApplication)getActivity().getApplication()).showToast("false",1);
            mSwipeRefreshLayout.setRefreshing(false);
            mSwipeRefreshLayout.setEnabled(false);
        }

    }



    @Override
    public void onDestroyView() {
        ((PPApplication)getActivity().getApplication()).showToast("onDestroyView",1);
        mSwipeRefreshLayout.setRefreshing(false);
        mRecyclerView.setAdapter(null);
        super.onDestroyView();
    }

    public List<JHNew.ResultBean.DataBean> getDa(String type) {
        JHCall.getService().getData(type, JHService.KEY).enqueue(new Callback<JHNew>() {
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
