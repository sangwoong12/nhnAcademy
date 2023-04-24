package com.nhnacademy.notice_board.repository.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.notice_board.exception.NotFoundUserException;
import com.nhnacademy.notice_board.item.Page;
import com.nhnacademy.notice_board.item.PageImpl;
import com.nhnacademy.notice_board.item.user.User;
import com.nhnacademy.notice_board.item.user.UserImpl;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class JsonUserRepository implements UserRepository {
    private final ObjectMapper objectMapper;
    private static final String JSON_FILE_PATH = Objects.requireNonNull(JsonUserRepository.class.getResource("/")).getPath();

    public JsonUserRepository() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Path path = Paths.get(JSON_FILE_PATH + "user.json");
        log.info("정보 : {}", JSON_FILE_PATH + "user.json");
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized List<User> readJsonFile() {
        File file = new File(JSON_FILE_PATH + "user.json");
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
        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            if (file.length() == 0) {
                return new ArrayList<>();
            }
            List<User> userList = new ArrayList<>();
            List<UserImpl> users;
            //interface 는 직렬화 불가능
            users = objectMapper.readValue(bufferedReader, new TypeReference<List<UserImpl>>() {
            });
            for (UserImpl user : users) {
                userList.add(user);
            }
            return userList;
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private synchronized void writeJsonFile(List<User> studentList) {
        // List<Student> 객체를 -> json 파일로 저장 : 직렬화
        File file = new File(JSON_FILE_PATH + "user.json");

        try (
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            objectMapper.writeValue(bufferedWriter, studentList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(User user) {
        List<User> users = readJsonFile();
        users.add(user);
        writeJsonFile(users);
    }

    @Override
    public void modify(User user) {
        List<User> users = readJsonFile();
        Optional<User> userOp = users.stream().filter(u -> Objects.equals(u.getId(), user.getId())).findAny();
        if (userOp.isEmpty()) {
            throw new NotFoundUserException(user.getId());
        }
        users.remove(userOp.get());
        users.add(user);
        writeJsonFile(users);
    }

    @Override
    public User remove(String id) {
        List<User> users = readJsonFile();
        Optional<User> userOp = users.stream().filter(u -> Objects.equals(u.getId(), id)).findAny();
        if (userOp.isEmpty()) {
            throw new NotFoundUserException(id);
        }
        users.remove(userOp.get());
        writeJsonFile(users);
        return userOp.get();
    }

    @Override
    public User getUser(String id) {
        List<User> users = readJsonFile();
        Optional<User> userOp = users.stream().filter(u -> Objects.equals(u.getId(), id)).findAny();
        if (userOp.isEmpty()) {
            throw new NotFoundUserException(id);
        }
        return userOp.get();
    }

    @Override
    public List<User> getUsers() {
        return readJsonFile();
    }

    @Override
    public boolean existById(String id) {
        List<User> users = readJsonFile();
        Optional<User> userOp = users.stream().filter(u -> Objects.equals(u.getId(), id)).findAny();
        return !userOp.isEmpty();
    }

    @Override
    public int getTotalCount() {
        return readJsonFile().size();
    }

    @Override
    public Page<User> getPagedPosts(int page, int size) {
        int totalPageCount = (int) Math.ceil((double) getTotalCount() / size); // 총 페이지 수 계산
        int start = (page - 1) * size; //0부터 시작해야해서 -1
        int end = Math.min(start + size, getTotalCount());//마지막 페이지의 경우 최대 갯수를 넘길수 없다.
        List<User> list = getUsers().subList(start, end); // 해당 페이지의 게시물 리스트
        return new PageImpl<>(page, size, totalPageCount, getTotalCount(), list);
    }
}
