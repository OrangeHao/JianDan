package com.orange.jiandan.app;

import android.app.Application;

/**
 * created by czh on 2018-03-19
 */

public class MyApp extends Application{

    private static MyApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }

    public static MyApp getInstance(){
        return mInstance;
    }
}
