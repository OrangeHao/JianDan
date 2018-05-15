package com.orange.jiandan.ui.jsoup.books;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.orange.jiandan.R;
import com.orange.jiandan.base.BaseActivity;
import com.orange.jiandan.ui.jsoup.chapter.ChaptersActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BooksActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, BooksActivity.class);
        context.startActivity(starter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_books;
    }

    @OnClick({R.id.jianlai_btn, R.id.guimi_btn, R.id.yulechunqiu_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jianlai_btn:
//                ChaptersActivity.start(mContext,new JianLaiLe());
                break;
            case R.id.guimi_btn:
//                ChaptersActivity.start(mContext,new GuiMi());
                break;
            case R.id.yulechunqiu_btn:
//                ChaptersActivity.start(mContext,new YuLeChunQiu());
                break;
        }
    }
}
