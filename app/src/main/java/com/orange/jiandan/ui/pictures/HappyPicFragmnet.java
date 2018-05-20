package com.orange.jiandan.ui.pictures;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orange.jiandan.R;
import com.orange.jiandan.base.BaseLazyFragment;
import com.orange.jiandan.model.CollectPicBean;
import com.orange.jiandan.model.JianDanDB;
import com.orange.jiandan.utils.L;
import com.orange.jiandan.utils.MeasureTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * created by czh on 2018-03-19
 */

public class HappyPicFragmnet extends BaseLazyFragment<NicePicsView, NicePicsPresenter> implements NicePicsView {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    private final List<String> mDataList = new ArrayList<>();
    @BindView(R.id.fab)
    FloatingActionButton fab;
    Unbinder unbinder;
    private NicePicsAdapter mAdapter;
    private int pageIndex = 1;

    public static HappyPicFragmnet newInstance() {
        return new HappyPicFragmnet();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_happy_pic;
    }


    @Override
    protected NicePicsPresenter createPresenter() {
        return new NicePicsPresenter(getContext());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initRefreshLayout() {
        swipeLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageIndex = 1;
                mPresenter.loadDatas(pageIndex, true);
            }
        });
    }

    @Override
    protected void initRecyclerView() {
        mAdapter = new NicePicsAdapter(mDataList);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                mPresenter.loadDatas(pageIndex, false);
            }
        }, recyclerview);
        recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerview.addItemDecoration(new DividerGridItemDecoration(2, MeasureTools.dp2px(getContext(), 6), false));
        recyclerview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PictureViewActivity.start(getActivity(), mDataList, position);
            }
        });
    }

    @Override
    public void fetchData() {

    }

    @Override
    public void getRefreshDatas(List<String> data) {
        L.debug("size:" + data.size());
        swipeLayout.setRefreshing(false);

        mDataList.clear();
        mDataList.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getLoadMoreDatas(List<String> data) {
        L.debug("size:" + data.size());

        swipeLayout.setRefreshing(false);

        mDataList.addAll(data);
        mAdapter.notifyDataSetChanged();

        mAdapter.loadMoreComplete();
    }

    @Override
    public void onFailed(Throwable e) {
        L.debug("request failed");
        swipeLayout.setRefreshing(false);
        mAdapter.loadMoreComplete();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        List<CollectPicBean> temp=JianDanDB.PicQuertAll();
        mDataList.clear();
        for (CollectPicBean bean:temp){
            mDataList.add(bean.getUri());
        }
        mAdapter.notifyDataSetChanged();
    }
}