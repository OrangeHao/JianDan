package com.orange.jiandan.ui.jsoup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;


import com.jaeger.library.StatusBarUtil;
import com.orange.jiandan.R;
import com.orange.jiandan.ui.jsoup.data.Books;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChapterTextActivity extends AppCompatActivity {

    @BindView(R.id.contentText)
    TextView contentText;

    private static final String BOOK="book";

    public static void start(Context context, String url, Books books) {
        Intent starter = new Intent(context, ChapterTextActivity.class);
        starter.putExtra(BOOK,books);
        starter.putExtra("url",url);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_text);
        ButterKnife.bind(this);

        StatusBarUtil.setColor(this,getResources().getColor(R.color.gray));
        StatusBarUtil.setLightMode(this);

        initView();
        initData();
    }

    private void initView(){

    }

    private void initData(){
        new NormalBookStucture(getIntent().getParcelableExtra(BOOK)).getContent(getIntent().getStringExtra("url"), new NormalBookStucture.DataListner() {
            @Override
            public void getChapters(List<Chapter> list) {

            }

            @Override
            public void getBookText(String content) {
                contentText.setText(Html.fromHtml(content));
            }
        });
    }
}
