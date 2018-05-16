package com.orange.jiandan.model.novel;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * created by czh on 2018/5/13
 */

@Entity
public class ChapterMessage {

    @Id
    private long id;

    private long bookId;
    private long chapterIndex;
    private String title;
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getChapterIndex() {
        return chapterIndex;
    }

    public void setChapterIndex(long chapterIndex) {
        this.chapterIndex = chapterIndex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
