package com.orange.jiandan.ui.home;

import android.os.Bundle;

import com.orange.jiandan.R;
import com.orange.jiandan.base.RxLazyFragment;

/**
 * created by czh on 2018-03-19
 */

public class DuanziFragment  extends RxLazyFragment {

    public static DuanziFragment newInstance() {
        return new DuanziFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_duanzi;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }
}