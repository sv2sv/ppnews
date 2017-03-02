package com.android.ppnews.tabfragment.state;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.ppnews.tabfragment.HomeFragment;
import com.android.ppnews.tabfragment.detail.Detail;
import com.android.ppnews.tabfragment.tab.HomeTab;

/**
 * Created by wangyao on 24/2/17.
 */

public class HomeFragmentState implements Parcelable {

    public static final Creator<HomeFragmentState> CREATOR = new Creator<HomeFragmentState>() {
        public HomeFragmentState createFromParcel(Parcel parcel) {
            return new HomeFragmentState((HomeTab) parcel.readParcelable(HomeTab.class.getClassLoader()),
                    (Detail) parcel.readParcelable(Detail.class.getClassLoader()),
                    parcel.readString());
        }

        public HomeFragmentState[] newArray(int i) {
            return new HomeFragmentState[i];
        }
    };

    public final HomeTab homeTab;
    public final String initialCardId;
    public final Detail detail;

    public HomeFragmentState() {
        this(HomeTab.FOR_YOU_TAB);
    }

    public HomeFragmentState(HomeTab homeTab) {
        this(homeTab, null, null);
    }

    public HomeFragmentState(HomeTab homeTab, Detail detail, String str){
        this.homeTab = homeTab;
        this.initialCardId = str;
        this.detail = detail;
    }
    public HomeFragmentState(Detail detail){
        this(null, detail, null);
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof HomeFragmentState)) {
            return false;
        }
        HomeFragmentState homeFragmentState = (HomeFragmentState) obj;
        if((this.homeTab == homeFragmentState.homeTab || (this.homeTab !=null && this.homeTab.equals(homeFragmentState.homeTab)))&&
                (this.detail == homeFragmentState.detail || (this.detail !=null && this.detail.equals(homeFragmentState.detail)))){
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
