package com.android.ppnews.tabfragment.helper;

import android.graphics.PointF;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by wangyao on 24/2/17.
 */

public class RefreshHelper {
    public static void jumpToTop(RecyclerView recyclerView, boolean z) {
        if (!z || firstVisibleItemPosition(recyclerView) > 10) {
            recyclerView.scrollToPosition(0);
        } else {
            smoothScrollToPositionWithOffset(recyclerView, 0, 0, 15.0f, false);
        }
    }


    public static int firstVisibleItemPosition(RecyclerView recyclerView) {
        if (recyclerView.getLayoutManager() == null) {
            return -1;
        }
        int childCount = recyclerView.getLayoutManager().getChildCount();
        int i = 0;
        while (true) {
            int i2 = childCount;
            if (i == i2) {
                return -1;
            }
            View childAt = recyclerView.getLayoutManager().getChildAt(i);
            if (childAt.getBottom() > 0) {
                return recyclerView.getChildAdapterPosition(childAt);
            }
            i2 = 1;
            i = i2 + i;
        }
    }

    public void scrollToPosition(RecyclerView recyclerView, RecyclerView.OnScrollListener onScrollListener, int i) {
        recyclerView.scrollToPosition(i);
        if (recyclerView.getChildCount() > 1) {
            forceScrollEvent(recyclerView, onScrollListener);
        }
    }

    public static void forceScrollEvent(RecyclerView recyclerView, RecyclerView.OnScrollListener onScrollListener) {
        onScrollListener.onScrolled(recyclerView, 0, 0);
    }

    public static void smoothScrollToPositionWithOffset(RecyclerView recyclerView, int i, int i2, float f, boolean z) {
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager != null) {
            final int i3 = i2;
            final float f2 = f;
            RecyclerView.SmoothScroller anonymousClass5 = new LinearSmoothScroller(recyclerView.getContext()) {
                private boolean prevUserInteractionEnabled;

                public PointF computeScrollVectorForPosition(int i) {
                    if (layoutManager.getChildCount() == 0) {
                        return null;
                    }
                    return new PointF(0.0f, (float) (i < layoutManager.getPosition(layoutManager.getChildAt(0)) ? -1 : 1));
                }

                protected void onTargetFound(View view, RecyclerView.State state, Action action) {
                    Action action2 = new Action(0, 0);
                    action2.update(action.getDx(), action.getDy(), action.getDuration(), action.getInterpolator());
                    super.onTargetFound(view, state, action2);
                    if (action2.getDuration() > 0) {
                        action2.setDy(action2.getDy() - i3);
                        if (action2.getDy() != 0 || action2.getDx() != 0 || action2.getInterpolator() != action.getInterpolator()) {
                            action.update(action2.getDx(), action2.getDy(), action2.getDuration(), action2.getInterpolator());
                        }
                    }
                }

                protected int getVerticalSnapPreference() {
                    return -1;
                }

                protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                    return f2 / ((float) displayMetrics.densityDpi);
                }

            };
            anonymousClass5.setTargetPosition(i);
            layoutManager.startSmoothScroll(anonymousClass5);
        }
    }

    public static void expandToolbar(AppBarLayout appBarLayout) {
        appBarLayout.setExpanded(true);
    }
}
