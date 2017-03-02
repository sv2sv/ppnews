package com.android.ppnews.tabfragment.detail;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import java.util.Locale;

/**
 * Created by wangyao on 2/3/17.
 */
public abstract  class Detail implements Parcelable{
    private int type;
    protected String url;

    protected Detail(int i, String url ) {
        this.type = i;
        this.url = url;
    }


    public static final Creator<Detail> CREATOR = new Creator<Detail>() {
        @Override
        public Detail createFromParcel(Parcel in) {
            switch (in.readInt()){
                case 11:
                    return new NewsDetail(in.readString());
                default:
                    throw new IllegalStateException();
            }
        }

        @Override
        public Detail[] newArray(int size) {
            return new Detail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeString(this.url);
    }

    public abstract Fragment getFragment(Context context);

    public boolean equals(Object obj) {
        if ((obj instanceof Detail) && ((Detail) obj).type == this.type) {
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
