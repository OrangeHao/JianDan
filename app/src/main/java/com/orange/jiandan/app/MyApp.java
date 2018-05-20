package com.orange.jiandan.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orange.jiandan.model.MyObjectBox;

import io.objectbox.BoxStore;

/**
 * created by czh on 2018-03-19
 */

public class MyApp extends Application{

    private static MyApp mInstance;

    private static BoxStore sBoxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;

        initTools();
    }

    private void initTools(){
        Fresco.initialize(this);

        sBoxStore = MyObjectBox.builder().androidContext(this).build();
    }

    public static BoxStore getBoxStore(){
        return sBoxStore;
    }

    public static MyApp getInstance(){
        return mInstance;
    }
}
