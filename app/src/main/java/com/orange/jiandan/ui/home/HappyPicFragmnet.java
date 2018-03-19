package com.orange.jiandan.ui.home;

import android.os.Bundle;

import com.orange.jiandan.R;
import com.orange.jiandan.base.RxLazyFragment;

/**
 * created by czh on 2018-03-19
 */

public class HappyPicFragmnet extends RxLazyFragment {

    public static HappyPicFragmnet newInstance() {
        return new HappyPicFragmnet();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_happy_pic;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }
}