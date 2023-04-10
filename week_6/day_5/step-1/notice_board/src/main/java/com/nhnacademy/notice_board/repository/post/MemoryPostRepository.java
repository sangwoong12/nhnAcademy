package com.nhnacademy.notice_board.repository.post;

import com.nhnacademy.notice_board.item.post.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryPostRepository implements PostRepository {
    Map<Long, Post> postMap = new ConcurrentHashMap<>();
    private static long sequence = 0L;
    @Override
    public long register(Post post) {
        sequence += 1;
        postMap.put(sequence,post);
        post.setId(sequence);
        return sequence;
    }

    @Override
    public void modify(Post post) {
        postMap.put(post.getId(),post);
    }
    @Override
    public Post remove(long id) {
        return postMap.remove(id);
    }

    @Override
    public Post getPost(long id) {
        if(!postMap.containsKey(id)){
            throw new RuntimeException("존재하지 않는 게시물입니다.");
        }
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
        int totalPageCount = (int) Math.ceil((double) getTotalCount() / size); // 총 페이지 수 계산
        int start = (page - 1) * size; //0부터 시작해야해서 -1
        int end = Math.min(start + size, getTotalCount());//마지막 페이지의 경우 최대 갯수를 넘길수 없다.
        List<Post> list = getPosts().subList(start, end); // 해당 페이지의 게시물 리스트
        return new PageImpl<>(page, size, totalPageCount, getTotalCount(), list);
    }

}
