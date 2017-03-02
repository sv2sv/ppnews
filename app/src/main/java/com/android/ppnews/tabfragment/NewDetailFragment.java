package com.android.ppnews.tabfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.android.ppnews.R;
import com.android.ppnews.tabfragment.state.NewDetailFragmentState;

/**
 * Created by wangyao on 1/3/17.
 */

public class NewDetailFragment extends StatefullFragment<NewDetailFragmentState>{
    private WebView mWebView;


    public NewDetailFragment(){
        super(null, "NewDetailFragment_state", R.layout.fragment_news_details);
    }

    @Override
    protected View doOnCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle args = getArguments();
        String url = args.getString("url");
        View view = layoutInflater.inflate(R.layout.fragment_news_details,viewGroup,false);
        mWebView = (WebView) view.findViewById(R.id.webView);
        mWebView.loadUrl(url);
        return view;
    }

    @Override
    protected void updateViews(NewDetailFragmentState newDetailFragmentState, NewDetailFragmentState state) {

    }

    @Override
    public void onDestroyView() {
        mWebView.destroy();
        mWebView = null;
        super.onDestroyView();
    }
}
