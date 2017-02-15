package com.android.ppnews.pojo;

import java.util.List;

/**
 * Created by wangyao on 15/2/17.
 */

public class Category {


    /**
     * link : /
     * name : 精选
     * child : []
     */

    private String clink;
    private String cname;
    private List<?> child;

    public String getClink() {
        return clink;
    }

    public void setClink(String clink) {
        this.clink = clink;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public List<?> getChild() {
        return child;
    }

    public void setChild(List<?> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "Category{" +
                "clink='" + clink + '\'' +
                ", cname='" + cname + '\'' +
                ", child=" + child +
                '}';
    }
}
