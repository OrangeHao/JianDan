package com.orange.jiandan.ui.jsoup.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * created by czh on 2018/5/6
 */

public class Books implements Parcelable {

    protected String url;
    protected String baseUrl;
    protected String chapter;
    protected String chaptertext;
    protected boolean hasExtraUrl=false;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getChaptertext() {
        return chaptertext;
    }

    public void setChaptertext(String chaptertext) {
        this.chaptertext = chaptertext;
    }

    public boolean isHasExtraUrl() {
        return hasExtraUrl;
    }

    public void setHasExtraUrl(boolean hasExtraUrl) {
        this.hasExtraUrl = hasExtraUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.baseUrl);
        dest.writeString(this.chapter);
        dest.writeString(this.chaptertext);
        dest.writeByte(this.hasExtraUrl ? (byte) 1 : (byte) 0);
    }

    public Books() {
    }

    protected Books(Parcel in) {
        this.url = in.readString();
        this.baseUrl = in.readString();
        this.chapter = in.readString();
        this.chaptertext = in.readString();
        this.hasExtraUrl = in.readByte() != 0;
    }

}
