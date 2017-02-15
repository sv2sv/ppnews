package com.android.ppnews.pojo;

/**
 * Created by wangyao on 15/2/17.
 */

public class Data {
    /**
     * category : 有戏
     * img : http://image.thepaper.cn/image/5/444/670.gif
     * title : 台剧《如朕亲临》：鱼塘+电子竞技
     * link : newsDetail_forward_1607815
     * time : 35分钟前
     * isCategory : true
     * category_link : list_25448
     */

    private String category;
    private String img;
    private String title;
    private String link;
    private String time;
    private boolean isCategory;
    private String category_link;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isIsCategory() {
        return isCategory;
    }

    public void setIsCategory(boolean isCategory) {
        this.isCategory = isCategory;
    }

    public String getCategory_link() {
        return category_link;
    }

    public void setCategory_link(String category_link) {
        this.category_link = category_link;
    }
}
