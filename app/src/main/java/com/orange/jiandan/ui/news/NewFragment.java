package com.orange.jiandan.ui.news;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orange.jiandan.R;
import com.orange.jiandan.base.BaseLazyFragment;
import com.orange.jiandan.model.NewsBean;
import com.orange.jiandan.utils.L;
import com.orange.jiandan.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * created by czh on 2018-03-19
 */

public class NewFragment extends BaseLazyFragment<NewsView,NewsPresenter> implements NewsView{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    private final List<NewsBean.PostsBean> mDataList=new ArrayList<>();
    private NewsAdapter mAdapter;
    private final int pageSize = 20;
    private int pageIndex = 1;


    public static NewFragment newInstance() {
        return new NewFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_news;
    }


    @Override
    protected NewsPresenter createPresenter() {
        return new NewsPresenter(getContext());
    }

    @Override
    public void fetchData() {
        swipeLayout.setRefreshing(true);
        mPresenter.loadDatas(pageIndex,true);
    }

    @Override
    protected void initRefreshLayout() {
        swipeLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageIndex=1;
                mPresenter.loadDatas(pageIndex,true);
            }
        });
    }

    @Override
    protected void initRecyclerView() {
        mAdapter=new NewsAdapter(mDataList);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                mPresenter.loadDatas(pageIndex,false);
            }
        },recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setAdapter(mAdapter);
    }


    @Override
    public void getRefreshNews(List<NewsBean.PostsBean> data) {
        L.debug("get refresh:"+data.size());
        swipeLayout.setRefreshing(false);

        mDataList.clear();
        mDataList.addAll(data);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void getLoadMoreNews(List<NewsBean.PostsBean> data) {
        L.debug("get loadmore:"+data.size());

        swipeLayout.setRefreshing(false);

        mDataList.addAll(data);
        mAdapter.notifyDataSetChanged();

        mAdapter.loadMoreComplete();
    }

    @Override
    public void getNewsFailed() {
        ToastUtil.showSingleToast("request failed");
        swipeLayout.setRefreshing(false);
        mAdapter.loadMoreComplete();
    }
}
