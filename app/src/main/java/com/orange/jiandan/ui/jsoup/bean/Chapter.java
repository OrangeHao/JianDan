package com.orange.jiandan.ui.jsoup.bean;

import java.io.Serializable;

/**
 * created by czh on 2018/3/29
 */

public class Chapter implements Serializable{
    private String url;
    private String title;

    public Chapter(String url, String title){
        this.url=url;
        this.title=title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
