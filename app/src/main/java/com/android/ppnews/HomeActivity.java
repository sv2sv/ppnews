package com.android.ppnews;

import android.view.MenuItem;

import com.android.ppnews.tabfragment.HomeFragment;

public class HomeActivity extends PPActivity {


    private HomeFragment mHomeFragment;

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }


    @Override
    protected void init() {
        super.init();
        this.mHomeFragment = PPDepend.getFragment(this, R.id.home_fragment);


        getProgressBar(R.id.progress);

    }

    @Override
    public void onBackPressed() {
        if (!this.mHomeFragment.handleOnBackPressed()) {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        PPDepend.showToast(item.getItemId(),1);
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
