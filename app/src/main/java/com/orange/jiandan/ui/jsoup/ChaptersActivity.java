package com.orange.jiandan.ui.jsoup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orange.jiandan.R;
import com.orange.jiandan.ui.jsoup.data.Books;
import com.orange.jiandan.ui.jsoup.data.JianLaiLe;
import com.orange.jiandan.ui.jsoup.data.YuLeChunQiu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChaptersActivity extends AppCompatActivity {

    @BindView(R.id.chapter_rv)
    RecyclerView chapterRv;
    private static final String BOOK="book";

    private final List<Chapter> chapterList=new ArrayList<Chapter>();
    private ChapterListAdapter mAdapter;
    
    public static void start(Context context, Books books) {
        Intent starter = new Intent(context, ChaptersActivity.class);
        starter.putExtra(BOOK,books);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_chapters);

        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView(){

    }

    private void initData(){
        new NormalBookStucture(getIntent().getParcelableExtra(BOOK)).getChapters(new NormalBookStucture.DataListner() {
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
                        ChapterTextActivity.start(ChaptersActivity.this,chapterList.get(position).getUrl(),getIntent().getParcelableExtra(BOOK));
                    }
                });
            }

            @Override
            public void getBookText(String content) {

            }
        });
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


}
