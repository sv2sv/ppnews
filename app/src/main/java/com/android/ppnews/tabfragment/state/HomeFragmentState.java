package com.android.ppnews.tabfragment.state;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.ppnews.tabfragment.tab.HomeTab;

/**
 * Created by wangyao on 24/2/17.
 */

public class HomeFragmentState implements Parcelable {

    public static final Creator<HomeFragmentState> CREATOR = new Creator<HomeFragmentState>() {
        public HomeFragmentState createFromParcel(Parcel parcel) {
            return new HomeFragmentState((HomeTab) parcel.readParcelable(HomeTab.class.getClassLoader()), parcel.readString());
        }

        public HomeFragmentState[] newArray(int i) {
            return new HomeFragmentState[i];
        }
    };

    public final HomeTab homeTab;
    public final String initialCardId;

    public HomeFragmentState() {
        this(HomeTab.FOR_YOU_TAB);
    }

    public HomeFragmentState(HomeTab homeTab) {
        this(homeTab, null);
    }

    public HomeFragmentState(HomeTab homeTab, String str) {
        this.homeTab = homeTab;
        this.initialCardId = str;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HomeFragmentState)) {
            return false;
        }
        HomeFragmentState homeFragmentState = (HomeFragmentState) obj;
        if(this.homeTab == homeFragmentState.homeTab || (this.homeTab !=null && this.homeTab.equals(homeFragmentState.homeTab))){
            return true;
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.homeTab, flags);
        dest.writeString(this.initialCardId);
    }
}
