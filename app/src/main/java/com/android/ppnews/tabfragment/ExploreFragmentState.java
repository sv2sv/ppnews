package com.android.ppnews.tabfragment;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wy on 17-2-26.
 */
public class ExploreFragmentState implements Parcelable{
    protected ExploreFragmentState(Parcel in) {
    }

    public static final Creator<ExploreFragmentState> CREATOR = new Creator<ExploreFragmentState>() {
        @Override
        public ExploreFragmentState createFromParcel(Parcel in) {
            return new ExploreFragmentState(in);
        }

        @Override
        public ExploreFragmentState[] newArray(int size) {
            return new ExploreFragmentState[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
