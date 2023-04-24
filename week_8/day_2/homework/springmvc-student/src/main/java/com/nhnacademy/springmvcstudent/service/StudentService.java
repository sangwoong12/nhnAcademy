package com.nhnacademy.springmvcstudent.service;

import com.nhnacademy.springmvcstudent.exception.DuplicateIdException;
import com.nhnacademy.springmvcstudent.exception.NotFoundStudentException;
import com.nhnacademy.springmvcstudent.item.Student;
import com.nhnacademy.springmvcstudent.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudent(String id){
       Student student =  studentRepository.getStudentById(id);
       if(Objects.isNull(student)){
           throw new NotFoundStudentException();
       }
       return student;
    }

    public void delete(String id){
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentList(){
        return studentRepository.getStudents();
    }

    public void register(Student student){
        if(studentRepository.existById(student.getId())){
            throw new DuplicateIdException();
        }
        studentRepository.save(student);
    }

    public void modify(Student student){
        studentRepository.update(student);
    }

}
