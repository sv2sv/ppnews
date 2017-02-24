package com.android.ppnews;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wy on 17-2-21.
 */

public abstract class PPFragment extends Fragment {

    protected String TAG = this.getClass().getSimpleName();
    protected PPActivity mActivity;

    protected PPActivity getHoldingActivity() {
        return mActivity;
    }



    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        this.mActivity = (PPActivity) context;
    }

    protected void addFragment(PPFragment fragment) {
        if(null!=fragment){
            getHoldingActivity().addFragment(fragment);
        }
    }

    protected void removeFragment(PPFragment fragment) {
        if(null!=fragment){
            getHoldingActivity().removeFragment(fragment);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);

        initView(view, savedInstanceState);
        return view;
    }

    protected abstract void initView(View view, Bundle savedInstanceState);

    protected abstract int getLayoutId();


}
