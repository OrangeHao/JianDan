package com.orange.jiandan.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.orange.jiandan.R;
import com.orange.jiandan.base.RxLazyFragment;
import com.orange.jiandan.ui.jsoup.ChaptersActivity;
import com.orange.jiandan.ui.jsoup.data.GuiMi;
import com.orange.jiandan.ui.jsoup.data.JianLaiLe;
import com.orange.jiandan.ui.jsoup.data.YuLeChunQiu;

import butterknife.OnClick;

/**
 * created by czh on 2018-03-19
 */

public class NewFragment extends RxLazyFragment{

    public static NewFragment newInstance() {
        return new NewFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_news;
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
