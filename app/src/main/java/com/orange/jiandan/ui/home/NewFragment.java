package com.orange.jiandan.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.orange.jiandan.R;
import com.orange.jiandan.base.RxLazyFragment;
import com.orange.jiandan.ui.jsoup.ChaptersActivity;

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

    @OnClick({R.id.jianlai_btn,R.id.guimi_btn})
    public void jianLaiOnclick(View view){
        switch (view.getId()){
            case R.id.jianlai_btn:
                startActivity(new Intent(getActivity(), ChaptersActivity.class));
                break;
            case R.id.guimi_btn:

                break;
        }
    }


}
