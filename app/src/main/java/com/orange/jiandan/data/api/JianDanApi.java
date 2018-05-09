package com.orange.jiandan.data.api;

import com.orange.jiandan.model.NewsBean;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * created by czh on 2018/5/9
 */
public interface JianDanApi {


    @GET("http://i.jandan.net/?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,excerpt,comment_count,comment_status,custom_fields&page={pageIndex}&custom_fields=thumb_c,views&dev=1")
    Single<NewsBean> getNews(@Path("pageIndex")String pageIndex);


}
