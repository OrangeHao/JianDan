package com.orange.jiandan.ui.pictures;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orange.jiandan.R;
import com.orange.jiandan.base.BaseLazyFragment;
import com.orange.jiandan.model.PicsBean;
import com.orange.jiandan.ui.news.NewsAdapter;
import com.orange.jiandan.utils.L;
import com.orange.jiandan.utils.MeasureTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * created by czh on 2018-03-19
 */

public class HappyPicFragmnet extends BaseLazyFragment<NicePicsView,NicePicsPresenter> implements NicePicsView{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    private final List<String> mDataList=new ArrayList<>();
    private NicePicsAdapter mAdapter;
    private int pageIndex=1;

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
                pageIndex=1;
                mPresenter.loadDatas(pageIndex,true);
            }
        });
    }

    @Override
    protected void initRecyclerView() {
        mAdapter=new NicePicsAdapter(mDataList);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                mPresenter.loadDatas(pageIndex,false);
            }
        },recyclerview);
        recyclerview.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerview.addItemDecoration(new DividerGridItemDecoration(2, MeasureTools.dp2px(getContext(),6),false));
        recyclerview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    public void fetchData() {

    }

    @Override
    public void getRefreshDatas(List<String> data) {
        L.debug("size:"+data.size());
        swipeLayout.setRefreshing(false);

        mDataList.clear();
        mDataList.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getLoadMoreDatas(List<String> data) {
        L.debug("size:"+data.size());

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
}