package com.orange.jiandan.ui.jsoup.books;

import android.os.Parcel;

/**
 * created by czh on 2018/5/6
 */

public class JianLaiLe extends Book {

    public JianLaiLe() {
        this.setUrl("http://www.jianlaixiaoshuo.com/");
        this.setChapter("chapterlist");
        this.setChaptertext("BookText");
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    protected JianLaiLe(Parcel in) {
        super(in);
    }

    public static final Creator<JianLaiLe> CREATOR = new Creator<JianLaiLe>() {
        @Override
        public JianLaiLe createFromParcel(Parcel source) {
            return new JianLaiLe(source);
        }

        @Override
        public JianLaiLe[] newArray(int size) {
            return new JianLaiLe[size];
        }
    };
}
