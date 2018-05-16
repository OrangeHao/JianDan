package com.orange.jiandan.ui.jsoup.chapter;

import com.orange.jiandan.base.BaseView;
import com.orange.jiandan.model.novel.ChapterMessage;
import com.orange.jiandan.ui.jsoup.bean.Chapter;

import java.util.List;

/**
 * created by czh on 2018/5/7
 */
public interface ChapterListView extends BaseView{

     void getChapters(List<ChapterMessage> list);

}
