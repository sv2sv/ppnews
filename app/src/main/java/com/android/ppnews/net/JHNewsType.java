package com.android.ppnews.net;

/**
 * Created by wangyao on 17/2/17.
 */

public enum JHNewsType {

    TOP("top"),
    SH("shehui"),
    GN("guonei"),
    GJ("guoji"),
    YL("yule"),
    TY("tiyu"),
    JS("junshi"),
    KJ("keji"),
    CJ("caijing"),
    SS("shishang");
    public String KEY ;
    public String str;

    JHNewsType(java.lang.String str) {
        this.str = str;
        this.KEY = "0ce28d8acf22bf9396f4e6a475aded0b";
    }
}
