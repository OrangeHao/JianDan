package com.orange.jiandan.data.api;

import com.orange.jiandan.model.NewsBean;
import com.orange.jiandan.model.NewsDetail;
import com.orange.jiandan.model.PicsBean;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * created by czh on 2018/5/9
 */
public interface JianDanApi {


    //新闻列表
    //@GET("http://i.jandan.net/?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,excerpt,comment_count,comment_status,custom_fields&page={pageIndex}&custom_fields=thumb_c,views&dev=1")
    @GET("http://i.jandan.net/")
    Single<NewsBean> getNews(
            @Query("oxwlxojflwblxbsapi")String way,
            @Query("include")String include,
            @Query("page")String page,
            @Query("custom_fields")String custom_fields,
            @Query("dev")String dev);


    //新闻详情
    //http://i.jandan.net/?oxwlxojflwblxbsapi=get_post&id=97943&include=content,date,modified
    @GET("http://i.jandan.net/")
    Single<NewsDetail> getNewsDetail(
            @Query("oxwlxojflwblxbsapi")String way,
            @Query("id")String id,
            @Query("include")String include);


    //图片列表
    //http://i.jandan.net/?oxwlxojflwblxbsapi=jandan.get_ooxx_comments&page=1
    @GET("http://i.jandan.net/")
    Single<PicsBean> getNicePics(
            @Query("oxwlxojflwblxbsapi")String way,
            @Query("page")String page);
}
