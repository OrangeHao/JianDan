package com.orange.jiandan.ui.jsoup.chapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orange.jiandan.R;
import com.orange.jiandan.model.novel.ChapterMessage;
import com.orange.jiandan.ui.jsoup.bean.Chapter;

import java.util.List;

/**
 * created by czh on 2018/5/7
 */
public class ChapterViewPagerAdapter extends PagerAdapter{
    private List<ChapterMessage> list;
    private View mCurrentView;

    public ChapterViewPagerAdapter(List<ChapterMessage> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_chapter_content,null);
        view.setTag(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }


    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
        mCurrentView=(View) object;
    }

    public View getCurrentView(){
        return mCurrentView;
    }
}
