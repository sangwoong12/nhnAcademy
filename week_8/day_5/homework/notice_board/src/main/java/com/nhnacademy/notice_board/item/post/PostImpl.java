package com.nhnacademy.notice_board.item.post;

import com.nhnacademy.notice_board.domain.PostRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostImpl implements Post {
    long id;
    String title;
    String content;
    String writerUserId;
    LocalDateTime writeTime;
    int viewCount;

    public static Post createByPostRequest(PostRequest postRequest, String writerUserId) {
        return new PostImpl(postRequest.getTitle(), postRequest.getContent(), writerUserId);
    }

    @Override
    public void increaseViewCount() {
        ++this.viewCount;
    }

    public PostImpl(String title, String content, String writerUserId) {
        this.title = title;
        this.content = content;
        this.writerUserId = writerUserId;
        this.writeTime = LocalDateTime.now();
        this.viewCount = 0;
    }

    public PostImpl() {
    }
}
