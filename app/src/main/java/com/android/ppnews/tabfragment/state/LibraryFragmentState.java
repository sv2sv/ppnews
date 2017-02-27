package com.android.ppnews.tabfragment.state;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wangyao on 27/2/17.
 */

public class LibraryFragmentState implements Parcelable{
    protected LibraryFragmentState() {
    }

    public static final Creator<LibraryFragmentState> CREATOR = new Creator<LibraryFragmentState>() {
        @Override
        public LibraryFragmentState createFromParcel(Parcel in) {
            return new LibraryFragmentState();
        }

        @Override
        public LibraryFragmentState[] newArray(int size) {
            return new LibraryFragmentState[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ForYouFragmentState)) {
            return false;
        }

        if(this == obj){
            return  true;
        }
        return  false;
    }
}
