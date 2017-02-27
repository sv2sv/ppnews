package com.android.ppnews.tabfragment.tab;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.android.ppnews.tabfragment.HomeTabFragment;

import java.util.Locale;

/**
 * Created by wy on 17-2-23.
 */

public abstract class HomeTab implements Parcelable{
    private int type ;

    public static final Parcelable.Creator<HomeTab> CREATOR = new Parcelable.Creator<HomeTab>() {
        @Override
        public HomeTab createFromParcel(Parcel source) {
            switch (source.readInt()) {
                case 0:
                    return HomeTab.FOR_YOU_TAB;
                case 1:
                    return HomeTab.LIBRARY_TAB;
                case 2:
                    return HomeTab.EXPLORE_TAB;
                case 3:
                    return HomeTab.LATER_TAB;
                default:
                    throw new IllegalStateException();
            }
        }

        @Override
        public HomeTab[] newArray(int size) {
            return new HomeTab[size];
        }
    };

    protected  HomeTab(int i){
        this.type =i ;

    }


    public static final HomeTab EXPLORE_TAB = new ExploreTab();
    public static final HomeTab FOR_YOU_TAB = new ForYouTab();
    public static final HomeTab LATER_TAB = new SavedTab();
    public static final HomeTab LIBRARY_TAB = new LibraryTab();
    public static final HomeTab DEFAULT_TAB = FOR_YOU_TAB;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
    }

    public abstract HomeTabFragment getFragment(Context context);

    public abstract int getTabLayoutId();

    public boolean equals(Object obj) {
        if ((obj instanceof HomeTab) && ((HomeTab) obj).type == this.type) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.type;
    }

    public String toString() {
        return String.format(Locale.US, "{type: %s}", new Object[]{Integer.valueOf(this.type)});
    }
}
