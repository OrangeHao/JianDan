package com.orange.jiandan.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * created by czh on 2018/5/20
 */

@Entity
public class CollectPicBean {

    @Id
    private long id;

    private String uri;
    private long collectTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public long getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(long collectTime) {
        this.collectTime = collectTime;
    }
}
