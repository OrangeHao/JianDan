package com.orange.jiandan.ui.jsoup.chapter;

import com.orange.jiandan.base.BaseView;
import com.orange.jiandan.model.novel.ChapterMessage;

import java.util.List;

/**
 * created by czh on 2018/5/8
 */
public interface ChapterContentView extends BaseView{

    void receivedContent(String content,int position);

    void receivedChapters(List<ChapterMessage> chapterList);
}
