package com.android.ppnews.tabfragment;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wy on 17-2-26.
 */
public class ForYouFragmentState implements Parcelable {
    protected ForYouFragmentState(Parcel in) {
    }

    public static final Creator<ForYouFragmentState> CREATOR = new Creator<ForYouFragmentState>() {
        @Override
        public ForYouFragmentState createFromParcel(Parcel in) {
            return new ForYouFragmentState(in);
        }

        @Override
        public ForYouFragmentState[] newArray(int size) {
            return new ForYouFragmentState[size];
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
