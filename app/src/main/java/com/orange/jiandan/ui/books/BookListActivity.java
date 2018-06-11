package com.orange.jiandan.ui.books;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orange.jiandan.R;
import com.orange.jiandan.base.BaseActivity;
import com.orange.jiandan.model.novel.BookMessage;
import com.orange.jiandan.ui.jsoup.chapter.ChaptersActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BookListActivity extends BaseActivity<BookListView,BookListPresenter> implements BookListView{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    private final List<BookMessage> mDatList=new ArrayList<>();
    private BookListAdapter mAdapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, BookListActivity.class);
        context.startActivity(starter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_book_list;
    }

    @Override
    protected BookListPresenter createPresenter() {
        return new BookListPresenter(mContext);
    }

    @Override
    protected void initRefreshLayout() {
        swipeLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getBooks();
            }
        });
    }

    @Override
    protected void initRecyclerView() {
        mAdapter=new BookListAdapter(mDatList);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        recyclerview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ChaptersActivity.start(mContext,mDatList.get(position).getId());
            }
        });

        swipeLayout.setRefreshing(true);
        mPresenter.getBooks();
    }


    @Override
    public void getBooks(List<BookMessage> data) {
        swipeLayout.setRefreshing(false);

        mDatList.clear();
        mDatList.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(Throwable e) {
        swipeLayout.setRefreshing(false);
    }
}
