package com.orange.jiandan.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orange.jiandan.R;
import com.orange.jiandan.base.BaseLazyFragment;
import com.orange.jiandan.ui.jsoup.books.GuiMi;
import com.orange.jiandan.ui.jsoup.books.JianLaiLe;
import com.orange.jiandan.ui.jsoup.books.YuLeChunQiu;
import com.orange.jiandan.ui.jsoup.chapter.ChaptersActivity;
import com.orange.jiandan.ui.jsoup.chapter.ContentActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * created by czh on 2018-03-19
 */

public class DuanziFragment extends BaseLazyFragment {

    Unbinder unbinder;

    public static DuanziFragment newInstance() {
        return new DuanziFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_duanzi;
    }

    @Override
    public void fetchData() {

    }


    @OnClick({R.id.jianlai_btn,R.id.guimi_btn,R.id.yulechunqiu_btn})
    public void jianLaiOnclick(View view){
        switch (view.getId()){
            case R.id.jianlai_btn:
                ChaptersActivity.start(getActivity(),new JianLaiLe());
                break;
            case R.id.guimi_btn:
                ChaptersActivity.start(getActivity(),new GuiMi());
                break;
            case R.id.yulechunqiu_btn:
                ChaptersActivity.start(getActivity(),new YuLeChunQiu());
                break;
        }
    }

}