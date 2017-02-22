package com.android.ppnews;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by wangyao on 17/2/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG = this.getClass().getName();
    protected PPApplication application;
    private int color;
    private static final int INVALID_VAL = -1;
    protected ViewGroup mContentView;
    protected SharedPreferences sp;
    public static final String KEY_CHANGE_TEXTSIZE = "change text size";
    public static final int TEXTSIZE_BIG = 2;
    public static final int TEXTSIZE_MID = 1;
    public static final int TEXTSIZE_SMA = 0;
    protected SpListener mSpListener = new SpListener();

    private ProgressBar mProgressBar;

    public class SpListener implements SharedPreferences.OnSharedPreferenceChangeListener {

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Log.i(TAG, "onSharedPreferenceChanged");
            int size;
            switch (key) {
                case KEY_CHANGE_TEXTSIZE:
                    changeTextSize();
                    break;
                default:
                    break;
            }

        }
    }

    private void changeTextSize() {
        switch (sp.getInt(KEY_CHANGE_TEXTSIZE, 0)) {
            case TEXTSIZE_MID:
                setChildTextSize(mContentView, 14, 15);
                break;
            case TEXTSIZE_BIG:
                setChildTextSize(mContentView, 19, 20);
                break;
            case TEXTSIZE_SMA:
                setChildTextSize(mContentView, 9, 10);
                break;
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "oncreate");
        super.onCreate(savedInstanceState);
        application = (PPApplication) this.getApplication();
        sp = application.getSp();
        sp.registerOnSharedPreferenceChangeListener(mSpListener);

        setContentView(getLayout());
        setToolbar(true);
        mContentView = (ViewGroup) this.findViewById(android.R.id.content);
      //  setStatusBarTransparent(true);
        setFullScreen(true);
        application.addActivity(this);
        init();

    }

    protected void init(){

    }
    public void setFullScreen(boolean enable){
        if (enable) {
         /*   Window window = this.getWindow();
           window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);*/

            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LOW_PROFILE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);

        }
    }

    private void setToolbar(boolean enable) {
        if (enable) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        }
    }

    public abstract int getLayout();

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    public void setStatusBarColor(int statusColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (statusColor != INVALID_VAL) {
                this.getWindow().setStatusBarColor(statusColor);
            }
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            int color = this.getColor(R.color.colorPrimaryDark);
            if (statusColor != INVALID_VAL) {
                color = statusColor;
            }
            View statusBarView = new View(this);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight());
            statusBarView.setBackgroundColor(color);
            mContentView.addView(statusBarView, lp);
        }
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = this.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void setStatusBarTransparent(boolean enable) {
        if (enable) {
            Window window = this.getWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);   //这里动态修改颜色
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //透明导航栏
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                mContentView.setFitsSystemWindows(true);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    public void setChildTextSize(ViewGroup parent, int tsize, int bsizes) {
        Log.i(TAG, "setChildTextSize: ");
        int size = parent.getChildCount();
        for (int i = 0; i < size; i++) {
            View view = parent.getChildAt(i);
            if (view instanceof ViewGroup) {
                setChildTextSize((ViewGroup) view, tsize, bsizes);
            } else if (view instanceof TextView) {
                ((TextView) view).setTextSize(tsize);
            } else if (view instanceof Button) {
                ((Button) view).setTextSize(bsizes);
            }
        }
        //parent.postInvalidate();
    }
    @Override
    protected void onResume() {
        if (sp.getInt(KEY_CHANGE_TEXTSIZE, 0) != TEXTSIZE_MID) {
            changeTextSize();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        sp.unregisterOnSharedPreferenceChangeListener(mSpListener);
        super.onDestroy();
        application.removeActivity(this);
    }

    //布局中Fragment的ID
    protected abstract int getFragmentContentId();


    protected void addFragment(BaseFragment fragment){
        if(null!=fragment){
            getFragmentManager().beginTransaction().replace(getFragmentContentId(),fragment,fragment.getClass()
            .getSimpleName()).addToBackStack(fragment.getClass().getSimpleName()).commitAllowingStateLoss();
        }
    }

    protected void removeFragment(BaseFragment fragment){
        if(getFragmentManager().getBackStackEntryCount()>1){
            getFragmentManager().popBackStack();
        }else {
            finish();
        }
    }

    protected void getProgressBar(int resid) {
        mProgressBar = (ProgressBar) this.findViewById(resid);
    }

    public void showProgress(boolean show) {
        if (show) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

}
