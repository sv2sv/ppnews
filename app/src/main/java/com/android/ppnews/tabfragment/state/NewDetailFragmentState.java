package com.android.ppnews.tabfragment.state;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wangyao on 2/3/17.
 */
public class NewDetailFragmentState implements Parcelable {
    protected NewDetailFragmentState(Parcel in) {
    }

    public static final Creator<NewDetailFragmentState> CREATOR = new Creator<NewDetailFragmentState>() {
        @Override
        public NewDetailFragmentState createFromParcel(Parcel in) {
            return new NewDetailFragmentState(in);
        }

        @Override
        public NewDetailFragmentState[] newArray(int size) {
            return new NewDetailFragmentState[size];
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
        if (!(obj instanceof NewDetailFragmentState)) {
            return false;
        }

        if(this == obj){
            return  true;
        }
        return false;
    }


}
