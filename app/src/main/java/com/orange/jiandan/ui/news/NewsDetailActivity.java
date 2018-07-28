package com.orange.jiandan.ui.news;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.orange.jiandan.R;
import com.orange.jiandan.base.BaseActivity;
import com.orange.jiandan.model.NewsDetail;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.Callback;
import com.zzhoujay.richtext.callback.SimpleImageFixCallback;

import butterknife.BindView;

public class NewsDetailActivity extends BaseActivity<NewsDetailView, NewsDetailPresenter> implements NewsDetailView {


    @BindView(R.id.contentView)
    TextView contentView;

    public static void start(Context context,int id) {
        Intent starter = new Intent(context, NewsDetailActivity.class);
        starter.putExtra("id",id);
        context.startActivity(starter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected NewsDetailPresenter createPresenter() {
        return new NewsDetailPresenter(this);
    }

    @Override
    protected void initView() {
        RichText.initCacheDir(this);
        mPresenter.getNewsData(getIntent().getIntExtra("id",0));
    }

    @Override
    public void getNewsDetail(NewsDetail.PostBean detail) {
        RichText.from(detail.getContent())
                .fix(new SimpleImageFixCallback() {

                    @Override
                    public void onFailure(ImageHolder holder, Exception e) {
                        super.onFailure(holder, e);
                        e.printStackTrace();
                        Log.d("czh",e+"");
                    }
                })
                .done(new Callback() {
                    @Override
                    public void done(boolean imageLoadDone) {
                        Log.d("czh","done");
                    }
                })
                .into(contentView);
    }
}
