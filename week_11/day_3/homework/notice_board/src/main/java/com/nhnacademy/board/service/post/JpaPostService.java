package com.nhnacademy.board.service.post;

import com.nhnacademy.board.domain.EditPostDto;
import com.nhnacademy.board.domain.PostDto;
import com.nhnacademy.board.entity.Post;
import com.nhnacademy.board.exception.ExistingPostIdException;
import com.nhnacademy.board.exception.ExistingUserIdException;
import com.nhnacademy.board.exception.NotFoundPostException;
import com.nhnacademy.board.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JpaPostService {
    PostRepository postRepository;

    @Autowired
    public JpaPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post getPost(long id) {
        return postRepository.getPostById(id);
    }

    @Transactional
    public void deletePost(long id) {
        if (!postRepository.existsById(id)) {
            throw new NotFoundPostException(id);
        }
        postRepository.deleteById(id);
    }
    @Transactional
    public void modifyPost(EditPostDto editPostDto) {
        Post post = postRepository.getPostById(editPostDto.getId());
        post.setTitle(editPostDto.getTitle());
        post.setContent(editPostDto.getContent());
        postRepository.save(post);
    }

    public Page<PostDto> getPagedPosts(Pageable pageable) {
        return postRepository.getAllBy(pageable);
    }
    @Transactional
    public Post increaseCountAndGetPost(Long postId) {
        postRepository.increaseViewCount(postId);
        return postRepository.getPostById(postId);
    }

    public void addPost(Post post) {
        if (postRepository.existsById(post.getId())) {
            throw new ExistingPostIdException(post.getId());
        }
        postRepository.save(post);
    }
}
