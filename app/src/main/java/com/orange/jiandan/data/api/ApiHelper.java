package com.orange.jiandan.data.api;

import android.util.Log;

import com.orange.jiandan.data.httplog.HttpLogger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * created by czh on 2018/5/9
 */
public class ApiHelper {
    private static final String TAG = "jiandanapi";

    private static final String BASE_URL="http://i.jandan.net";

    private static ApiHelper sInstance;
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;

    private ApiHelper(){
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        mOkHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(logInterceptor).build();
//        mOkHttpClient = new OkHttpClient.Builder()
//                .addNetworkInterceptor(
//                        new Interceptor() {
//                            @Override
//                            public Response intercept(Chain chain) throws IOException {
//                                Request request = chain.request();
//
//                                //在这里获取到request后就可以做任何事情了
//                                Response response = chain.proceed(request);
//                                Log.d(TAG, "intercept: "+request.url().toString());
//                                return response;
//                            }
//                        }
//                ).build();

        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static ApiHelper getInstance(){
        if (sInstance == null){
            synchronized (ApiHelper.class){
                if (sInstance == null){
                    sInstance = new ApiHelper();
                }
            }
        }
        return sInstance;
    }

    public Retrofit getRetrofit(){
        return mRetrofit;
    }

    public OkHttpClient getOkHttpClient(){
        return mOkHttpClient;
    }
}
