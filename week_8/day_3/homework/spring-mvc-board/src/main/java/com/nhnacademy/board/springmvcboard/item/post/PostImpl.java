package com.nhnacademy.board.springmvcboard.item.post;

import java.time.LocalDateTime;

public class PostImpl implements Post {
    long id;
    String title;
    String content;
    String writerUserId;
    LocalDateTime writeTime;
    int viewCount;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getWriterUserId() {
        return writerUserId;
    }

    @Override
    public void setWriterUserId(String writerUserId) {
        this.writerUserId = writerUserId;
    }

    @Override
    public LocalDateTime getWriteTime() {
        return writeTime;
    }

    @Override
    public void setWriteTime(LocalDateTime writeTime) {
        this.writeTime = writeTime;
    }

    @Override
    public int getViewCount() {
        return viewCount;
    }

    @Override
    public void increaseViewCount() {
        ++this.viewCount;
    }

    public PostImpl(long id, String title, String content, String writerUserId, LocalDateTime writeTime, int viewCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerUserId = writerUserId;
        this.writeTime = writeTime;
        this.viewCount = viewCount;
    }
    public PostImpl(){};
}
