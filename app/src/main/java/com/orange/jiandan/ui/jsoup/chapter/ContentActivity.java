package com.orange.jiandan.ui.jsoup.chapter;


import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.orange.jiandan.R;
import com.orange.jiandan.base.BaseActivity;
import com.orange.jiandan.model.novel.BookMessage;
import com.orange.jiandan.model.novel.ChapterMessage;
import com.orange.jiandan.model.novel.NovelDB;
import com.orange.jiandan.ui.jsoup.bean.Chapter;
import com.orange.jiandan.ui.jsoup.books.Book;
import com.orange.jiandan.utils.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ContentActivity extends BaseActivity<ChapterContentView,ChapterContentPresenter>  implements ViewPager.OnPageChangeListener,ChapterContentView{

    public static void start(Context context, long bookId,String urlOnclik) {
        Intent starter = new Intent(context, ContentActivity.class);
        starter.putExtra("book",bookId);
        starter.putExtra("urlOnclik",urlOnclik);
        context.startActivity(starter);
    }

    @BindView(R.id.chapter_pager)
    ViewPager mChapterPager;

    private BookMessage mBook;
    private ChapterViewPagerAdapter mAdapter;
    private List<ChapterMessage> mDataList=new ArrayList<>();
    private int currentItem;

    @Override
    public int getLayoutId() {
        return R.layout.activity_content;
    }

    @Override
    protected ChapterContentPresenter createPresenter() {
        return new ChapterContentPresenter(this,getIntent().getLongExtra("book",1));
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
        StatusBarUtil.setLightMode(this);

        mBook= NovelDB.BookQuertById(getIntent().getLongExtra("book",1));
        mPresenter.loadLocalChapters(mBook.getId());
    }

    private void initViewPage(){
        String urlOnclik=getIntent().getStringExtra("urlOnclik");
        int len=mDataList.size();
        for (int i=0;i<len;i++){
            if (mDataList.get(i).getUrl().equals(urlOnclik)){
                currentItem=i;
                break;
            }
        }

        mAdapter=new ChapterViewPagerAdapter(mDataList);
        mChapterPager.setAdapter(mAdapter);
        mChapterPager.addOnPageChangeListener(this);
        mChapterPager.setOffscreenPageLimit(3);
        mChapterPager.setCurrentItem(currentItem);

        setBarTitle(mDataList.get(currentItem).getTitle());
        mPresenter.getContent(mDataList.get(currentItem),currentItem);

        updateCurrentChapter();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("czh","position:"+position);
        currentItem=position;
        setBarTitle(mDataList.get(position).getTitle());

        updateCurrentChapter();
        setCurAndNextData(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void receivedContent(String content,int position) {
        View view=mChapterPager.findViewWithTag(position);
        if (view==null){
            return;
        }
        TextView textView=view.findViewById(R.id.contentText);
        if (textView!=null){
            textView.setText(Html.fromHtml(content));
        }
    }

    @Override
    public void receivedChapters(List<ChapterMessage> chapterList) {
        mDataList.addAll(chapterList);
        initViewPage();
    }

    @Override
    public void onFailed(Throwable e) {
        ToastUtil.showSingleToast("get local chapters error:"+e);
    }

    private void setCurAndNextData(int position){
        View current=mChapterPager.findViewWithTag(position);
        if(current!=null && TextUtils.isEmpty(((TextView)current.findViewById(R.id.contentText)).getText().toString())){
            mPresenter.getContent(mDataList.get(position),position);
        }
        if (position+1<mDataList.size()){
            position++;
            View next=mChapterPager.findViewWithTag(position);
            if(next!=null && TextUtils.isEmpty(((TextView)next.findViewById(R.id.contentText)).getText().toString())){
                mPresenter.getContent(mDataList.get(position),position);
            }
        }
    }

    private void updateCurrentChapter(){
        mBook.setCurrentChapterName(mDataList.get(currentItem).getTitle());
        mBook.setCurrentChapterId(mDataList.get(currentItem).getId());
        NovelDB.BookUpdate(mBook);
    }
}
