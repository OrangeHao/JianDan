package com.orange.jiandan.ui.jsoup.chapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jaeger.library.StatusBarUtil;
import com.orange.jiandan.R;
import com.orange.jiandan.base.BaseActivity;
import com.orange.jiandan.ui.jsoup.ChapterListAdapter;
import com.orange.jiandan.ui.jsoup.ChapterTextActivity;
import com.orange.jiandan.ui.jsoup.bean.Chapter;
import com.orange.jiandan.ui.jsoup.books.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ChaptersActivity extends BaseActivity<ChapterListView,ChapterListPresenter>implements ChapterListView{

    @BindView(R.id.chapter_rv)
    RecyclerView chapterRv;
    private static final String BOOK="book";

    private final List<Chapter> chapterList=new ArrayList<Chapter>();
    private ChapterListAdapter mAdapter;
    
    public static void start(Context context, long bookId) {
        Intent starter = new Intent(context, ChaptersActivity.class);
        starter.putExtra(BOOK, bookId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        StatusBarUtil.setLightMode(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_chapters;
    }

    @Override
    protected ChapterListPresenter createPresenter() {
        return new ChapterListPresenter(this,getIntent().getLongExtra(BOOK,1));
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @OnClick({R.id.sort})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.sort:
                Collections.reverse(chapterList);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }


    @Override
    public void getChapters(List<Chapter> list) {
        chapterList.addAll(list);
        Collections.reverse(chapterList);
        chapterRv.setLayoutManager(new LinearLayoutManager(ChaptersActivity.this));
        mAdapter=new ChapterListAdapter(R.layout.item_chapter_list,chapterList);
        chapterRv.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ChapterTextActivity.start(ChaptersActivity.this,chapterList.get(position).getUrl(),getIntent().getParcelableExtra(BOOK));
                ContentActivity.start(mContext,getIntent().getParcelableExtra(BOOK),chapterList,position);
            }
        });
    }
}
