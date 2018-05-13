package com.orange.jiandan.model.novel;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * created by czh on 2018/5/13
 */

@Entity
public class BookMessage {

    @Id
    private long id;

    private String name;
    private String author;
    private String introduction;
    private String coverUrl;
    private String currentChapterName;
    private long currentChapterId;


    private String url;
    private String baseUrl;
    private String chapterKey;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getCurrentChapterName() {
        return currentChapterName;
    }

    public void setCurrentChapterName(String currentChapterName) {
        this.currentChapterName = currentChapterName;
    }

    public long getCurrentChapterId() {
        return currentChapterId;
    }

    public void setCurrentChapterId(long currentChapterId) {
        this.currentChapterId = currentChapterId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getChapterKey() {
        return chapterKey;
    }

    public void setChapterKey(String chapterKey) {
        this.chapterKey = chapterKey;
    }

    public String getContentKey() {
        return contentKey;
    }

    public void setContentKey(String contentKey) {
        this.contentKey = contentKey;
    }

    public long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
    }

    private String contentKey;

    private long creatTime;



}
