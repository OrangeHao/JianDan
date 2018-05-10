package com.orange.jiandan.ui.news;

import com.orange.jiandan.model.NewsBean;

import java.util.List;

/**
 * created by czh on 2018/5/9
 */
public interface NewsView {

    void getRefreshNews(List<NewsBean.PostsBean> data);
    void getLoadMoreNews(List<NewsBean.PostsBean> data);

    void getNewsFailed();
}
