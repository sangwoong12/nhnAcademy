package com.nhnacademy.board.repository.post;

import com.nhnacademy.board.item.Page;
import com.nhnacademy.board.item.PageImpl;
import com.nhnacademy.board.item.post.Post;
import com.nhnacademy.board.mapper.post.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class DbPostRepository implements PostRepository {
    private final PostMapper postMapper;

    @Override
    public long register(Post post) {
        return postMapper.register(post);
    }

    @Override
    public void modify(Post post) {
        postMapper.modify(post);
    }

    @Override
    public Post remove(long id) {
        Post post = postMapper.getPost(id);
        postMapper.remove(id);
        return post;
    }

    @Override
    public Post getPost(long id) {
        return postMapper.getPost(id);
    }

    @Override
    public List<Post> getPosts() {
        return postMapper.getPosts();
    }

    @Override
    public boolean existById(long id) {
        return postMapper.existById(id);
    }

    @Override
    public void increaseCount(Post post) {
        post.increaseViewCount();
        postMapper.modify(post);
    }

    @Override
    public int getTotalCount() {
        return postMapper.getTotalCount();
    }

    @Override
    public Page<Post> getPagedPosts(int page, int size) {
        int totalPageCount = (int) Math.ceil((double) getTotalCount() / size); // 총 페이지 수 계산
        int start = (page - 1) * size; //0부터 시작해야해서 -1
        int end = Math.min(start + size, getTotalCount());//마지막 페이지의 경우 최대 갯수를 넘길수 없다.
        List<Post> list = getPosts().subList(start, end); // 해당 페이지의 게시물 리스트
//        List<Post> list = postMapper.getPagedPosts(size, (size - 1) * page);
        return new PageImpl<>(page, size, totalPageCount, getTotalCount(), list);
    }
}
