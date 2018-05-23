package com.orange.jiandan.model.novel;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * created by czh on 2018/5/23
 */

@Entity
public class ChapterContent {

    @Id
    private long id;

    private long chapterId;

    private long bookId;

    private String url;

    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChapterId() {
        return chapterId;
    }

    public void setChapterId(long chapterId) {
        this.chapterId = chapterId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
