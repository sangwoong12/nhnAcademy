package com.nhnacademy.notice_board.repository.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.notice_board.controller.exception.NotFoundPostException;
import com.nhnacademy.notice_board.item.post.Post;
import com.nhnacademy.notice_board.item.post.PostImpl;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * interface 는 직렬화가 불가능 함. 구현체로 받고 다시 post 로 만드는 과정을 거쳐야함.
 * filter 를 통해 카운트를 증가할경우 읽고 쓰는 과정을 거치지 않음. increaseCount 메서드 추가.
 */
@Slf4j
public class JsonPostRepository implements PostRepository{
    private final ObjectMapper objectMapper;
    static ClassLoader classLoader = JsonPostRepository.class.getClassLoader();
    private static final String JSON_FILE_PATH = classLoader.getResource("/").getPath();
    public JsonPostRepository() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Path path = Paths.get(JSON_FILE_PATH+"post.json");
        log.info("정보 : {}",JSON_FILE_PATH+"post.json");
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private synchronized List<Post> readJsonFile() {
        File file = new File(JSON_FILE_PATH+"post.json");
        try {
            if (!file.exists()) { // 파일이 존재하지 않을 때
                if (file.createNewFile()) { // 파일 생성
                } else {
                    throw new RuntimeException("파일 생성에 실패하였습니다.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 생성에 실패하였습니다.");
        }
        //json read & 역직렬화 ( json string -> Object )
        try(FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            if(file.length() == 0){
                return new ArrayList<>();
            }
            List<Post> posts = new ArrayList<>();
            List<PostImpl> postList;
            //interface 는 직렬화 불가능
            postList = objectMapper.readValue(bufferedReader, new TypeReference<List<PostImpl>>() {});
            for (PostImpl post : postList) {
                posts.add(post);
            }
            return posts;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    private synchronized void writeJsonFile(List<Post> studentList) {
        // List<Student> 객체를 -> json 파일로 저장 : 직렬화
        File file = new File(JSON_FILE_PATH + "post.json");

        try (
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            objectMapper.writeValue(bufferedWriter, studentList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static long sequence = 0L;

    @Override
    public void increaseCount(Post post) {
        List<Post> postList = readJsonFile();
        Optional<Post> postOp = postList.stream().filter(p -> p.getId() == post.getId()).findAny();
        if(postOp.isEmpty()){
            throw new NotFoundPostException();
        }
        postOp.get().increaseViewCount();
        writeJsonFile(postList);
    }

    @Override
    public long register(Post post) {
        List<Post> postList = readJsonFile();
        postList.add(post);
        sequence++;
        post.setId(sequence);
        writeJsonFile(postList);
        return sequence;
    }

    @Override
    public void modify(Post post) {
        List<Post> postList = readJsonFile();
        Optional<Post> removePost = postList.stream().filter(p -> p.getId() == post.getId()).findAny();
        if(removePost.isEmpty()){
            throw new NotFoundPostException();
        }
        postList.remove(removePost.get());
        postList.add(post);
        writeJsonFile(postList);
    }

    @Override
    public Post remove(long id) {
        List<Post> postList = readJsonFile();
        Optional<Post> post = postList.stream().filter(p -> p.getId() == id).findAny();
        if(post.isEmpty()){
            throw new NotFoundPostException();
        }
        postList.remove(post.get());
        writeJsonFile(postList);
        return post.get();
    }

    @Override
    public Post getPost(long id) {
        List<Post> postList = readJsonFile();
        Optional<Post> post = postList.stream().filter(p -> p.getId() == id).findAny();
        if(post.isEmpty()){
            throw new NotFoundPostException();
        }
        return post.get();
    }

    @Override
    public List<Post> getPosts() {
        return readJsonFile();
    }

    @Override
    public boolean existById(long id) {
        List<Post> postList = readJsonFile();
        Optional<Post> post = postList.stream().filter(p -> p.getId() == id).findAny();
        return !post.isEmpty();
    }

    @Override
    public int getTotalCount() {
        return readJsonFile().size();
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
