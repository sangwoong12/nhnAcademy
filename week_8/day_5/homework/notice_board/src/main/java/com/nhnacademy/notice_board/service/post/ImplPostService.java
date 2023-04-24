package com.nhnacademy.notice_board.service.post;

import com.nhnacademy.notice_board.exception.ExistingPostIdException;
import com.nhnacademy.notice_board.exception.NotFoundPostException;
import com.nhnacademy.notice_board.item.Page;
import com.nhnacademy.notice_board.item.post.Post;
import com.nhnacademy.notice_board.repository.post.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class ImplPostService implements PostService {
    PostRepository postRepository;

    public ImplPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post getPost(long id) {
        if (postRepository.existById(id)) {
            return postRepository.getPost(id);
        }
        throw new NotFoundPostException(id);
    }

    @Override
    public void deletePost(long id) {
        if (!postRepository.existById(id)) {
            throw new NotFoundPostException(id);
        }
        postRepository.remove(id);
    }

    @Override
    public void addPost(Post post) {
        if (postRepository.existById(post.getId())) {
            throw new ExistingPostIdException(post.getId());
        }
        postRepository.register(post);
    }

    @Override
    public void modifyPost(long id, String title, String content) {
        Post post = postRepository.getPost(id);
        post.setTitle(title);
        post.setContent(content);
        postRepository.modify(post);
    }

    @Override
    public void increaseCount(Post post) {
        postRepository.increaseCount(post);
    }

    @Override
    public Page<Post> getPagedPosts(int page, int size) {
        return postRepository.getPagedPosts(page, size);
    }
}

