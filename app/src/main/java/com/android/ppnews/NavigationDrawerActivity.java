package com.android.ppnews;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;

/**
 * Created by wangyao on 24/2/17.
 */


public abstract class NavigationDrawerActivity extends PPActivity {
    private DrawerLayout drawer;
    private ViewGroup drawerRootView;
    private boolean isAccountListExpanded = false;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;

    public abstract Fragment getPrimaryVisibleFragment();

    protected NavigationDrawerActivity() {
        super();
    }

    @Override
    public void onPostCreate(Bundle bundle) {
        boolean z;
        super.onPostCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.drawer = (DrawerLayout) getContentView().findViewById(R.id.drawer);
        this.mDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.play_drawer_open, R.string.play_drawer_close);
        this.drawer.addDrawerListener(this.mDrawerToggle);
        this.mNavigationView = (NavigationView) getContentView().findViewById(R.id.drawer_nav);
        this.drawerRootView = (ViewGroup) mNavigationView.getHeaderView(0);

        if (bundle == null) {
            z = false;
        } else {
            z = bundle.getBoolean("key_account_list_expanded");
        }
        this.isAccountListExpanded = z;
       /* this.drawer.initializeDrawer(this.isAccountListExpanded, showDownloadToggle());*/
        setDrawerIndicatorEnabled(getDrawerIndicatorEnabled());

    }

    protected boolean getDrawerIndicatorEnabled() {
        return false;
    }

    @Override
    protected void init() {
        super.init();

    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }


    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.drawer != null) {
            this.mDrawerToggle.onConfigurationChanged(configuration);
        }
    }

    public void setSupportActionBar(Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        syncDrawerIndicator();
    }

    public void syncDrawerIndicator() {
        if (this.mDrawerToggle != null) {
            this.mDrawerToggle.syncState();
        }

    }

    public boolean isDrawerVisible() {

        return this.drawer != null && this.drawer.isDrawerVisible(drawerRootView);
    }

    public void closeDrawer() {
        if (null == drawer) {
            throw new NullPointerException("drawer is null");
        }
        this.mDrawerToggle.onDrawerClosed(drawer);
    }


    public boolean isDrawerIndicatorEnabled() {

        if (this.mDrawerToggle != null) {
            return this.mDrawerToggle.isDrawerIndicatorEnabled();
        }
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.drawer == null) {
            return this.mDrawerToggle != null && this.mDrawerToggle.onOptionsItemSelected(menuItem);
        }
        return true;
    }

    public boolean onOptionsItemSelectedCloseDrawerIfNeeded(MenuItem menuItem) {

        if (this.drawer != null && this.isDrawerIndicatorEnabled()) {
            return this.onOptionsItemSelected(menuItem);
        }
        if (isDrawerVisible()) {
            closeDrawer();
            return true;
        }

        return false;
    }

    public void onBackPressed() {
        if (this.drawer == null || !this.drawer.isDrawerOpen(drawerRootView)) {
            super.onBackPressed();
        } else {
            closeDrawer();
        }
    }


    public void setDrawerIndicatorEnabled(boolean z) {
        if (null == drawer) {
            throw new NullPointerException("drawer is null");
        }
        if (this.mDrawerToggle != null) {
            this.mDrawerToggle.setDrawerIndicatorEnabled(z);
        }
    }
}