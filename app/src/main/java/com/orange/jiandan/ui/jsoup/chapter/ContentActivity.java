package com.orange.jiandan.ui.jsoup.chapter;


import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.orange.jiandan.R;
import com.orange.jiandan.base.BaseActivity;
import com.orange.jiandan.base.BasePresenter;
import com.orange.jiandan.ui.jsoup.bean.Chapter;
import com.orange.jiandan.ui.jsoup.books.Book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ContentActivity extends BaseActivity<ChapterContentView,ChapterContentPresenter>  implements ViewPager.OnPageChangeListener,ChapterContentView{

    public static void start(Context context, Book book,List<Chapter> data,int position) {
        Intent starter = new Intent(context, ContentActivity.class);
        starter.putExtra("book",book);
        starter.putExtra("data",(Serializable) data);
        starter.putExtra("position",position);
        context.startActivity(starter);
    }

    @BindView(R.id.chapter_pager)
    ViewPager mChapterPager;

    private ChapterViewPagerAdapter mAdapter;
    private List<Chapter> mDataList;
    private int currentItem;
    private TextView currentTextView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_content;
    }

    @Override
    protected ChapterContentPresenter createPresenter() {
        return new ChapterContentPresenter(this,getIntent().getParcelableExtra("book"));
    }

    @Override
    protected boolean enableReturnBtn() {
        return false;
    }

    @Override
    protected void initView() {
        setToolBarScrollEnable(true);
        setBarColor(R.color.gray);
        setBarTitleColor(R.color.textDefualt2);
//        StatusBarUtil.setTranslucent(this);
//        StatusBarUtil.setColor(this,getResources().getColor(R.color.gray));
        StatusBarUtil.setLightMode(this);

        currentItem=getIntent().getIntExtra("position",0);
        mDataList=(List<Chapter>) getIntent().getSerializableExtra("data");

        mAdapter=new ChapterViewPagerAdapter(mDataList);
        mChapterPager.setAdapter(mAdapter);
        mChapterPager.addOnPageChangeListener(this);
        mChapterPager.setCurrentItem(currentItem);

        setBarTitle(mDataList.get(currentItem).getTitle());
        mPresenter.getContent(mDataList.get(currentItem).getUrl(),currentItem);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("czh","position:"+position);
        currentItem=position;
        setBarTitle(mDataList.get(position).getTitle());
        View view=mChapterPager.findViewWithTag(position);
        currentTextView=view.findViewById(R.id.contentText);
        if (TextUtils.isEmpty(currentTextView.getText().toString())){
            mPresenter.getContent(mDataList.get(position).getUrl(),position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void receivedContent(String content,int position) {
        View view=mChapterPager.findViewWithTag(position);
        TextView textView=view.findViewById(R.id.contentText);
        if (textView!=null){
            textView.setText(Html.fromHtml(content));
        }
    }
}
