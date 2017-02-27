package com.android.ppnews.tabfragment.state;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Objects;

/**
 * Created by wangyao on 27/2/17.
 */
public class SavedFragmentState implements Parcelable{
    protected SavedFragmentState() {
    }

    public static final Creator<SavedFragmentState> CREATOR = new Creator<SavedFragmentState>() {
        @Override
        public SavedFragmentState createFromParcel(Parcel in) {
            return new SavedFragmentState();
        }

        @Override
        public SavedFragmentState[] newArray(int size) {
            return new SavedFragmentState[size];
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

    public int hashCode() {
        return Objects.hashCode(new Object[]{ExploreFragmentState.class});
    }
}
