package com.nhnacademy.student.item;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class JsonStudentRepository implements StudentRepository{
    private final ObjectMapper objectMapper;
    static ClassLoader classLoader = JsonStudentRepository.class.getClassLoader();
    private static final String JSON_FILE_PATH = classLoader.getResource("/").getPath();
    public JsonStudentRepository() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Path path = Paths.get(JSON_FILE_PATH+"student.json");
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private synchronized List<Student> readJsonFile() {
        File file = new File(JSON_FILE_PATH+"student.json");
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
            List<Student> students;
            log.info("readValue 하기");
            students = objectMapper.readValue(bufferedReader, new TypeReference<List<Student>>() {});
            return students;
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private synchronized void writeJsonFile(List<Student> studentList){
        // List<Student> 객체를 -> json 파일로 저장 : 직렬화
        File file = new File(JSON_FILE_PATH+"student.json");

        try(
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            objectMapper.writeValue(bufferedWriter,studentList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Student student) {
        List<Student> students = readJsonFile();
        students.add(student);
        writeJsonFile(students);
    }

    @Override
    public void update(Student student) {
        List<Student> students = readJsonFile();
        students.remove(students.stream()
                .filter(st -> st.getId().equals(student.getId()))
                .findFirst().get());
        students.add(student);
        writeJsonFile(students);
    }

    @Override
    public void deleteById(String id) {
        List<Student> students = readJsonFile();
        students.remove(students.stream()
                .filter(st -> st.getId().equals(id))
                .findFirst().get());
        writeJsonFile(students);
    }

    @Override
    public Student getStudentById(String id) {
        List<Student> students = readJsonFile();
        return students.stream()
                .filter(st -> st.getId().equals(id))
                .findFirst().get();
    }

    @Override
    public List<Student> getStudents() {
        return readJsonFile();
    }

    @Override
    public boolean existById(String id) {
        List<Student> students = readJsonFile();
        for (Student student : students) {
            if(student.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
}
