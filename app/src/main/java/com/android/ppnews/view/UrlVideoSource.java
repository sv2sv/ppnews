package com.android.ppnews.view;

import android.os.Parcel;

/**
 * Created by wangyao on 24/2/17.
 */

public class UrlVideoSource implements VideoPlayerView.VideoSource {
    public static final Creator<UrlVideoSource> CREATOR = new Creator<UrlVideoSource>() {
        public UrlVideoSource createFromParcel(Parcel parcel) {
            return new UrlVideoSource(parcel);
        }

        public UrlVideoSource[] newArray(int i) {
            return new UrlVideoSource[i];
        }
    };
    final boolean looping;
    public final String url;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.url);
        parcel.writeInt(this.looping ? 1 : 0);
    }

    public UrlVideoSource(String str) {
        this(str, false);
    }

    public UrlVideoSource(String str, boolean z) {
        this.url = str;
        this.looping = z;
    }

    public String getId() {
        return this.url;
    }

    public String getUrl() {
        return this.url;
    }

    protected UrlVideoSource(Parcel parcel) {
        boolean z = true;
        this.url = parcel.readString();
        if (parcel.readInt() != 1) {
            z = false;
        }
        this.looping = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UrlVideoSource)) {
            return false;
        }
        UrlVideoSource urlVideoSource = (UrlVideoSource) obj;
        if (this.url == null ? urlVideoSource.url != null : !this.url.equals(urlVideoSource.url)) {
            return false;
        }
        if (this.looping != urlVideoSource.looping) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.url != null) {
            hashCode = this.url.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode *= 31;
        if (this.looping) {
            i = 1;
        }
        return hashCode + i;
    }
}