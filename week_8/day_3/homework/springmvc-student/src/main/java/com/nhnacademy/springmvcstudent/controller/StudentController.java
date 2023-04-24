package com.nhnacademy.springmvcstudent.controller;

import com.nhnacademy.springmvcstudent.exception.ValidException;
import com.nhnacademy.springmvcstudent.item.Student;
import com.nhnacademy.springmvcstudent.item.StudentRequest;
import com.nhnacademy.springmvcstudent.service.StudentService;
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

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/list.do")
    public String getList(Model model) {
        model.addAttribute("studentList", studentService.getStudentList());
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
        Student student = new Student(studentRequest.getId(), studentRequest.getName(), studentRequest.getGender(),
                studentRequest.getAge(), LocalDateTime.now());
        studentService.register(student);
        return "redirect:/student/view.do/" + student.getId();
    }

    @GetMapping("/view.do/{id}")
    public String getViewForm(@PathVariable("id") String id, Model model) {
        Student student = studentService.getStudent(id);
        model.addAttribute("student", student);
        return "student/view";
    }

    @GetMapping("/update.do/{id}")
    public String getUpdateForm(@PathVariable("id") String id, Model model) {
        Student student = studentService.getStudent(id);
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
        studentService.modify(student);
        return "redirect:/student/view.do/" + student.getId();
    }

    @PostMapping("/delete.do/{id}")
    public String delete(@PathVariable("id") String id) {
        studentService.delete(id);
        return "redirect:/student/list.do";
    }
}

