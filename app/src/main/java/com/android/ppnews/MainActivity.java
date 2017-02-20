package com.android.ppnews;

import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends BaseActivity {


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private boolean a = true;

    public void click(View v) {
        if (a) {
            application.getSp().edit()
                    .putInt(BaseActivity.KEY_CHANGE_TEXTSIZE, BaseActivity.TEXTSIZE_BIG)
                    .commit();
            application.showToast(sp.getInt(BaseActivity.KEY_CHANGE_TEXTSIZE, 0) + "", 1);
        } else {
            application.getSp().edit()
                    .putInt(BaseActivity.KEY_CHANGE_TEXTSIZE, BaseActivity.TEXTSIZE_SMA)
                    .commit();
            application.showToast(sp.getInt(BaseActivity.KEY_CHANGE_TEXTSIZE, 0) + "", 1);
        }
        a = !a;
    }
    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
