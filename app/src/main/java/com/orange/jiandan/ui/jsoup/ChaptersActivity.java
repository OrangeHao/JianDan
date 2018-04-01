package com.orange.jiandan.ui.jsoup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jaeger.library.StatusBarUtil;
import com.orange.jiandan.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChaptersActivity extends AppCompatActivity {

    @BindView(R.id.chapter_rv)
    RecyclerView chapterRv;

    private List<Chapter> chapterList=new ArrayList<Chapter>();

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
        new JianLai().getChapters(new JianLai.DataListner() {
            @Override
            public void getChapters(List<Chapter> list) {
                chapterList.addAll(list);
                Collections.reverse(chapterList);
                chapterRv.setLayoutManager(new LinearLayoutManager(ChaptersActivity.this));
                ChapterListAdapter adapter=new ChapterListAdapter(R.layout.item_chapter_list,chapterList);
                chapterRv.setAdapter(adapter);

                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Log.d("czh",chapterList.get(position).getTitle());
                        startActivity(new Intent(ChaptersActivity.this,ChapterTextActivity.class)
                                .putExtra("url",chapterList.get(position).getUrl()));
                    }
                });
            }

            @Override
            public void getBookText(String content) {

            }
        });
    }


}
