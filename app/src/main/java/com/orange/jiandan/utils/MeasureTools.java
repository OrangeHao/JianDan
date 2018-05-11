package com.orange.jiandan.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * created by czh on 2018/5/11
 */
public class MeasureTools {

    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
