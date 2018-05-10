package com.orange.jiandan.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.orange.jiandan.app.MyApp;

/**
 * Created by hcc on 16/8/4 21:18
 * 100332338@qq.com
 * <p/>
 * Toast工具类
 */
public class ToastUtil {

    private static Toast mToast;

    private static Toast getSingleInstance(){
        if (mToast==null){
            synchronized (ToastUtil.class){
                if (mToast==null){
                    mToast=new Toast(MyApp.getInstance());
                    mToast.setDuration(Toast.LENGTH_LONG);
                }
            }
        }
        return mToast;
    }

    public static void showSingleToast(String message){
        getSingleInstance().setText(message);
        getSingleInstance().show();
    }

    public static void showShort(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void showLong(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

    public static void LongToast(final String text) {
        new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(MyApp.getInstance(), text, Toast.LENGTH_LONG).show());
    }

    public static void LongToast(final int stringId) {
        new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(MyApp.getInstance(), stringId, Toast.LENGTH_LONG).show());
    }

    public static void ShortToast(final String text) {
        new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(MyApp.getInstance(), text, Toast.LENGTH_SHORT).show());
    }

    public static void ShortToast(final int stringId) {
        new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(MyApp.getInstance(), stringId, Toast.LENGTH_SHORT).show());
    }
}
