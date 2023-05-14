package com.nhnacademy.board.utils;

import com.nhnacademy.board.domain.PostRequest;
import com.nhnacademy.board.entity.Post;

import java.time.LocalDateTime;

public class PostUtils {
    private PostUtils(){}

    public static Post createPostByPostRequest(PostRequest postRequest){
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setViewCount(0);
        post.setWriteTime(LocalDateTime.now());
        post.setContent(postRequest.getContent());
        return post;
    }
}
