package com.orange.jiandan.ui.home;

import android.os.Bundle;

import com.orange.jiandan.R;
import com.orange.jiandan.base.RxLazyFragment;

/**
 * created by czh on 2018-03-19
 */

public class NewFragment extends RxLazyFragment{

    public static NewFragment newInstance() {
        return new NewFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_news;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }
}
