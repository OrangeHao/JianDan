package com.orange.jiandan.ui.jsoup.data;

import android.os.Parcel;

/**
 * created by czh on 2018/5/6
 */
public class GuiMi extends Books{

    public GuiMi() {
        this.setUrl("http://www.bequge.com/0_124/");
        this.setBaseUrl("http://www.bequge.com");
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

    protected GuiMi(Parcel in) {
        super(in);
    }

    public static final Creator<GuiMi> CREATOR = new Creator<GuiMi>() {
        @Override
        public GuiMi createFromParcel(Parcel source) {
            return new GuiMi(source);
        }

        @Override
        public GuiMi[] newArray(int size) {
            return new GuiMi[size];
        }
    };
}
