package com.nhnacademy.board.repository.post;

import com.nhnacademy.board.item.Page;
import com.nhnacademy.board.item.post.Post;

import java.util.List;

public interface PostRepository {
    long register(Post post);

    void modify(Post post);

    Post remove(long id);

    Post getPost(long id);

    List<Post> getPosts();

    boolean existById(long id);

    void increaseCount(Post post);

    int getTotalCount();

    Page<Post> getPagedPosts(int page, int size);

}
