package com.nhnacademy.springmvcstudent.controller;

import com.nhnacademy.springmvcstudent.exception.DuplicateIdException;
import com.nhnacademy.springmvcstudent.exception.NotFoundStudentException;
import com.nhnacademy.springmvcstudent.exception.ValidException;
import com.nhnacademy.springmvcstudent.item.Student;
import com.nhnacademy.springmvcstudent.item.StudentRequest;
import com.nhnacademy.springmvcstudent.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/student")
public class StudentController {
    StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/list.do")
    public String getList(Model model) {
        model.addAttribute("studentList", studentRepository.getStudents());
        return "student/list";
    }

    @GetMapping("/register.do")
    public String getRegisterForm(Model model) {
        model.addAttribute("student",new Student());
        return "student/register";
    }

    @PostMapping("/register.do")
    public String register(@Valid StudentRequest studentRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidException(bindingResult);
        }
        if (studentRepository.existById(studentRequest.getId())) {
            throw new DuplicateIdException();
        }
        Student student = new Student(studentRequest.getId(), studentRequest.getName(), studentRequest.getGender(),
                studentRequest.getAge(), LocalDateTime.now());
        studentRepository.save(student);
        return "redirect:/student/view.do/" + student.getId();
    }

    @GetMapping("/view.do/{id}")
    public String getViewForm(@PathVariable("id") String id, Model model) {
        if (!studentRepository.existById(id)) {
            throw new NotFoundStudentException();
        }
        Student studentById = studentRepository.getStudentById(id);
        model.addAttribute("student", studentById);
        return "student/view";
    }

    @GetMapping("/update.do/{id}")
    public String getUpdateForm(@PathVariable("id") String id, Model model) {
        Student student = studentRepository.getStudentById(id);
        model.addAttribute("student", student);
        return "student/register";
    }

    @PostMapping("/update.do")
    public String update(@Valid StudentRequest studentRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidException(bindingResult);
        }
        Student student = new Student(studentRequest.getId(), studentRequest.getName(), studentRequest.getGender(),
                studentRequest.getAge(), LocalDateTime.now());
        studentRepository.update(student);
        return "redirect:/student/view.do/" + student.getId();
    }

    @PostMapping("/delete.do/{id}")
    public String delete(@PathVariable("id") String id) {
        if (!studentRepository.existById(id)) {
            throw new NotFoundStudentException();
        }
        studentRepository.deleteById(id);
        return "redirect:/student/list.do";
    }
}

