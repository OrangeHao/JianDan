package com.orange.jiandan.ui.books;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orange.jiandan.R;
import com.orange.jiandan.model.novel.BookMessage;

import java.util.List;

/**
 * created by czh on 2018/5/14
 */
public class BookListAdapter extends BaseQuickAdapter<BookMessage,BaseViewHolder>{

    public BookListAdapter(@Nullable List<BookMessage> data) {
        super(R.layout.item_book_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookMessage item) {
        helper.setText(R.id.book_name,item.getName());
    }

}
