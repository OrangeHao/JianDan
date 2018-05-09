package com.orange.jiandan.data;

import com.orange.jiandan.model.NewsBean;

import java.util.List;

import io.reactivex.Single;

/**
 * created by czh on 2018/5/9
 */
public interface DataResource {

    Single<List<NewsBean.PostsBean>> getNews(int pageIndex);

}
