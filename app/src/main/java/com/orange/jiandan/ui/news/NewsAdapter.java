package com.orange.jiandan.ui.news;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orange.jiandan.R;
import com.orange.jiandan.model.NewsBean;

import java.util.List;

/**
 * created by czh on 2018/5/9
 */
public class NewsAdapter extends BaseQuickAdapter<NewsBean.PostsBean,BaseViewHolder>{

    public NewsAdapter(@Nullable List data) {
        super(R.layout.item_news, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, NewsBean.PostsBean item) {
        helper.setText(R.id.news_title, item.getTitle());
        helper.setText(R.id.news_time, "xxxxx");
        helper.setText(R.id.news_comment_count, "xxxxx");
    }
}
