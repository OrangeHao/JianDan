package com.orange.jiandan.ui.pictures;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orange.jiandan.R;
import com.orange.jiandan.base.RxLazyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * created by czh on 2018-03-19
 */

public class HappyPicFragmnet extends RxLazyFragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    public static HappyPicFragmnet newInstance() {
        return new HappyPicFragmnet();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_happy_pic;
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initRefreshLayout() {

    }

    @Override
    protected void initRecyclerView() {

    }

    @Override
    public void fetchData() {
        swipeLayout.setRefreshing(true);
    }
}