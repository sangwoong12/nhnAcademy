package com.nhnacademy.board.service.post;

import com.nhnacademy.board.exception.ExistingPostIdException;
import com.nhnacademy.board.exception.NotFoundPostException;
import com.nhnacademy.board.item.Page;
import com.nhnacademy.board.item.post.Post;
import com.nhnacademy.board.repository.post.PostRepository;
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
    public Post deletePost(long id) {
        if (!postRepository.existById(id)) {
            throw new NotFoundPostException(id);
        }
        return postRepository.remove(id);
    }

    @Override
    public long addPost(Post post) {
        if (postRepository.existById(post.getId())) {
            throw new ExistingPostIdException(post.getId());
        }
        return postRepository.register(post);
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

