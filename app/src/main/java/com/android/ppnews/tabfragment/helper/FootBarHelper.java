package com.android.ppnews.tabfragment.helper;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.ppnews.PPDepend;
import com.android.ppnews.R;
import com.android.ppnews.tabfragment.tab.HomeTab;

import java.util.Map;

/**
 * Created by wangyao on 23/2/17.
 */

public class FootBarHelper {
    private static final int DEFAULT_COLOR_ICON = PPDepend.getColorResource(R.color.grey_600);
    private static final int DEFAULT_COLOR_TEXT = PPDepend.getColorResource(R.color.grey_700);
    private static final int SELECTED_COLOR = PPDepend.getColorResource(R.color.app_color_material);
    private final int defaultTextSize = PPDepend.resources().getDimensionPixelSize(R.dimen.foot_button_text_size);
    private final OnTabSelectedListener listener;
    private final int selectedTextSize = PPDepend.resources().getDimensionPixelSize(R.dimen.foot_button_text_size_selected);
    private final ViewGroup mFooterBar;
    private final Map<HomeTab, ViewGroup> tabs = new ArrayMap();

    public void disappearTab() {

        ObjectAnimator
                .ofFloat(mFooterBar,"Alpha",1.0f,0.0f)
                .ofFloat(mFooterBar,"ScaleY",1.0f,0.2f)
                .ofInt(mFooterBar,"ScrollY",0,-100)
                .ofInt()
                .setDuration(3000)
                .start();
    }

    public interface OnTabSelectedListener {
        void onTabSelected(HomeTab homeTab);
    }

    public FootBarHelper(ViewGroup footerbar, OnTabSelectedListener listener) {
        this.mFooterBar = footerbar;
        this.listener = listener;
        setupFootButtons();
    }

    public void show(){
        this.mFooterBar.setVisibility(View.VISIBLE);
    }

    public void hide(){
        this.mFooterBar.setVisibility(View.GONE);
    }

    private void setupFootButtons(){
        setupTabButton(HomeTab.FOR_YOU_TAB);
        setupTabButton(HomeTab.LIBRARY_TAB);
        setupTabButton(HomeTab.EXPLORE_TAB);
        setupTabButton(HomeTab.LATER_TAB);

    }

    private void setupTabButton(final HomeTab homeTab) {
        ViewGroup viewGroup = (ViewGroup) this.mFooterBar.findViewById(homeTab.getTabLayoutId());
        viewGroup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FootBarHelper.this.listener.onTabSelected(homeTab);
            }
        });
        this.tabs.put(homeTab, viewGroup);
    }

    public void setSelectedTab(HomeTab homeTab) {
        ViewGroup viewGroup = (ViewGroup) this.tabs.get(homeTab);
        updateButtonSelectionState(viewGroup, homeTab, true);
        for (Map.Entry entry : this.tabs.entrySet()) {
            if (entry.getValue() != viewGroup) {
                updateButtonSelectionState((ViewGroup) entry.getValue(), (HomeTab) entry.getKey(), false);
            }
        }
    }

    private void updateButtonSelectionState(ViewGroup viewGroup, HomeTab homeTab, boolean z) {
        viewGroup.setSelected(z);
        TextView textView = (TextView) viewGroup.getChildAt(1);
        updateIcon((ImageView) viewGroup.getChildAt(0), z);
        updateText(textView, z);
    }

    public static void updateIcon(ImageView imageView, boolean z) {
        ColorStateList valueOf;
        Drawable wrap = DrawableCompat.wrap(imageView.getDrawable());
        if (z) {
            valueOf = ColorStateList.valueOf(SELECTED_COLOR);
        } else {
            valueOf = ColorStateList.valueOf(DEFAULT_COLOR_ICON);
        }
        DrawableCompat.setTintList(wrap, valueOf);
        imageView.setImageDrawable(wrap);
    }

    private void updateText(final TextView textView, boolean z) {
        int i;
        textView.setTextColor(z ? SELECTED_COLOR : DEFAULT_COLOR_TEXT);
        float textSize = textView.getTextSize();
        if (z) {
            i = this.selectedTextSize;
        } else {
            i = this.defaultTextSize;
        }
        if (textSize != ((float) i)) {
            ValueAnimator ofFloat;
            if (z) {
                ofFloat = ValueAnimator.ofFloat(new float[]{(float) this.defaultTextSize, (float) this.selectedTextSize});
            } else {
                ofFloat = ValueAnimator.ofFloat(new float[]{(float) this.selectedTextSize, (float) this.defaultTextSize});
            }
            ofFloat.setDuration(z ? 150 : 0);
            ofFloat.setInterpolator(new LinearOutSlowInInterpolator());
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    textView.setTextSize(0, ((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
            });
            ofFloat.start();
        }
    }
}
