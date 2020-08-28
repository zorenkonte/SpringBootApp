package com.dark.mode.springhibernate.controller.api;

import com.dark.mode.springhibernate.controller.response.CustomStudentResponse;
import com.dark.mode.springhibernate.exception.ResourceNotFoundException;
import com.dark.mode.springhibernate.model.Student;
import com.dark.mode.springhibernate.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final CustomStudentResponse customStudentResponse;

    @Autowired
    public StudentController(StudentService studentService, CustomStudentResponse customStudentResponse) {
        this.studentService = studentService;
        this.customStudentResponse = customStudentResponse;
    }

    @GetMapping("/all")
    private CustomStudentResponse students() {
        customStudentResponse.setStudentList(studentService.all());
        return customStudentResponse;
    }

    @GetMapping("/{id}")
    private Student getStudentById(@PathVariable Integer id) {
        return studentService.getCustomer(id).orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
    }

    @GetMapping("/lastname/{lastName}")
    private CustomStudentResponse getByLastName(@PathVariable String lastName) {
        customStudentResponse.setStudentList(studentService.findByLastName(lastName));
        return customStudentResponse;
    }

}
