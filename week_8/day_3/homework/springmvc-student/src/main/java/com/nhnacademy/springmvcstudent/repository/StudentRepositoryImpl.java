package com.nhnacademy.springmvcstudent.repository;

import com.nhnacademy.springmvcstudent.item.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StudentRepositoryImpl implements StudentRepository {
    private final Map<String, Student> studentsMap = new ConcurrentHashMap<>();

    @Override
    public void save(Student student) {
        studentsMap.put(student.getId(), student);
    }

    @Override
    public void update(Student student) {
        studentsMap.put(student.getId(), student);
    }

    @Override
    public void deleteById(String id) {
        studentsMap.remove(id);
    }

    @Override
    public Student getStudentById(String id) {
        return studentsMap.get(id);
    }

    @Override
    public List<Student> getStudents() {
        return new ArrayList<>(studentsMap.values());
    }

    @Override
    public boolean existById(String id) {
        return studentsMap.containsKey(id);
    }
}
