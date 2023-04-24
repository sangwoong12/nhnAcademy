package com.nhnacademy.notice_board.repository.post;

import com.nhnacademy.notice_board.item.Page;
import com.nhnacademy.notice_board.item.PageImpl;
import com.nhnacademy.notice_board.item.post.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryPostRepository implements PostRepository {
    Map<Long, Post> postMap = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    @Override
    public long register(Post post) {
        long seq = sequence.incrementAndGet();
        postMap.put(seq, post);
        post.setId(seq);
        return seq;
    }

    @Override
    public void modify(Post post) {
        postMap.put(post.getId(), post);
    }

    @Override
    public Post remove(long id) {
        return postMap.remove(id);
    }

    @Override
    public Post getPost(long id) {
        return postMap.get(id);
    }

    @Override
    public List<Post> getPosts() {
        return new ArrayList<>(postMap.values());
    }

    @Override
    public boolean existById(long id) {
        return postMap.containsKey(id);
    }

    @Override
    public int getTotalCount() {
        return postMap.size();
    }

    @Override
    public Page<Post> getPagedPosts(int page, int size) {
        int totalPageCount = (int) Math.ceil((double) getTotalCount() / size);
        int start = (page - 1) * size;
        int end = Math.min(start + size, getTotalCount());
        List<Post> list = getPosts().subList(start, end);
        return new PageImpl<>(page, size, totalPageCount, getTotalCount(), list);
    }

    @Override
    public void increaseCount(Post post) {
        post.increaseViewCount();
    }
}
