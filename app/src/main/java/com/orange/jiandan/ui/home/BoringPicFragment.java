package com.orange.jiandan.ui.home;

import android.os.Bundle;

import com.orange.jiandan.R;
import com.orange.jiandan.base.RxLazyFragment;

/**
 * created by czh on 2018-03-19
 */

public class BoringPicFragment extends RxLazyFragment {

    public static BoringPicFragment newInstance() {
        return new BoringPicFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_boring_pic;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }

}