package com.nhnacademy.notice_board.service.post;

import com.nhnacademy.notice_board.item.Page;
import com.nhnacademy.notice_board.item.post.Post;

public interface PostService {
    Post getPost(long id);

    void deletePost(long id);

    void addPost(Post post);

    void modifyPost(long id, String title, String content);

    Page<Post> getPagedPosts(int page, int size);

    void increaseCount(Post post);
}
