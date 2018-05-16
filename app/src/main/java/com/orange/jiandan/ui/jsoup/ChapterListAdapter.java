package com.orange.jiandan.ui.jsoup;

import android.support.annotation.Nullable;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orange.jiandan.R;
import com.orange.jiandan.model.novel.ChapterMessage;
import com.orange.jiandan.ui.jsoup.bean.Chapter;

import java.util.List;

/**
 * created by czh on 2018/3/29
 */

public class ChapterListAdapter extends BaseQuickAdapter<ChapterMessage,BaseViewHolder> {

    public ChapterListAdapter(int layoutResId, @Nullable List<ChapterMessage> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChapterMessage item) {
        helper.setText(R.id.chapter_title,item.getTitle());
    }
}
