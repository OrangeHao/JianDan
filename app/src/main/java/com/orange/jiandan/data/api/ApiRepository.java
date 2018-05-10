package com.orange.jiandan.data.api;

import com.orange.jiandan.data.DataResource;
import com.orange.jiandan.model.NewsBean;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;

/**
 * created by czh on 2018/5/9
 */
public class ApiRepository implements DataResource{

    private static ApiRepository sInstance;
    private Retrofit mRetrofit;
    private JianDanApi mJianDanApi;

    private ApiRepository(){
        mRetrofit = ApiHelper.getInstance()
                .getRetrofit();

        mJianDanApi = mRetrofit.create(JianDanApi.class);
    }

    public static ApiRepository getInstance(){
        if (sInstance == null){
            synchronized (ApiRepository.class){
                if (sInstance == null){
                    sInstance = new ApiRepository();
                }
            }
        }
        return sInstance;
    }

    @Override
    public Single<List<NewsBean.PostsBean>> getNews(int pageIndex) {
        return mJianDanApi.getNews(
                "get_recent_posts",
                "url,date,tags,author,title,excerpt,comment_count,comment_status,custom_fields",
                ""+pageIndex,
                "thumb_c,views",
                "1")
                .map(bean->bean.getPosts());
    }



}
