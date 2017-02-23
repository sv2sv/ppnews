package com.android.ppnews;

/**
 * Created by wangyao on 23/2/17.
 */

public class FootBarHelper {
    private static final int DEFAULT_COLOR_ICON = NSDepend.getColorResource(R.color.grey_600);
    private static final int DEFAULT_COLOR_TEXT = NSDepend.getColorResource(R.color.grey_700);
    private static final int SELECTED_COLOR = NSDepend.getColorResource(R.color.app_color_material);
    private final int defaultTextSize = NSDepend.resources().getDimensionPixelSize(R.dimen.tab_button_text_size);
    private final OnTabSelectedListener listener;
    private final int selectedTextSize = NSDepend.resources().getDimensionPixelSize(R.dimen.tab_button_text_size_selected);
    private final ViewGroup tabBar;
    private final Map<HomeTab, ViewGroup> tabs = new ArrayMap();
}
