package com.nhnacademy.springmvcstudent.controller;

import com.nhnacademy.springmvcstudent.exception.ValidException;
import com.nhnacademy.springmvcstudent.item.Student;
import com.nhnacademy.springmvcstudent.item.StudentRequest;
import com.nhnacademy.springmvcstudent.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api")
public class StudentRestController {
    StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    public ResponseEntity<Void> a() {
        return ResponseEntity.status(HttpStatus.CREATED).build();//이런식으로 처리
        // bindingResult 로 밑에 수정하기.
        //TODO : register , modify에 대한 request 별도로 만들기.
    }

    @PostMapping(value = "/students")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addUser(@Valid @RequestBody StudentRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidException(bindingResult);
        }
        studentService.register(
                new Student(request.getId(), request.getName(), request.getGender(), request.getAge(), LocalDateTime.now()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/students/{studentId}")
    public Student getUser(@PathVariable("studentId") String studentId) {
        Student student = studentService.getStudent(studentId);
        return student;
    }

    @PutMapping("/students/{studentId}")
    public void putUser(@PathVariable("studentId") String studentId, @RequestBody StudentRequest studentRequest) {
        Student student = new Student(studentId, studentRequest.getName(), studentRequest.getGender(), studentRequest.getAge(), LocalDateTime.now());
        studentService.modify(student);
    }
}
