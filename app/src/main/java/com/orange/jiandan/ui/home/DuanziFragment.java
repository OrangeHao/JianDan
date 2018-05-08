package com.orange.jiandan.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orange.jiandan.R;
import com.orange.jiandan.base.BaseLazyFragment;
import com.orange.jiandan.ui.jsoup.chapter.ContentActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * created by czh on 2018-03-19
 */

public class DuanziFragment extends BaseLazyFragment {

    Unbinder unbinder;

    public static DuanziFragment newInstance() {
        return new DuanziFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_duanzi;
    }

    @Override
    public void fetchData() {

    }


    @OnClick(R.id.test_btn)
    public void onViewClicked() {
    }
}