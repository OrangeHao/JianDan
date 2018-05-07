package com.orange.jiandan.ui.jsoup.books;

import android.os.Parcel;

/**
 * created by czh on 2018/5/7
 */

public class YuLeChunQiu extends Book {

    public YuLeChunQiu() {
        this.setUrl("https://www.booktxt.net/7_7067/");
        this.setBaseUrl("https://www.booktxt.net");
        this.setChapter("list");
        this.setChaptertext("content");
        this.setHasExtraUrl(true);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    protected YuLeChunQiu(Parcel in) {
        super(in);
    }

    public static final Creator<YuLeChunQiu> CREATOR = new Creator<YuLeChunQiu>() {
        @Override
        public YuLeChunQiu createFromParcel(Parcel source) {
            return new YuLeChunQiu(source);
        }

        @Override
        public YuLeChunQiu[] newArray(int size) {
            return new YuLeChunQiu[size];
        }
    };
}
