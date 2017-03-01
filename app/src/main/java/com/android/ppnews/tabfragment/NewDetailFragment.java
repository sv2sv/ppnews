package com.android.ppnews.tabfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.android.ppnews.PPFragment;
import com.android.ppnews.R;

/**
 * Created by wangyao on 1/3/17.
 */

public class NewDetailFragment extends PPFragment{
    private WebView mWebView;


    public NewDetailFragment(){

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

}
