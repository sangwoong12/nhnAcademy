package com.nhnacademy.board.service.post;

import com.nhnacademy.board.item.Page;
import com.nhnacademy.board.item.post.Post;

public interface PostService {
    Post getPost(long id);

    Post deletePost(long id);

    long addPost(Post post);

    void modifyPost(long id, String title, String content);

    Page<Post> getPagedPosts(int page, int size);

    void increaseCount(Post post);
}
