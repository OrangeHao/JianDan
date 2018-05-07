package com.orange.jiandan.ui.jsoup.chapter;


import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.orange.jiandan.R;
import com.orange.jiandan.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ContentActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, ContentActivity.class);
        context.startActivity(starter);
    }

    @BindView(R.id.chapter_pager)
    ViewPager mChapterPager;

    private ChapterViewPagerAdapter mAdapter;
    private final List<View> mViewList=new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_content;
    }

    @Override
    protected void initView() {
        for (int i=0;i<3;i++){
            View view = LayoutInflater.from(this).inflate(R.layout.layout_chapter_content,null);
            TextView txt_num = (TextView)view.findViewById(R.id.contentText);
            txt_num.setText(i + "");
            mViewList.add(view);
        }

        mAdapter=new ChapterViewPagerAdapter(mViewList);
        mChapterPager.setAdapter(mAdapter);
        mChapterPager.setCurrentItem(Integer.MAX_VALUE/2);
    }



}
