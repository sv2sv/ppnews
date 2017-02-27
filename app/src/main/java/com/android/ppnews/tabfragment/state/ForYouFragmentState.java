package com.android.ppnews.tabfragment.state;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wy on 17-2-26.
 */
public class ForYouFragmentState implements Parcelable {
    protected ForYouFragmentState() {
    }

    public static final Creator<ForYouFragmentState> CREATOR = new Creator<ForYouFragmentState>() {
        @Override
        public ForYouFragmentState createFromParcel(Parcel in) {
            return new ForYouFragmentState();
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

    public boolean equals(Object obj) {
        if (!(obj instanceof ForYouFragmentState)) {
            return false;
        }
        if(this == obj){
            return  true;
        }
        return false;
    }

}
