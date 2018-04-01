package com.orange.jiandan.ui.jsoup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;


import com.jaeger.library.StatusBarUtil;
import com.orange.jiandan.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChapterTextActivity extends AppCompatActivity {

    @BindView(R.id.contentText)
    TextView contentText;

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
        String url=getIntent().getStringExtra("url");
        new JianLai().getContent(url, new JianLai.DataListner() {
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
