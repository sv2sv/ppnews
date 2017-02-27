package com.android.ppnews.view;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;

/**
 * Created by wy on 17-2-27.
 */

public class OverlayAppBarBehavior extends AppBarLayout.Behavior {
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int i2, int[] iArr) {
        super.onNestedPreScroll(coordinatorLayout, appBarLayout, view, i, i2, iArr);
        iArr[1] = 0;
    }
}
