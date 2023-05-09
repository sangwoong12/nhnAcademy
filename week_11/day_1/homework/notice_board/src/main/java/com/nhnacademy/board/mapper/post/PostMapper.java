package com.nhnacademy.board.mapper.post;

import com.nhnacademy.board.item.post.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    Long register(Post post);

    void modify(Post post);

    void remove(long id);

    Post getPost(long id);
    List<Post> getPosts();

    boolean existById(long id);

    int getTotalCount();

    List<Post> getPagedPosts(int size, int page);
}
