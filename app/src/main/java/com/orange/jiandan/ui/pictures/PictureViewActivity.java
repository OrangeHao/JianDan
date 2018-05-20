package com.orange.jiandan.ui.pictures;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;

import com.orange.jiandan.R;
import com.orange.jiandan.base.BaseActivity;
import com.orange.jiandan.model.CollectPicBean;
import com.orange.jiandan.model.JianDanDB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PictureViewActivity extends BaseActivity {


    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private List<String> mDataList = new ArrayList<>();
    private PictureViewAdapter mAdapter;


    public static void start(Context context, List<String> datas, int position) {
        Intent starter = new Intent(context, PictureViewActivity.class);
        starter.putExtra("data", (Serializable) datas);
        starter.putExtra("position", position);
        context.startActivity(starter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_picture_view;
    }

    @Override
    protected void initView() {
        mDataList = (List<String>) getIntent().getSerializableExtra("data");

        mAdapter = new PictureViewAdapter(mDataList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(getIntent().getIntExtra("position", 0));
    }


    @OnClick(R.id.fab)
    public void onViewClicked() {
        CollectPicBean bean=new CollectPicBean();
        bean.setUri(mDataList.get(mViewPager.getCurrentItem()));
        bean.setCollectTime(System.currentTimeMillis());
        JianDanDB.PicAdd(bean);
    }
}
