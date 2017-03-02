package com.android.ppnews.tabfragment.detail;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;

import com.android.ppnews.tabfragment.NewDetailFragment;

/**
 * Created by wangyao on 2/3/17.
 */

public class NewsDetail extends Detail {
    public NewsDetail(String url) {
        super(11,url);
    }

    @Override
    public Fragment getFragment(Context context) {
        Fragment fm = new NewDetailFragment();
        Bundle args = new Bundle();
        args.putString("url",this.url);
        fm.setArguments(args);
        return fm;
    }
}
