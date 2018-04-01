package com.orange.jiandan.ui.jsoup;

import android.support.annotation.Nullable;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orange.jiandan.R;

import java.util.List;

/**
 * created by czh on 2018/3/29
 */

public class ChapterListAdapter extends BaseQuickAdapter<Chapter,BaseViewHolder> {

    public ChapterListAdapter(int layoutResId, @Nullable List<Chapter> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Chapter item) {
        helper.setText(R.id.chapter_title,item.getTitle());
    }
}
