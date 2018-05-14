package com.orange.jiandan.ui.books;

import com.orange.jiandan.base.BaseView;
import com.orange.jiandan.model.novel.BookMessage;

import java.util.List;

/**
 * created by czh on 2018/5/14
 */
public interface BookListView extends BaseView{

    void getBooks(List<BookMessage> data);

}
