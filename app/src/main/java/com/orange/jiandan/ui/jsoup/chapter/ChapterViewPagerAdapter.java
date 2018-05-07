package com.orange.jiandan.ui.jsoup.chapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * created by czh on 2018/5/7
 */
public class ChapterViewPagerAdapter extends PagerAdapter{
    private List<View> list_view;

    public ChapterViewPagerAdapter(List<View> list_view) {
        this.list_view = list_view;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list_view.get(position % list_view.size()));
        return list_view.get(position % list_view.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
