package com.android.ppnews;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wy on 17-2-21.
 */

public abstract class PPFragment extends Fragment {

    protected String TAG = this.getClass().getSimpleName();
    protected PPActivity mActivity;
    protected View mRootView;

    protected PPActivity getHoldingActivity() {
        return mActivity;
    }



    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        this.mActivity = (PPActivity) context;
    }

    /*protected void addFragment(PPFragment fragment) {
        if(null!=fragment){
            getHoldingActivity().addFragment(fragment);
        }
    }

    protected void removeFragment(PPFragment fragment) {
        if(null!=fragment){
            getHoldingActivity().removeFragment(fragment);
        }
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view =  doOnCreateView(inflater,container,savedInstanceState);
        setRootView(view);
        onViewCreated(view);
        return view;
    }

    protected  void onViewCreated(View view){

    }

    protected View doOnCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }


    protected void setRootView(View rootView) {
        this.mRootView = rootView;
    }
}
