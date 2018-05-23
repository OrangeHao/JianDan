package com.orange.jiandan.ui.jsoup.chapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jaeger.library.StatusBarUtil;
import com.orange.jiandan.R;
import com.orange.jiandan.base.BaseActivity;
import com.orange.jiandan.model.novel.BookMessage;
import com.orange.jiandan.model.novel.ChapterMessage;
import com.orange.jiandan.ui.jsoup.ChapterListAdapter;
import com.orange.jiandan.ui.jsoup.ChapterTextActivity;
import com.orange.jiandan.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChaptersActivity extends BaseActivity<ChapterListView, ChapterListPresenter> implements ChapterListView {

    @BindView(R.id.chapter_rv)
    RecyclerView chapterRv;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    private static final String BOOK = "book";
    private final List<ChapterMessage> chapterList = new ArrayList<>();
    private ChapterListAdapter mAdapter;

    public static void start(Context context, long bookId) {
        Intent starter = new Intent(context, ChaptersActivity.class);
        starter.putExtra(BOOK, bookId);
        context.startActivity(starter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_chapters;
    }

    @Override
    protected ChapterListPresenter createPresenter() {
        return new ChapterListPresenter(this, getIntent().getLongExtra(BOOK, 1));
    }


    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        mPresenter.loadLocalData();
    }

    @Override
    protected void initRefreshLayout() {
        swipeLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadNewData();
            }
        });
    }

    @Override
    protected void initRecyclerView() {
        mAdapter = new ChapterListAdapter(R.layout.item_chapter_list, chapterList,mPresenter.getmBook());
        chapterRv.setLayoutManager(new LinearLayoutManager(ChaptersActivity.this));
        chapterRv.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ContentActivity.start(mContext,getIntent().getLongExtra(BOOK, 1),chapterList.get(position).getUrl());
            }
        });
    }

    @OnClick({R.id.sort})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sort:
                Collections.reverse(chapterList);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }


    @Override
    public void getChapters(List<ChapterMessage> list) {
        swipeLayout.setRefreshing(false);

        if (list.size()==0){
            ToastUtil.LongToast("no chapters");
            return;
        }

        chapterList.clear();
        chapterList.addAll(list);
        mAdapter.notifyDataSetChanged();
        gotoCurentChapter();
    }

    @Override
    public void onFailed(Throwable e) {
        swipeLayout.setRefreshing(false);
    }

    private void gotoCurentChapter(){
        int position=0;
        for (int i=0;i<chapterList.size();i++){
            if(mPresenter.getmBook().getCurrentChapterId()==chapterList.get(i).getId()){
                position=i;
            }
        }
        if (position==0){
            return;
        }
        chapterRv.scrollToPosition(position);
    }
}
