package com.nhnacademy.board.service.post;

import com.nhnacademy.board.entity.Post;
import com.nhnacademy.board.repository.JpaPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaPostService {
    JpaPostRepository postRepository;


    public Post getPost(long id) {
        return postRepository.getReferenceById(id);
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    public long addPost(Post post) {
        Post save = postRepository.save(post);
        return save.getId();
    }

    public void modifyPost(Post post) {
        postRepository.save(post);
    }

    public Page<Post> getPagedPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public void increaseCount(Post post) {
        int viewCount = post.getViewCount();
        post.setViewCount(++viewCount);
        postRepository.save(post);
    }
}
