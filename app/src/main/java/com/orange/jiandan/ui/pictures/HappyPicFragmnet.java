package com.orange.jiandan.ui.pictures;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.orange.jiandan.R;
import com.orange.jiandan.base.BaseLazyFragment;

import butterknife.BindView;

/**
 * created by czh on 2018-03-19
 */

public class HappyPicFragmnet extends BaseLazyFragment {

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